package com.dev.touris.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.dev.touris.common.mapper.TourisMapper;
import com.dev.touris.model.vo.Touris;
import com.dev.touris.model.vo.TourisArea;

@Repository
public class TourisDaoImpl implements TourisDao {
	private TourisMapper mapper;
	private SqlSessionTemplate session;

	public TourisDaoImpl(TourisMapper mapper, SqlSessionTemplate session) {
		this.mapper=mapper;
		this.session=session;
	}
	@Override
	public List<TourisArea> tourisMainSelectMapPage(SqlSessionTemplate session) {
		return session.selectList("touris.tourisarealist");
//		return mapper.tourisMainSelectMapPage();
	}
	@Override
	public List<TourisArea> selectTourisArea(SqlSessionTemplate session, String areaName) {
		return session.selectList("touris.selecttourisarea", areaName);
	}
	@Override
	public List<Touris> selecttourislist(SqlSessionTemplate session, String areaId) {
		return session.selectList("touris.selecttourislist", areaId);
	}
	@Override
	public TourisArea selecttourisarea(SqlSessionTemplate session, String areaId) {
		return session.selectOne("touris.selecttourisarealist", areaId);
	}

	
}
