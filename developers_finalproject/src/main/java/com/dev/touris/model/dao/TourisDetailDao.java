package com.dev.touris.model.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.dev.touris.model.vo.Touris;

public interface TourisDetailDao {
	
	Touris selectById(SqlSession session, String id);
	void insertDetail(SqlSession session, Map param);
	void insertImage(SqlSession session, Map param);
}
