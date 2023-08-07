package com.dev.community.model.dao;

import java.util.List;
import java.util.Map;



import org.apache.ibatis.session.SqlSession;


import com.dev.community.model.dto.Community;
import com.dev.community.model.dto.CommunityFile;

public interface CommunityDao {

	List<Community> communityList(SqlSession session, Map<String, Object> paging);
	Community communityView(SqlSession session,int no);
	int insertCommunity(SqlSession session,Community m);
	int communitySaveFile(SqlSession session,CommunityFile file);
	int communityCount(SqlSession session);
	int deleteCommunityFile(SqlSession session,String fileName);
}
