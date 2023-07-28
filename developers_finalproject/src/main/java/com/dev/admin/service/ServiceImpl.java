package com.dev.admin.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.dev.admin.dao.AdminDao;
import com.dev.admin.model.dto.Admin;

@Service
public class ServiceImpl implements AdminService {
	
	private AdminDao dao;
	
	private SqlSession session;

	public ServiceImpl(AdminDao dao, SqlSession session) {
		this.dao=dao;
		this.session=session;
	}
	
	@Override
	public Admin adminLogin(Map param) {
		return dao.adminLogin(session,param);
		
	}
}
