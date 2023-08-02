package com.dev.ac.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.dev.ac.dto.Accommodation;

public interface AcDao {
	
	List<Accommodation> acListAll(SqlSessionTemplate session);
	
	List<Accommodation> acSearchAll(SqlSessionTemplate session,Map param);
	
	Accommodation acDetail(SqlSessionTemplate session, int no);
	
	Accommodation acPay(SqlSessionTemplate session, int no);
}
