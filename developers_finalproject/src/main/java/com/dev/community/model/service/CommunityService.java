package com.dev.community.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.web.multipart.MultipartFile;

import com.dev.community.model.dto.Community;
import com.dev.community.model.dto.CommunityFile;


public interface CommunityService {

	List<Community> communityList(Map<String, Object> paging);
	Community communityView(int no,HttpServletRequest req, HttpServletResponse res);
	int insertCommunity(Community m);
	String communitySaveFile(MultipartFile file,HttpSession session);
	int communityCount();
	int communitySaveFileDB(CommunityFile file);
	int removeCommunityFile(String fileName,HttpSession session);
	
}
