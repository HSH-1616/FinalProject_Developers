package com.dev.touris.model.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.dev.touris.model.service.TourisService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/touris")
public class TourisController {
	private TourisService service;
	
	public TourisController(TourisService service) {
		this.service=service;
	}
	
	//메인페이지에서 
	@RequestMapping("/tourismainpage")
	public String tourisMainSelectMapPage(Model model) {
		model.addAttribute("area", service.tourisMainSelectMapPage());
		return "touris/tourismainpage";
	}
	
	@RequestMapping("/tourismap")
	public String tourislist(String areaEng, Model model) throws Exception{
		String apiurl = "http://api.openweathermap.org/data/2.5/weather?q="+areaEng+"&lang=kr&appid="+"28efd00f42dd0134f7e11ce21611ea86";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(apiurl, HttpMethod.GET, null, String.class);
        String r = response.getBody();
        System.out.println(r);
        JsonObject obj = JsonParser.parseString(r).getAsJsonObject();
        JsonObject coordObject = obj.getAsJsonObject("coord");
        double lon = coordObject.get("lon").getAsDouble();
        double lat = coordObject.get("lat").getAsDouble();
        System.out.println("Longitude: " + lon);
        System.out.println("Latitude: " + lat);

        // Get the "weather" array
        JsonArray weatherArray = obj.getAsJsonArray("weather");
        System.out.println(weatherArray);
        System.out.println();
		model.addAttribute("tourislist", service.selecttourislist(areaEng));
		model.addAttribute("titletouris", service.selecttourisarealist(areaEng));
		
		
		double roundedTemperature = obj.get("main").getAsJsonObject().get("temp").getAsDouble() - 273;
		int temperature = (int)roundedTemperature;
		model.addAttribute("temperature",temperature);
		model.addAttribute("humidity", obj.get("main").getAsJsonObject().get("humidity").getAsInt());
		model.addAttribute("weatherMain",
				obj.get("weather").getAsJsonArray().get(0).getAsJsonObject().get("main").getAsString());
		model.addAttribute("weatherDescription",
				obj.get("weather").getAsJsonArray().get(0).getAsJsonObject().get("description").getAsString());
		model.addAttribute("weatherIcon",
				obj.get("weather").getAsJsonArray().get(0).getAsJsonObject().get("icon").getAsString());
		model.addAttribute("windSpeed", obj.get("wind").getAsJsonObject().get("speed").getAsDouble());
		model.addAttribute("country", obj.get("sys").getAsJsonObject().get("country").getAsString());
		model.addAttribute("city", obj.get("name").getAsString());
		model.addAttribute("clouds", obj.get("clouds").getAsJsonObject().get("all").getAsInt());
//		System.out.println(temperature);
//		System.out.println(obj.get("main").getAsJsonObject().get("humidity").getAsInt());
		return "touris/tourismap";
	}
	
	@RequestMapping("/searchtouris")
	public String searchtouris(@RequestParam Map<String, Object> param, String text, String tourisAreaid, Model model) {
		param.put("text", text);
		param.put("areaId", tourisAreaid);
		
		model.addAttribute("tourlist", service.searchtouris(param));
		System.out.println(service.searchtouris(param));
		return "touris/searchtouris";
	}
	@RequestMapping("/searchtouris2")
	public String searchtouris2(@RequestParam Map<String, Object> param, String text, String tourisAreaid, Model model) {
		param.put("text", text);
		param.put("areaId", tourisAreaid);
		
		model.addAttribute("tourlist", service.searchtouris(param));
		return "touris/searchtouris2";
	}
	@RequestMapping(value = "/inserttourisroute", method = RequestMethod.POST, consumes = "application/json")
	public String inserttourisroute(@RequestBody Map data) {
		List<Map> routedata = (List<Map>) data.get("routedata");
		Integer loginmemberid = Integer.parseInt(String.valueOf(data.get("loginmemberid")));
		String tustartDate = (String) data.get("tustartDate");
		String tuendDate = (String) data.get("tuendDate");
//		System.out.println(routedata);
		service.inserttourismember(loginmemberid, tustartDate, tuendDate, routedata);
		return "redirect:/";
	}
	
	
}

