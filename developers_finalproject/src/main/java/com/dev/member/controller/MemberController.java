package com.dev.member.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestTemplate;

import com.dev.member.model.dto.Member;
import com.dev.member.service.MemberService;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Controller
@SessionAttributes({"loginMember"})
@RequestMapping("/member")
public class MemberController {

	private final Environment env;
	private final RestTemplate restTemplate = new RestTemplate();
	private String CLIENT_ID = "TR_SQ2GAJzrrTPobWiSh"; //네이버 애플리케이션 클라이언트 아이디값;
	private String CLI_SECRET = "KzSC54sFgk"; //네이버 애플리케이션 클라이언트 시크릿값;

	private MemberService service;
    
	public MemberController(MemberService service,Environment env) {
		this.env = env;
		this.service = service;
	}
	
//카카오 로그인 처리
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
	public String naverLogin(String code, String state, Model model) throws IOException, ParseException {
//		System.out.println(code);
//		System.out.println(state);
		String redirectURI = URLEncoder.encode("http://localhost:8888/member/naver/callback", "UTF-8");
		String apiURL;
		apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
		apiURL += "client_id=" + CLIENT_ID;
		apiURL += "&client_secret=" + CLI_SECRET;
		apiURL += "&redirect_uri=" + redirectURI;
		apiURL += "&code=" + code;
		apiURL += "&state=" + state;
		//System.out.println("apiURL=" + apiURL);
		String res = requestToServer(apiURL);
		if (res != null && !res.equals("")) {
//			System.out.println(res);
			model.addAttribute("res", res);
			Map<String, Object> parsedJson = new JSONParser(res).parseObject();
//			System.out.println(parsedJson);
			String accessToken = (String) parsedJson.get("access_token");
			// 액세스 토큰으로 네이버에서 프로필 받기
			String apiURL2 = "https://openapi.naver.com/v1/nid/me";
			String headerStr = "Bearer " + accessToken; // Bearer 다음에 공백 추가
			String res2 = requestToServer(apiURL2, headerStr);
			if (res2 != null && !res.equals("")) {
//				System.out.println(res2);
				model.addAttribute("res", res2);
				// Map<String, Object> parsedJson2 = new JSONParser(res2).parseObject();
				JsonObject obj = JsonParser.parseString(res2.toString()).getAsJsonObject();
				JsonObject arr = (JsonObject) obj.get("response");
//				System.out.println("arr : " + arr);
				String memberNickname = arr.get("nickname").getAsString();
				String memberImage = arr.get("profile_image").getAsString();
				String memberEmail = arr.get("email").getAsString();
				Member member = service.selectByEmail(memberEmail);
//				System.out.println(member);
				if(member==null) {
				Member m = Member.builder().memberNickname(memberNickname)
						.memberImage(memberImage)
						.memberEmail(memberEmail).memberCategory("N").build();
				service.insertMember(m);
				}
				model.addAttribute("loginMember",member);
			}
		} else {
			model.addAttribute("res", "Login failed!");
		}
		return "redirect:/";
	}

	private String requestToServer(String apiURL, String headerStr) throws IOException {
		URL url = new URL(apiURL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
//		System.out.println("header Str: " + headerStr);
		if (headerStr != null && !headerStr.equals("")) {
			con.setRequestProperty("Authorization", headerStr);
		}
		int responseCode = con.getResponseCode();
		BufferedReader br;
//		System.out.println("responseCode=" + responseCode);
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
	private String requestToServer(String apiURL) throws IOException {
		return requestToServer(apiURL, "");
	}
	
//구글 로그인 처리
    @GetMapping("/login/oauth2/code/{registrationId}")
    public String googleLogin(@RequestParam String code,@PathVariable String registrationId, Model model) {
        String accessToken = getAccessToken(code, registrationId);
        JsonNode userResourceNode = getUserResource(accessToken, registrationId);

        String memberEmail = userResourceNode.get("email").asText();
        String memberNickname = userResourceNode.get("name").asText();
        String memberImage = userResourceNode.get("picture").asText();
        
		Member member = service.selectByEmail(memberEmail);
//		System.out.println(member);
		if(member==null) {
		Member m = Member.builder().memberNickname(memberNickname)
				.memberImage(memberImage)
				.memberEmail(memberEmail).memberCategory("G").build();
		service.insertMember(m);
		}
		model.addAttribute("loginMember",member);
		return "redirect:/";
    }
    private String getAccessToken(String authorizationCode, String registrationId) {
        String clientId = env.getProperty("oauth2." + registrationId + ".client-id");
        String clientSecret = env.getProperty("oauth2." + registrationId + ".client-secret");
        String redirectUri = env.getProperty("oauth2." + registrationId + ".redirect-uri");
        String tokenUri = env.getProperty("oauth2." + registrationId + ".token-uri");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", authorizationCode);
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUri);
        params.add("grant_type", "authorization_code");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity entity = new HttpEntity(params, headers);

        ResponseEntity<JsonNode> responseNode = restTemplate.exchange(tokenUri, HttpMethod.POST, entity, JsonNode.class);
        JsonNode accessTokenNode = responseNode.getBody();
        return accessTokenNode.get("access_token").asText();
    }

    private JsonNode getUserResource(String accessToken, String registrationId) {
        String resourceUri = env.getProperty("oauth2."+registrationId+".resource-uri");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity entity = new HttpEntity(headers);
        return restTemplate.exchange(resourceUri, HttpMethod.GET, entity, JsonNode.class).getBody();
    }
	
 //로그아웃
  	@RequestMapping("/logout")
  	public String logout(SessionStatus status) {
  		if(!status.isComplete()) status.setComplete();
  		return "redirect:/";
  	}	
	
	
}
