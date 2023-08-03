package com.dev.touris.model.service;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.dev.touris.model.dao.TourisDao;
import com.dev.touris.model.vo.Touris;
import com.dev.touris.model.vo.TourisArea;

@Service
public class TourisServiceImpl implements TourisService {
	
	private TourisDao dao;
	private SqlSessionTemplate session;
	
	
	public TourisServiceImpl(TourisDao dao, SqlSessionTemplate session) {
		this.dao=dao;
		this.session=session;
	}
	@Override
	public List<TourisArea> tourisMainSelectMapPage() {
		return dao.tourisMainSelectMapPage(session);
	}
	@Override
	public List<TourisArea> selectTourisArea(String areaName) {
		return dao.selectTourisArea(session, areaName);
	}
	@Override
	public List<Touris> selecttourislist(String areaId) {
		return dao.selecttourislist(session, areaId);
	}
	@Override
	public TourisArea selecttourisarealist(String areaId) {
		return dao.selecttourisarea(session, areaId);
	}
	
	
}
