package com.dev.admin.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dev.admin.model.dto.Admin;
import com.dev.admin.model.service.AdminService;


@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private AdminService service;
	
	public AdminController(AdminService service) {
		this.service=service;
	}
	@GetMapping
	public String adminPage() {
		return "admin/loginadmin";
	}
	@GetMapping("/memberList")
	public String memberList() {
		return "admin/memberList";
	}
	@PostMapping("/adminlogin")
	public String adminLogin(@RequestParam Map param,Model m) {
		System.out.println(param);
		Admin a=service.adminLogin(param);
		System.out.println(a);
		if(a!=null) {
			return "redirect:/admin/memberList";
		}else {
			return "admin/loginadmin";			
		}
	}
	
}
