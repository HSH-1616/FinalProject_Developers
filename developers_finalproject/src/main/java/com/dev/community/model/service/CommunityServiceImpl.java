package com.dev.community.model.service;

import java.io.File;
import java.io.IOException;
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
	public Community communityView(int no, HttpServletRequest req, HttpServletResponse res) {
		
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int communitySaveFileDB(CommunityFile file) {
		
		return dao.communitySaveFile(session, file);
	}

	@Override
	public int removeCommunityFile(String fileName, HttpSession session) {
		String path=session.getServletContext().getRealPath("/upload/community/");
		File file=new File(path+fileName);
		if(file.exists()) {
			file.delete();
			
			return 1;
		}
		return 0;
	}
	

}
