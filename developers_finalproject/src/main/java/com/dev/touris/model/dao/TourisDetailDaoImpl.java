package com.dev.touris.model.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dev.member.model.dto.Member;
import com.dev.touris.model.vo.Touris;
import com.dev.touris.model.vo.TourisTemp;

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

	@Override
	public void insertHeart(SqlSession session, Map param) {
		session.insert("tourisDetail.insertHeart",param);
	}

	@Override
	public void deleteHeart(SqlSession session, Map param) {
		session.delete("tourisDetail.deleteHeart",param);
	}

	@Override
	public Member selectByIdforMember(SqlSession session, Map param) {
		return session.selectOne("tourisDetail.selectByIdforMember",param);
	}

	@Override
	public void insertTourisTemp(SqlSession session, TourisTemp touris) {
		session.insert("tourisDetail.insertTourisTemp",touris);
	}

	@Override
	public void deleteTourisTemp(SqlSession session) {
		session.delete("tourisDetail.deleteTourisTemp");
		
	}
	
	
	
}
