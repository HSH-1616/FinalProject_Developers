package com.dev.notice.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.dev.notice.model.dao.NoticeDao;
import com.dev.notice.model.dto.Notice;

@Service
public class NoticeServiceImpl implements NoticeService {

	private NoticeDao dao;
	private SqlSession session;
	
	public NoticeServiceImpl(NoticeDao dao, SqlSession session) {
		this.dao=dao;
		this.session=session;
	}
	@Override
	public List<Notice> NoticeList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertNotice(Notice m) {
		
		return dao.insertNotice(session, m);
	}

	@Override
	public int deleteNotice(String no) {
		// TODO Auto-generated method stub
		return 0;
	}

}
