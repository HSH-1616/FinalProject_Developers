package com.dev.touris.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.dev.touris.common.mapper.TourisMapper;
import com.dev.touris.model.vo.Touris;
import com.dev.touris.model.vo.TourisArea;
import com.dev.touris.model.vo.TourisMember;

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
	public List<Touris> selecttourislist(SqlSessionTemplate session, String areaEng) {
		return session.selectList("touris.selecttourislist", areaEng);
	}
	@Override
	public TourisArea selecttourisarea(SqlSessionTemplate session, String areaEng) {
		return session.selectOne("touris.selecttourisarealist", areaEng);
	}
	@Override
	public List<Touris> searchtouris(SqlSessionTemplate session, Map<String, Object> param) {
		return session.selectList("touris.searchtourislist", param);
	}
	@Override
	public int inserttourismember(SqlSessionTemplate session, Map tourismemberdata) {
		return session.insert("touris.inserttourismember", tourismemberdata);
	}
	@Override
	public int inserttourisroute(SqlSessionTemplate session, Map routedata) {
		return session.insert("touris.inserttourisroute", routedata);
	}
	@Override
	public int myPageTourisRouteCount(SqlSessionTemplate session) {
		return session.selectOne("touris.myPageTourisRouteCount");
	}
	@Override
	public List<TourisMember> myPageTourisRoute(SqlSessionTemplate session, int loginmemberid, Map param) {
		int cPage=(int)param.get("cPage");
		int numPerpage=(int)param.get("numPerpage");
		return session.selectList("touris.myPageTourisRoute", loginmemberid, new RowBounds((cPage-1)*numPerpage, numPerpage));
	}
	@Override
	public List<TourisMember> myPageTourisRouteList(SqlSessionTemplate session, int loginmemberid) {
		return session.selectList("touris.myPageTourisRouteList", loginmemberid);
	}
	@Override
	public List<Touris> tourislist(SqlSessionTemplate session, Map<String,Object> param) {
		int cPage=(int)param.get("cPage");
		int numPerpage=(int)param.get("numPerpage");
		RowBounds rb=new RowBounds((cPage-1)*numPerpage,numPerpage);
		return session.selectList("touris.tourisList", null, rb);
	}
	@Override
	public int tourisListCount(SqlSessionTemplate session) {
		return session.selectOne("touris.tourisListCount");
	}
	

	
}
