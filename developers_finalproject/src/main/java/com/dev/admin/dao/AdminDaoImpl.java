package com.dev.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dev.admin.model.dto.Admin;
import com.dev.member.model.dto.Black;
import com.dev.member.model.dto.Member;
import com.dev.touris.model.vo.Touris;

@Repository
public class AdminDaoImpl implements AdminDao {

	@Override
	public Admin adminLogin(SqlSession session,Map param) {
		return session.selectOne("admin.adminLogin",param);
	}
	@Override
	public List<Member> selectMemberAll(SqlSession session,Map param) {
		int cPage=(int)param.get("cPage");
		int numPerpage=(int)param.get("numPerpage");
		RowBounds rb=new RowBounds((cPage-1)*numPerpage,numPerpage);
		return session.selectList("admin.selectMemberAll",null,rb);
	}
	@Override
	public int selectMemberAllCount(SqlSession session) {
		return session.selectOne("admin.selectMemberAllCount");
	}
	@Override
	public void insertBlack(SqlSession session, Map param) {
		session.insert("admin.insertBlack",param);
	}
	@Override
	public void updateBlack(SqlSession session, Map param) {
		session.update("admin.updateBlack",param);
	}
	@Override
	public List<Black> selectBlackAll(SqlSession session) {
		return session.selectList("admin.selectBlackAll");
	}
	@Override
	public void blackClear(SqlSession session, Map param) {
		session.delete("admin.blackClear", param);
	}
	@Override
	public void updateClear(SqlSession session, Map param) {
		session.update("admin.updateClear",param);
	}
	@Override
	public List<Member> selectBySocial(SqlSession session, Map param) {
		int cPage=(int)param.get("cPage");
		int numPerpage=(int)param.get("numPerpage");
		RowBounds rb=new RowBounds((cPage-1)*numPerpage,numPerpage);
		return session.selectList("admin.selectBySocial",param,rb);
	}
	@Override
	public int selectBySocialCount(SqlSession session,String memberCategory) {
		return session.selectOne("admin.selectBySocialCount", memberCategory);
	}
	@Override
	public List<Touris> selectTourisAll(SqlSession session, Map param) {
		int cPage=(int)param.get("cPage");
		int numPerpage=(int)param.get("numPerpage");
		RowBounds rb=new RowBounds((cPage-1)*numPerpage,numPerpage);
		return session.selectList("admin.selectTourisAll", null, rb);
	}
	@Override
	public int selectTourisAllCount(SqlSession session) {
		return session.selectOne("admin.selectTourisAllCount");
	}
	@Override
	public List<Touris> searchTouris(SqlSession session, Map param) {
		int cPage=(int)param.get("cPage");
		int numPerpage=(int)param.get("numPerpage");
		RowBounds rb=new RowBounds((cPage-1)*numPerpage,numPerpage);
		return session.selectList("admin.searchTouris",param,rb);
	}
	@Override
	public int searchTourisCount(SqlSession session, Map param) {
		return session.selectOne("admin.searchTourisCount",param);
	}
	@Override
	public List<Touris> selectBytourisAreaId(SqlSession session, Map param) {
		int cPage=(int)param.get("cPage");
		int numPerpage=(int)param.get("numPerpage");
		RowBounds rb=new RowBounds((cPage-1)*numPerpage,numPerpage);
		return session.selectList("admin.selectBytourisAreaId",param,rb);
	}
	@Override
	public int selectBytourisAreaIdCount(SqlSession session, Map param) {
		return session.selectOne("admin.selectBytourisAreaIdCount", param);
	}
	
	
}
