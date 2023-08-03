package com.dev.notice.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dev.notice.model.dto.Notice;
import com.dev.notice.model.dto.NoticeFile;

@Repository
public class NoticeDaoImpl implements NoticeDao {

	
	
	@Override
	public List<Notice> noticeList(SqlSession session) {
		return session.selectList("notice.noticeList");
	}

	@Override
	public int insertNotice(SqlSession session, Notice m) {
		return session.insert("notice.insertNotice",m);
	}

	@Override
	public int deleteNotice(SqlSession session, int no) {
		return session.delete("notice.deleteNotice",no);
	}

	@Override
	public int noticeSaveFile(SqlSession session, NoticeFile file) {
		
		return session.insert("notice.insertFile",file);
	}

	@Override
	public Notice noticeView(SqlSession session, int no) {
		
		return session.selectOne("notice.noticeView",no);
	}

	@Override
	public int deleteNoticeFile(SqlSession session, String fileName) {
		return session.delete("notice.deleteNoticeFile",fileName);
	}

}
