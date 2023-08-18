package com.dev.touris.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.dev.touris.model.vo.Touris;
import com.dev.touris.model.vo.TourisArea;
import com.dev.touris.model.vo.TourisMember;

public interface TourisDao {
	//메인페이지에서 전국지역선택하는 페이지
	List<TourisArea> tourisMainSelectMapPage(SqlSessionTemplate session);
	//메인페이지 지역별 소개 모달창 띄어주기
	List<TourisArea> selectTourisArea(SqlSessionTemplate session, String areaName);
	
	//여행경로 대표지역에 대한것들 뿌려주는
	TourisArea selecttourisarea(SqlSessionTemplate session, String areaId);
	//여행경로 관광지 보여주는 페이지
	List<Touris> selecttourislist(SqlSessionTemplate session, String areaId);
	//관광지 검색어 검색했을때 뿌려주는
	List<Touris> searchtouris(SqlSessionTemplate session, Map<String, Object> param);
	int inserttourisroute(SqlSessionTemplate session, Map routedata);
	int inserttourismember(SqlSessionTemplate session, Map tourismemberdata);
	int myPageTourisRouteCount(SqlSessionTemplate session, int loginmemberid);
	List<TourisMember> myPageTourisRoute(SqlSessionTemplate session, int loginmemberid, Map param);
	List<TourisMember> myPageTourisRouteList(SqlSessionTemplate session, int loginmemberid);
	int tourisListCount(SqlSessionTemplate session);
	List<Touris> tourislist(SqlSessionTemplate session, Map<String,Object> param);
	int deleteroute(SqlSessionTemplate session, int tuId);
}
