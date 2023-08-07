package com.dev.community.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dev.community.model.dto.Community;
import com.dev.community.model.dto.CommunityFile;

@Repository
public class CommunityDaoImpl implements CommunityDao {

	
	@Override
	public List<Community> communityList(SqlSession session, Map<String, Object> paging) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Community communityView(SqlSession session, int no) {
		// TODO Auto-generated method stub
		return null;
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

	

}
