package com.dev.notice.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dev.notice.model.dto.Notice;

@Repository
public class NoticeDaoImpl implements NoticeDao {

	
	
	@Override
	public List<Notice> NoticeList(SqlSession session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertNotice(SqlSession session, Notice m) {
		return session.insert("notice.insertNotice",m);
	}

	@Override
	public int deleteNotice(SqlSession session, String no) {
		// TODO Auto-generated method stub
		return 0;
	}

}
