package com.dev.touris.model.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.dev.member.model.dto.Member;
import com.dev.touris.model.dao.TourisDetailDao;
import com.dev.touris.model.vo.Touris;
import com.dev.touris.model.vo.TourisTemp;

@Service
public class TourisDetailServiceImpl implements TourisDetailService {
	
	private SqlSession session;
	private TourisDetailDao dao;
	
	
	public TourisDetailServiceImpl(SqlSession session, TourisDetailDao dao) {
		this.session=session;
		this.dao=dao;
	}
	
	@Override
	public Touris selectById(String id) {
		return dao.selectById(session,id);
	}

	@Override
	public void insertDetail(Map param) {
		 dao.insertDetail(session,param);
	}

	@Override
	public void insertImage(Map param) {
		dao.insertImage(session,param);
		
	}

	@Override
	public void insertHeart(Map param) {
		dao.insertHeart(session,param);
		
	}

	@Override
	public void deleteHeart(Map param) {
		dao.deleteHeart(session,param);
	}

	@Override
	public Member selectByIdforMember(Map param) {
		return dao.selectByIdforMember(session,param);
	}

	@Override
	public void insertTourisTemp(TourisTemp touris) {
		dao.insertTourisTemp(session, touris);
	}

	@Override
	public void deleteTourisTemp() {
		dao.deleteTourisTemp(session);
		
	}
	
	
	
	
	
	
}
