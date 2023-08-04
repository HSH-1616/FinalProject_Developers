package com.dev.notice.model.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import com.dev.notice.model.dao.NoticeDao;
import com.dev.notice.model.dto.Notice;
import com.dev.notice.model.dto.NoticeFile;

@Service
public class NoticeServiceImpl implements NoticeService {

	private NoticeDao dao;
	private SqlSession session;
	
	public NoticeServiceImpl(NoticeDao dao, SqlSession session) {
		this.dao=dao;
		this.session=session;
	}
	@Override
	public List<Notice> noticeList(Map<String, Object> paging) {
		
		return dao.noticeList(session,paging);
	}

	@Override
	public int insertNotice(Notice m) {
		
		return dao.insertNotice(session, m);
	}

	@Override
	public int deleteNotice(int no) {
		return dao.deleteNotice(session, no);
	}
	@Override
	public String saveFile(MultipartFile file, HttpSession hsession) {
		if(file.isEmpty()) {
			return null;
		}
		String path=hsession.getServletContext().getRealPath("/upload/notice/");
		
		String oriName=file.getOriginalFilename();
		String uuid=UUID.randomUUID().toString();
		String extension=oriName.substring(oriName.lastIndexOf("."));
		String fileName=uuid+extension;
		NoticeFile nfile=NoticeFile.builder().oriName(oriName).fileName(fileName).filePath(path).build();
		//파일 저장경로
		String savePath=path+fileName;
		try {
			file.transferTo(new File(savePath));
		}catch(IOException e) {
			e.printStackTrace();
		}
		String[] split = path.split("webapp");
		path = split[1]; // realPath에서 프로젝트 경로만 남김
		
		dao.noticeSaveFile(session,nfile);
		
		return fileName;
	}
	@Override
	public Notice noticeView(int no,HttpServletRequest req, HttpServletResponse res) {
		Integer noticeNo=no;
		Cookie[] cookies = req.getCookies();
		Cookie oldCookie=null;
		if(cookies != null) {
			for(Cookie cookie:cookies) {
				
				if(cookie.getName().equals("view")) {
					oldCookie=cookie;
				}
			}
		}
		if(oldCookie !=null) {
			if(!oldCookie.getValue().contains("["+noticeNo.toString()+"]")) {
				dao.noticeViewCountUp(session, no);
				oldCookie.setValue(oldCookie.getValue()+"_["+noticeNo+"]");
				oldCookie.setPath("/");
				oldCookie.setMaxAge(60*60*1);
				res.addCookie(oldCookie);
			}
		}else {
				dao.noticeViewCountUp(session, noticeNo);
				Cookie newCookie=new Cookie("view","["+noticeNo+"]");
				newCookie.setPath("/");
				newCookie.setMaxAge(60*60*12);
				res.addCookie(newCookie);
			}
		
		return dao.noticeView(session, no);
	}
	@Override
	public int deleteNoticeFile(String fileName) {
		
		return dao.deleteNoticeFile(session, fileName);
	}
	@Override
	public int updateNotice(Notice n) {
		
		return dao.updateNotice(session, n);
	}
	@Override
	public List<Notice> searchNotice(Map<String, Object> params, Map<String, Object> paging) {
		
		return dao.searchNotice(session,params,paging);
	}
	@Override
	public int noticeCount() {
		
		return dao.noticeCount(session);
	}

	
}








