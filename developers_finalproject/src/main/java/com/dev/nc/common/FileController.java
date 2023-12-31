package com.dev.nc.common;

import java.io.File;
import java.io.IOException;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dev.community.model.dto.CommunityFile;
import com.dev.community.model.service.CommunityService;
import com.dev.notice.model.service.NoticeService;

@Controller
@RequestMapping("/ncCommon")
public class FileController {

	private NoticeService service;
	private CommunityService communityService;
	public FileController(NoticeService service, CommunityService communityService) {
		this.service=service;
		this.communityService=communityService;
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
	
	@PostMapping("/communityUploadFile.do")
	@ResponseBody
	public String communityUploadFile(@RequestParam(value = "no",defaultValue = "0") int communityNo, MultipartHttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		 
		  Map<String, MultipartFile> fileMap = request.getFileMap();
		  String fileName="";
		  String fileNames="";
		  if(communityNo!=0) {
			  for (MultipartFile mf : fileMap.values()) {
					 fileName=communityService.communitySaveFile(mf, session);
					 fileNames+=fileName+" ";
					 CommunityFile file=CommunityFile.builder().fileName(fileName).communityNo(communityNo).build();
					 communityService.communitySaveFileDB(file);
				 };
		  }else {
			 for (MultipartFile mf : fileMap.values()) {
				 fileNames+=communityService.communitySaveFile(mf, session)+" "; 
			 };
			 
		  }
        fileNames=fileNames.strip();
		return fileNames;
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
	
	@PostMapping("/removeCommunityFile.do")
	@ResponseBody
	public int removeCommunityFile(String fileName,HttpSession session) {
		
			
			
		
		return communityService.removeCommunityFile(fileName, session);
	}
}









