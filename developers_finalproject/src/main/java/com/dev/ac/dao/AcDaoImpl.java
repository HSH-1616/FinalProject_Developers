package com.dev.ac.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.dev.ac.dto.Accommodation;

@Repository
public class AcDaoImpl implements AcDao {

	@Override
	public List<Accommodation> acListAll(SqlSessionTemplate session) {
		return session.selectList("accommodation.acListAll");
	}

}
