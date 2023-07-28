package com.dev.admin.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.dev.admin.model.dto.Admin;

public interface AdminDao {
	
	Admin adminLogin(SqlSession session,Map param);

}
