package com.dev.nc.common;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dev.notice.model.service.NoticeService;

@Controller
@RequestMapping("/ncCommon")
public class FileController {

	private NoticeService service;
	
	public FileController(NoticeService service) {
		this.service=service;
	}
	
	@PostMapping("/noticeUploadFile.do")
	@ResponseBody
	public String noticeUploadFile(MultipartHttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		  String path=null;
		  Map<String, MultipartFile> fileMap = request.getFileMap();

          for (MultipartFile mf : fileMap.values()) {
        	 path=service.saveFile(mf, session);
          }
        System.out.println(path);
		return path;
	}
}
