package com.dev.community.model.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dev.community.model.dao.CommunityDao;
import com.dev.community.model.dto.Community;
import com.dev.community.model.dto.CommunityFile;
import com.dev.community.model.dto.Reply;
import com.dev.notice.model.dto.NoticeFile;

@Service
public class CommunityServiceImpl implements CommunityService {

	private CommunityDao dao;
	private SqlSession session;
	public CommunityServiceImpl(SqlSession session, CommunityDao dao) {
		this.dao=dao;
		this.session=session;
	}
	
	@Override
	public List<Community> communityList(Map<String, Object> paging) {
		
		return dao.communityList(session, paging);
	}

	@Override
	public Community communityView(int no) {
		
		return dao.communityView(session, no);
	}

	@Override
	public int insertCommunity(Community m) {
		
		
		
		return dao.insertCommunity(session, m);
	}

	@Override
	public String communitySaveFile(MultipartFile file, HttpSession session) {
		if(file.isEmpty()) {
			return null;
		}
		String path=session.getServletContext().getRealPath("/upload/community/");
		
		String oriName=file.getOriginalFilename();
		String uuid=UUID.randomUUID().toString();
		String extension=oriName.substring(oriName.lastIndexOf("."));
		String fileName=uuid+extension;
		//파일 저장경로
		String savePath=path+fileName;
		try {
			file.transferTo(new File(savePath));
		}catch(IOException e) {
			e.printStackTrace();
		}
		String[] split = path.split("webapp");
		path = split[1]; // realPath에서 파일이름만 남김
		
		
		
		return fileName;
	}

	@Override
	public int communityCount() {
		
		return dao.communityCount(session);
	}

	@Override
	public int communitySaveFileDB(CommunityFile file) {
		if(file.getCommunityNo()!=0) {
			return dao.updateCommunityFile(session, file);
		}
		
		return dao.communitySaveFile(session, file);
	}

	@Override
	public int removeCommunityFile(String fileName, HttpSession hsession) {
		String path=hsession.getServletContext().getRealPath("/upload/community/");
		File file=new File(path+fileName);
		if(file.exists()) {
			file.delete();
			dao.deleteCommunityFile(session, fileName);
			return 1;
		}
		return 0;
	}

	@Override
	public int communityLike(int id, int no) {
		Map<String, Object> like=new HashMap<>();
		like.put("memberId", id);
		like.put("communityNo",no);
		Map<String, Object>likeResult= dao.selectLike(session,like );
		if(likeResult==null) {
			dao.insertLike(session, like);
			dao.likeCountUp(session, no);
			return 1;
		}else {
			dao.deleteLike(session, like);
			dao.likeCountDown(session, no);
		}
		return 0;
	}

	@Override
	public Map<String,Object> CommunityLikeCheck(int id, int no) {
		
		return dao.selectLike(session, Map.of("memberId",id,"communityNo",no));
	}

	@Override
	public int insertReply(Reply reply) {
		int result=0;
		if(reply.getReplyRef()==0) {
			result=dao.insertReply(session, reply);
		}else {
			result=dao.insertReplies(session, reply);
		}
		return result;
		
	}

	

	@Override
	public List<Reply> replyList(int communityNo) {
//		List<Reply>replyList= dao.replyList(session, communityNo);
//		List<Reply>resultList=new ArrayList<>();
//		for(Reply r:replyList) {
//			if(r.getReplyLevel()==0) {
//				int temp=r.getReplyNo();
//				resultList.add(r);
//				for(Reply p:replyList) {
//					if(temp==p.getReplyRef()) {
//						resultList.add(p);
//					}
//				}
//			}
//		}
		
		return dao.replyList(session, communityNo);
	}

	@Override
	public int updateReply(Reply r) {
		
		return dao.updateReply(session, r);
	}

	@Override
	public int deleteReply(Reply r) {
		int level=r.getReplyLevel();
		int replyNo=r.getReplyNo();
		if(level==0) {
			return dao.deleteReply(session, replyNo);
		}
		return dao.deleteReplies(session, replyNo);
	}

	@Override
	public int updateCommunity(Community c) {
		
		return dao.updateCommunity(session, c);
	}

	@Override
	public int deleteCommunity(int communityNo, HttpSession hsession) {
		
		List<String> fileList=dao.selectCommunityFileList(session, communityNo);
		for(String f:fileList) {
			removeCommunityFile(f, hsession);
		}
		
		return dao.deleteCommunity(session, communityNo);
	}

	@Override
	public List<Community> mypageCommunity(int memberId, Map<String, Object> params) {
		
		return dao.mypageCommunity(session, memberId, params);
	}
	
	

}
