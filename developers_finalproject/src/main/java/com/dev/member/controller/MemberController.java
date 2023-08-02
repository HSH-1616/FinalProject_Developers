package com.dev.member.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.dev.member.model.dto.Member;
import com.dev.member.service.MemberService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Controller
@SessionAttributes({"loginMember"})
@RequestMapping("/member")
public class MemberController {

	private String CLIENT_ID = "TR_SQ2GAJzrrTPobWiSh"; // 애플리케이션 클라이언트 아이디값";
	private String CLI_SECRET = "KzSC54sFgk"; // 애플리케이션 클라이언트 시크릿값";
	private MemberService service;

	public MemberController(MemberService service) {
		this.service = service;
	}
	@GetMapping("/KakaoLoginCheck")
	@ResponseBody
	public Member KakaoLoginCheck(@RequestParam Map param) {
		String memberEmail=(String)param.get("memberEmail");
		Member m=service.selectByEmail(memberEmail);
		return m;
	}
	@GetMapping("/Kakaoenroll")
	public String Kakaoenroll(@RequestParam Map param,Model model) {
		String memberNickname=(String)param.get("memberNickname");
		String memberImage=(String)param.get("memberImage");
		String memberEmail=(String)param.get("memberEmail");
		Member m = Member.builder().memberNickname(memberNickname)
				.memberImage(memberImage)
				.memberEmail(memberEmail.trim()).memberCategory("K").build();
		service.insertMember(m);
		Member member=service.selectByEmail(memberEmail);
		model.addAttribute("loginMember",member);
		return "redirect:/";
	}
	@GetMapping("KakaoLogin")
	public String KakaoLogin(@RequestParam Map param,Model model) {
		Member member=service.selectByEmail((String)param.get("memberEmail"));
		model.addAttribute("loginMember",member);
		return "redirect:/";
	}
	
//네이버로그인처리
	@RequestMapping("/naver/callback")
	public String naverCallback1(String code, String state, Model model) throws IOException, ParseException {
		// String code = request.getParameter("code");
		System.out.println(code);
		// String state = request.getParameter("state");
		System.out.println(state);
		String redirectURI = URLEncoder.encode("http://localhost:8888/member/naver/callback", "UTF-8");
		String apiURL;
		apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
		apiURL += "client_id=" + CLIENT_ID;
		apiURL += "&client_secret=" + CLI_SECRET;
		apiURL += "&redirect_uri=" + redirectURI;
		apiURL += "&code=" + code;
		apiURL += "&state=" + state;
		System.out.println("apiURL=" + apiURL);
		String res = requestToServer(apiURL);
		if (res != null && !res.equals("")) {
			System.out.println(res);
			model.addAttribute("res", res);
			Map<String, Object> parsedJson = new JSONParser(res).parseObject();
			System.out.println(parsedJson);
			String accessToken = (String) parsedJson.get("access_token");
			// 액세스 토큰으로 네이버에서 프로필 받기
			String apiURL2 = "https://openapi.naver.com/v1/nid/me";
			String headerStr = "Bearer " + accessToken; // Bearer 다음에 공백 추가
			String res2 = requestToServer(apiURL2, headerStr);
			if (res2 != null && !res.equals("")) {
				System.out.println(res2);
				model.addAttribute("res", res2);
				// Map<String, Object> parsedJson2 = new JSONParser(res2).parseObject();
				JsonObject obj = JsonParser.parseString(res2.toString()).getAsJsonObject();
				JsonObject arr = (JsonObject) obj.get("response");
				System.out.println("arr : " + arr);
				String memberNickname = arr.get("nickname").getAsString();
				String memberImage = arr.get("profile_image").getAsString();
				String memberEmail = arr.get("email").getAsString();
				Member member = service.selectByEmail(memberEmail.trim());
				System.out.println(member);
				if(member==null) {
				Member m = Member.builder().memberNickname(memberNickname)
						.memberImage(memberImage)
						.memberEmail(memberEmail.trim()).memberCategory("N").build();
				service.insertMember(m);
				}
				model.addAttribute("loginMember",member);
			}
		} else {
			model.addAttribute("res", "Login failed!");
		}
		return "redirect:/";
	}
	
//로그아웃
	/**
	 * 세션 무효화(로그아웃)
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(SessionStatus status) {
		if(!status.isComplete()) status.setComplete();
		return "redirect:/";
	}
	

	private String requestToServer(String apiURL, String headerStr) throws IOException {
		URL url = new URL(apiURL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		System.out.println("header Str: " + headerStr);
		if (headerStr != null && !headerStr.equals("")) {
			con.setRequestProperty("Authorization", headerStr);
		}
		int responseCode = con.getResponseCode();
		BufferedReader br;
		System.out.println("responseCode=" + responseCode);
		if (responseCode == 200) { // 정상 호출
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} else { // 에러 발생
			br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		}
		String inputLine;
		StringBuffer res = new StringBuffer();
		while ((inputLine = br.readLine()) != null) {
			res.append(inputLine);
		}
		br.close();
		if (responseCode == 200) {
			return res.toString();
		} else {
			return null;
		}
	}

	/**
	 * 토큰 갱신 요청 페이지 컨트롤러
	 * 
	 * @param session
	 * @param request
	 * @param model
	 * @param refreshToken
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping("/naver/refreshToken")
	public String refreshToken(HttpSession session, HttpServletRequest request, Model model, String refreshToken)
			throws IOException, ParseException {
		String apiURL;
		apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=refresh_token&";
		apiURL += "client_id=" + CLIENT_ID;
		apiURL += "&client_secret=" + CLI_SECRET;
		apiURL += "&refresh_token=" + refreshToken;
		System.out.println("apiURL=" + apiURL);
		String res = requestToServer(apiURL);
		model.addAttribute("res", res);
		session.invalidate();
		return "test-naver-callback";
	}

	/**
	 * 토큰 삭제 컨트롤러
	 * 
	 * @param session
	 * @param request
	 * @param model
	 * @param accessToken
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/naver/deleteToken")
	public String deleteToken(HttpSession session, HttpServletRequest request, Model model, String accessToken)
			throws IOException {
		String apiURL;
		apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=delete&";
		apiURL += "client_id=" + CLIENT_ID;
		apiURL += "&client_secret=" + CLI_SECRET;
		apiURL += "&access_token=" + accessToken;
		apiURL += "&service_provider=NAVER";
		System.out.println("apiURL=" + apiURL);
		String res = requestToServer(apiURL);
		model.addAttribute("res", res);
		session.invalidate();
		return "test-naver-callback";
	}
	/**
	 * 서버 통신 메소드
	 * 
	 * @param apiURL
	 * @return
	 * @throws IOException
	 */
	private String requestToServer(String apiURL) throws IOException {
		return requestToServer(apiURL, "");
	}
	
}
