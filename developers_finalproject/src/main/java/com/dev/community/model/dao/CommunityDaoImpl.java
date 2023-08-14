package com.dev.community.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dev.community.model.dto.Community;
import com.dev.community.model.dto.CommunityFile;
import com.dev.community.model.dto.Reply;

@Repository
public class CommunityDaoImpl implements CommunityDao {

	
	@Override
	public List<Community> communityList(SqlSession session, Map<String, Object> paging) {
		int cPage=(int)paging.get("cPage");
		int numPerpage=(int)paging.get("numPerpage");
		return session.selectList("community.communityList",null,new RowBounds((cPage-1)*numPerpage, numPerpage));
	}

	@Override
	public Community communityView(SqlSession session, int no) {
		
		return session.selectOne("community.communityView",no);
	}

	@Override
	public int insertCommunity(SqlSession session, Community m) {
		
		return session.insert("community.insertCommunity",m);
	}

	@Override
	public int communitySaveFile(SqlSession session, CommunityFile file) {
		
		return session.insert("community.insertCommunityFile",file);
	}

	@Override
	public int communityCount(SqlSession session) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteCommunityFile(SqlSession session, String fileName) {
		
		return session.delete("community.deleteCommunityFile",fileName);
	}

	@Override
	public int insertLike(SqlSession session, Map<String, Object> like) {
		
		return session.insert("community.insertLike",like);
	}

	@Override
	public int deleteLike(SqlSession session, Map<String, Object> like) {
		
		return session.delete("community.deleteLike",like);
	}

	@Override
	public Map<String, Object> selectLike(SqlSession session, Map<String, Object> like) {
		
		return session.selectOne("community.selectLike",like);
	}

	@Override
	public int likeCountUp(SqlSession session, int communityNo) {
		return session.update("community.likeCountUp",communityNo);
	}

	@Override
	public int likeCountDown(SqlSession session, int communityNo) {
		
		return session.update("community.likeCountDown",communityNo);
	}

	@Override
	public int insertReply(SqlSession session, Reply reply) {
		
		return session.insert("community.insertReply",reply);
	}

	@Override
	public int insertReplies(SqlSession session, Reply replies) {
		
		return session.insert("community.insertReplies",replies);
	}

	@Override
	public List<Reply> replyList(SqlSession session, int communityNo) {
		
		return session.selectList("community.replyList",communityNo);
	}

	@Override
	public int updateReply(SqlSession session, Reply r) {
		
		return session.update("community.updateReply",r);
	}

	@Override
	public int deleteReply(SqlSession session, int replyNo) {

		return session.update("community.deleteReply",replyNo);
	}

	@Override
	public int deleteReplies(SqlSession session, int replyNo) {
		
		return session.delete("community.deleteReplies",replyNo);
	}

	@Override
	public int updateCommunity(SqlSession session, Community c) {
		
		return session.update("community.updateCommunity",c);
	}

	@Override
	public int updateCommunityFile(SqlSession session, CommunityFile file) {
		
		return session.insert("community.updateCommunityFile",file);
	}

	@Override
	public List<String> selectCommunityFileList(SqlSession session, int communityNo) {
		
		return session.selectList("community.selectCommunityFiles",communityNo);
	}

	@Override
	public int deleteCommunity(SqlSession session, int communityNo) {
	
		return session.delete("community.deleteCommunity",communityNo);
	}

	

}
