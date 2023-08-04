package com.dev.notice.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.dev.notice.model.dto.Notice;
import com.dev.notice.model.dto.NoticeFile;

public interface NoticeDao {

	List<Notice> noticeList(SqlSession session,Map<String, Object> paging);
	int insertNotice(SqlSession session,Notice m);
	int deleteNotice(SqlSession session,int no);
	int deleteNoticeFile(SqlSession session, String fileName);
	int noticeSaveFile(SqlSession session,NoticeFile file);
	Notice noticeView(SqlSession session,int no);
	int updateNotice(SqlSession session,Notice n);
	int noticeViewCountUp(SqlSession session, int no);
	List<Notice> searchNotice(SqlSession session, Map<String, Object> params,Map<String,Object> paging);
	int noticeCount(SqlSession session);
	
}
