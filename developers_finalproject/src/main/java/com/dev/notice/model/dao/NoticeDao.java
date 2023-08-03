package com.dev.notice.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dev.notice.model.dto.Notice;
import com.dev.notice.model.dto.NoticeFile;

public interface NoticeDao {

	List<Notice> noticeList(SqlSession session);
	int insertNotice(SqlSession session,Notice m);
	int deleteNotice(SqlSession session,int no);
	int deleteNoticeFile(SqlSession session, String fileName);
	int noticeSaveFile(SqlSession session,NoticeFile file);
	Notice noticeView(SqlSession session,int no);
}
