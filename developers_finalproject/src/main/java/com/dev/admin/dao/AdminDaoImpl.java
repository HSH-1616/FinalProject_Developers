package com.dev.admin.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dev.admin.model.dto.Admin;

@Repository
public class AdminDaoImpl implements AdminDao {

	@Override
	public Admin adminLogin(SqlSession session,Map param) {
		return session.selectOne("admin.adminLogin",param);
	}
}
