package com.dev.community.model.dao;

import java.util.List;
import java.util.Map;



import org.apache.ibatis.session.SqlSession;


import com.dev.community.model.dto.Community;
import com.dev.community.model.dto.CommunityFile;
import com.dev.community.model.dto.Reply;

public interface CommunityDao {

	List<Community> communityList(SqlSession session, Map<String, Object> paging);
	Community communityView(SqlSession session,int no);
	int insertCommunity(SqlSession session,Community m);
	int communitySaveFile(SqlSession session,CommunityFile file);
	int communityCount(SqlSession session);
	int deleteCommunityFile(SqlSession session,String fileName);
	int insertLike(SqlSession session, Map<String, Object> like);
	int deleteLike(SqlSession session, Map<String, Object> like);
	Map<String, Object> selectLike(SqlSession session, Map<String, Object> like);
	int likeCountUp(SqlSession session, int no);
	int likeCountDown(SqlSession session, int no);
	int insertReply(SqlSession session,Reply reply);
	int insertReplies(SqlSession session,Reply replies);
	List<Reply> replyList(SqlSession session,int communityNo);
	int updateReply(SqlSession session,Reply r);
	int deleteReply(SqlSession session,int replyNo);
	int deleteReplies(SqlSession session, int replyNo);
}
