package com.dev.touris.model.service;

import java.util.List;
import java.util.Map;

import com.dev.touris.model.vo.Touris;
import com.dev.touris.model.vo.TourisArea;

public interface TourisService {
	List<TourisArea> tourisMainSelectMapPage();
	List<TourisArea> selectTourisArea(String areaName);
	TourisArea selecttourisarealist(String areaEng);
	List<Touris> selecttourislist(String areaEng);
	List<Touris> searchtouris(Map<String, Object> param);
	int inserttourismember(int loginmemberid, String tustartDate, String tuendDate, List<Map> tourismemberdata);
}
