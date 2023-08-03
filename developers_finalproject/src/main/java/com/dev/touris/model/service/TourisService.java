package com.dev.touris.model.service;

import java.util.List;
import java.util.Map;

import com.dev.touris.model.vo.Touris;
import com.dev.touris.model.vo.TourisArea;

public interface TourisService {
	List<TourisArea> tourisMainSelectMapPage();
	List<TourisArea> selectTourisArea(String areaName);
	TourisArea selecttourisarealist(String areaId);
	List<Touris> selecttourislist(String areaId);
}
