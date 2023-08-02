package com.dev.nc.common;

import java.io.File;
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
		  String fileName=null;
		  Map<String, MultipartFile> fileMap = request.getFileMap();

          for (MultipartFile mf : fileMap.values()) {
        	 fileName=service.saveFile(mf, session);
          }
        
		return fileName;
	}
	
	@PostMapping("/removeFile.do")
	@ResponseBody
	public String removeFile(String fileName,HttpSession session) {
		String path=session.getServletContext().getRealPath("/upload/notice/");
		File file=new File(path+fileName);
		if(file.exists()) {
			file.delete();
			service.deleteNoticeFile(fileName);
			return "true";
		}
		return "false";
	}
}









