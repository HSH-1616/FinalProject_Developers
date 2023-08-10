package com.dev.admin.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.dev.admin.dao.AdminDao;
import com.dev.admin.model.dto.Admin;
import com.dev.member.model.dto.Black;
import com.dev.member.model.dto.Member;
import com.dev.touris.model.vo.Touris;

@Service
public class AdminServiceImpl implements AdminService {
	
	private AdminDao dao;
	private SqlSession session;

	public AdminServiceImpl(AdminDao dao, SqlSession session) {
		this.dao=dao;
		this.session=session;
	}
	@Override
	public Admin adminLogin(Map param) {
		return dao.adminLogin(session,param);
	}
	@Override
	public List<Member> selectMemberAll(Map param) {
		return dao.selectMemberAll(session,param);
	}
	@Override
	public int selectMemberAllCount() {
		return dao.selectMemberAllCount(session);
	}
	@Override
	public void insertBlack(Map param) {
		dao.insertBlack(session,param);
	}
	@Override
	public void updateBlack(Map param) {
		dao.updateBlack(session,param);
	}
	@Override
	public List<Black> selectBlackAll() {
		return dao.selectBlackAll(session);
	}
	@Override
	public void blackClear(Map param) {
		dao.blackClear(session,param);
	}
	@Override
	public void updateClear(Map param) {
		dao.updateClear(session,param);
	}
	@Override
	public List<Member> selectBySocial(Map param) {
		return dao.selectBySocial(session, param);
	}
	@Override
	public int selectBySocialCount(String memberCategory) {
		return dao.selectBySocialCount(session,memberCategory);
	}
	@Override
	public List<Touris> selectTourisAll(Map param) {
		return dao.selectTourisAll(session,param);
	}
	@Override
	public int selectTourisAllCount() {
		return dao.selectTourisAllCount(session);
	}
	@Override
	public List<Touris> searchTouris(Map param) {
		return dao.searchTouris(session,param);
	}
	@Override
	public int searchTourisCount(Map param) {
		return dao.searchTourisCount(session,param);
	}
	@Override
	public List<Touris> selectBytourisAreaId(Map param) {
		return dao.selectBytourisAreaId(session,param);
	}
	@Override
	public int selectBytourisAreaIdCount(Map param) {
		return dao.selectBytourisAreaIdCount(session,param);
	}
	
	
	
	
	
}
