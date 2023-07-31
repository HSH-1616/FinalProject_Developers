package com.dev.notice.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dev.notice.model.dto.Notice;

public interface NoticeDao {

	List<Notice> NoticeList(SqlSession session);
	int insertNotice(SqlSession session,Notice m);
	int deleteNotice(SqlSession session,String no);
}
