package com.dev.touris.model.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dev.touris.model.service.TourisService;
import com.dev.touris.model.vo.TourisArea;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/touris")
public class RestTourisController {
	private TourisService service;
	
	public RestTourisController(TourisService service) {
		this.service=service;
	}
	
	//메인페이지에서 모달창 띄어주기
	@GetMapping("/selecttourisarea")
	@ResponseBody
	public List<TourisArea> selectTourisArea(String areaName) {
		System.out.println(service.selectTourisArea(areaName));
		return service.selectTourisArea(areaName);
	}
	
	
}
