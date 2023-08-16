package com.dev.touris.model.service;

import java.util.List;
import java.util.Map;

import com.dev.touris.model.vo.Touris;
import com.dev.touris.model.vo.TourisArea;
import com.dev.touris.model.vo.TourisMember;
import com.dev.touris.model.vo.TourisRoute;

public interface TourisService {
	List<TourisArea> tourisMainSelectMapPage();
	List<TourisArea> selectTourisArea(String areaName);
	TourisArea selecttourisarealist(String areaEng);
	List<Touris> selecttourislist(String areaEng);
	List<Touris> searchtouris(Map<String, Object> param);
	int inserttourismember(int loginmemberid, String tustartDate, String tuendDate, String registrationDate, List<Map> tourismemberdata);
	int myPageTourisRouteCount();
	List<TourisMember> myPageTourisRoute(int loginmemberid, Map params);
	List<TourisMember> myPageTourisRouteList(int loginmemberid);
	int tourisListCount();
	List<Touris> tourislist(Map<String,Object> param);
	
	
}
