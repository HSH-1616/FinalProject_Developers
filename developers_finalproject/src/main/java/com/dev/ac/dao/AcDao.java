package com.dev.ac.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.dev.ac.dto.Accommodation;

public interface AcDao {
	
	List<Accommodation> acListAll(SqlSessionTemplate session);
}
