package com.dev.touris.model.service;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.dev.touris.model.dao.TourisDao;
import com.dev.touris.model.vo.Touris;
import com.dev.touris.model.vo.TourisArea;
import com.dev.touris.model.vo.TourisMember;

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
	public List<Touris> selecttourislist(String areaEng) {
		return dao.selecttourislist(session, areaEng);
	}
	@Override
	public TourisArea selecttourisarealist(String areaEng) {
		return dao.selecttourisarea(session, areaEng);
	}
	@Override
	public List<Touris> searchtouris(Map<String, Object> param) {
		return dao.searchtouris(session, param);
	}

	@Override
	public int inserttourismember(int loginmemberid, String tustartDate, String tuendDate, String registrationDate, List<Map> routedata) {
		int insertedCount = 0;
		Map tourismember = new HashMap<>();
		tourismember.put("loginmemberid", loginmemberid);
		tourismember.put("tustartDate", tustartDate);
		tourismember.put("tuendDate", tuendDate);
		tourismember.put("registrationDate", registrationDate);
		int result = dao.inserttourismember(session, tourismember);
		
		if (result > 0) {
			// 각 여행 내용 저장
			for (Map t : routedata) {
				Integer tuid = (Integer)tourismember.get("tuId");
				Integer tourisrouteLevel = Integer.parseInt((String) t.get("tourisrouteLevel"));
				String tourisesId = (String) t.get("tourisesId");
				String tourisrouteDate = (String) t.get("tourisrouteDate");
				
				Map tourismaproute = new HashMap<>();
				
				tourismaproute.put("tourisrouteLevel", tourisrouteLevel);
				tourismaproute.put("tourisesId", tourisesId);
				tourismaproute.put("tourisrouteDate", tourisrouteDate);
				tourismaproute.put("tuid", tuid);
				int resultintsert = dao.inserttourisroute(session, tourismaproute);
				insertedCount += resultintsert;
			}
		}
		
		return insertedCount;
	}
	@Override
	public int myPageTourisRouteCount() {
		return dao.myPageTourisRouteCount(session);
	}
	@Override
	public List<TourisMember> myPageTourisRoute(int loginmemberid, Map params) {
		return dao.myPageTourisRoute(session, loginmemberid, params);
	}
	@Override
	public List<TourisMember> myPageTourisRouteList(int loginmemberid) {
		return dao.myPageTourisRouteList(session, loginmemberid);
	}
	@Override
	public List<Touris> tourislist(Map<String,Object> param) {
		return dao.tourislist(session, param);
	}
	@Override
	public int tourisListCount() {
		return dao.tourisListCount(session);
	}
	
//	@Override
//	public int inserttourisroute(List<Map> routedata) {
//		int insertedCount = 0;
//
//		for (Map t : routedata) {
//			Map tourismaproute = new HashMap<>();
//			Integer tourisrouteLevel = Integer.parseInt((String) t.get("tourisrouteLevel"));
//			String tourisesId = (String) t.get("tourisesId");
//			String tourisrouteDate = (String) t.get("tourisrouteDate");
//			tourismaproute.put("tourisrouteLevel", tourisrouteLevel);
//			tourismaproute.put("tourisesId", tourisesId);
//			tourismaproute.put("tourisrouteDate", tourisrouteDate);
//			System.out.println("서비스 부분 : " + tourismaproute);
//			int result = dao.inserttourisroute(session, tourismaproute);
//			insertedCount += result;
//		}
//
//		return insertedCount;
//	}
	
}
