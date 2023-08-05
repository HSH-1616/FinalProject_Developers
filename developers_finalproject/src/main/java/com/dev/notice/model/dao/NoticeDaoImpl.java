package com.dev.notice.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dev.notice.model.dto.Notice;
import com.dev.notice.model.dto.NoticeFile;

@Repository
public class NoticeDaoImpl implements NoticeDao {

	
	
	@Override
	public List<Notice> noticeList(SqlSession session,Map<String, Object> paging) {
		int cPage=(int)paging.get("cPage");
		int numPerpage=(int)paging.get("numPerpage");
		return session.selectList("notice.noticeList",null,new RowBounds((cPage-1)*numPerpage, numPerpage));
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

	@Override
	public int updateNotice(SqlSession session, Notice n) {
		return session.update("notice.updateNotice",n);
	}

	@Override
	public int noticeViewCountUp(SqlSession session, int no) {
		return session.update("notice.noticeViewCountUp",no);
	}

	@Override
	public List<Notice> searchNotice(SqlSession session, Map<String, Object> params,Map<String, Object> paging) {
		int cPage=(int)paging.get("cPage");
		int numPerpage=(int)paging.get("numPerpage");
		return session.selectList("notice.noticeSearch",params,new RowBounds((cPage-1)*numPerpage, numPerpage));
	}

	@Override
	public int noticeCount(SqlSession session) {
		
		return session.selectOne("notice.noticeListCount");
	}

}
