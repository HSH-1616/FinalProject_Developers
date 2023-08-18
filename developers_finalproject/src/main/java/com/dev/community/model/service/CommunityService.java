package com.dev.community.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.web.multipart.MultipartFile;

import com.dev.community.model.dto.Community;
import com.dev.community.model.dto.CommunityFile;
import com.dev.community.model.dto.Reply;


public interface CommunityService {

	List<Community> communityList(Map<String, Object> paging);
	Community communityView(int no);
	int insertCommunity(Community m);
	String communitySaveFile(MultipartFile file,HttpSession session);
	int communityCount(int memberId);
	int communitySaveFileDB(CommunityFile file);
	int removeCommunityFile(String fileName,HttpSession session);
	int communityLike(int id, int no);
	Map<String,Object> CommunityLikeCheck(int id, int no);
	int insertReply(Reply reply);
	List<Reply> replyList(int communityNo);
	int updateReply(Reply r);
	int deleteReply(Reply r);
	int updateCommunity(Community c);
	int deleteCommunity(int communityNo,HttpSession hsession);
	List<Community> mypageCommunity(int memberId, Map<String, Object> params);
	
}
