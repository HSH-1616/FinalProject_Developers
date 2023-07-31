package com.dev.ac.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.dev.ac.dto.Accommodation;

@Repository
public class AcDaoImpl implements AcDao {

	@Override
	public List<Accommodation> acListAll(SqlSessionTemplate session) {
		return session.selectList("accommodation.acListAll");
	}

	@Override
	public List<Accommodation> acSearchAll(SqlSessionTemplate session, Map param) {
		return session.selectList("accommodation.searchListAll",param);
	}

	@Override
	public Accommodation acDetail(SqlSessionTemplate session, int no) {
		return session.selectOne("accommodation.acDetail",no);
	}

}
