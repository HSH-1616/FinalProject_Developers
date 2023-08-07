package com.dev.touris.model.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dev.touris.model.vo.Touris;

@Repository
public class TourisDetailDaoImpl implements TourisDetailDao {
	
	
	
	
	@Override
	public Touris selectById(SqlSession session, String id) {
		return session.selectOne("tourisDetail.selectById",id); 
	}

	@Override
	public void insertDetail(SqlSession session, Map param) {
		session.update("tourisDetail.insertDetail",param);
	}

	@Override
	public void insertImage(SqlSession session, Map param) {
		session.insert("tourisDetail.insertImage",param);
		
	}
	
	
}
