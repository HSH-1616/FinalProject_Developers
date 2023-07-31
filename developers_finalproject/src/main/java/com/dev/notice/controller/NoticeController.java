package com.dev.notice.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dev.notice.model.service.NoticeService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/notice")
@Slf4j
public class NoticeController {
	private static final String UPLOAD_PATH = "C:\\upload\\tmp";
	private NoticeService service;
	
	public NoticeController(NoticeService service) {
		this.service=service;
	}
	
	@GetMapping("/noticeList.do")
	public String noticeList() {
		return "/notice/noticeList";
	}
	
	@GetMapping("/noticeWrite.do")
	public String noticeWrite() {
		return "/notice/noticeWrite";
	}
	
//	@PostMapping("/insertNotice.do")
//	public String insertNotice(Model m,Notice n) {
//		System.out.println(n);
//		int result=service.insertNotice(n);
//		if(result>0) {
//			System.out.println("성공");
//		}else {
//			System.out.println("실패");
//		}
//		return "redirect:/notice/noticeList.do";
//	}
	@PostMapping("/uploadFile.do")
	@ResponseBody
	public int uploadFile(MultipartFile files) {
		String file_name=saveFile(files);
		System.out.println(file_name);
		return 0;
	}
	
private String saveFile(MultipartFile file){
        
        UUID uuid = UUID.randomUUID();
        String saveName = uuid + "_" + file.getOriginalFilename();
        
        // 저장할 File 객체를 생성(껍데기 파일)
        File saveFile = new File(UPLOAD_PATH,saveName); // 저장할 폴더 이름, 저장할 파일 이름
 
        try {
            file.transferTo(saveFile); // 업로드 파일에 saveFile이라는 껍데기 입힘
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
 
        return saveName;
    } 
}
