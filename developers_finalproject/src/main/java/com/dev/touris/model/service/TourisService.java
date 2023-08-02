package com.dev.touris.model.service;

import java.util.List;

import com.dev.touris.model.vo.Touris;
import com.dev.touris.model.vo.TourisArea;

public interface TourisService {
	List<TourisArea> tourisMainSelectMapPage();
	List<TourisArea> selectTourisArea(String areaName);
	List<Touris> selecttourislist(String areaId);
}
