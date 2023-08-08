package com.dev.community.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dev.community.model.dto.Community;
import com.dev.community.model.dto.CommunityFile;

@Repository
public class CommunityDaoImpl implements CommunityDao {

	
	@Override
	public List<Community> communityList(SqlSession session, Map<String, Object> paging) {
		int cPage=(int)paging.get("cPage");
		int numPerpage=(int)paging.get("numPerpage");
		System.out.println(cPage);
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

	

}
