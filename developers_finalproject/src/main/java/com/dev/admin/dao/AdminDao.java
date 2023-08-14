package com.dev.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.dev.admin.model.dto.Admin;
import com.dev.food.model.dto.Food;
import com.dev.member.model.dto.Black;
import com.dev.member.model.dto.Member;
import com.dev.touris.model.vo.Touris;

public interface AdminDao {
	
	Admin adminLogin(SqlSession session,Map param);
	List<Member> selectMemberAll(SqlSession session, Map param);
	int selectMemberAllCount(SqlSession session);
	void insertBlack(SqlSession session, Map param);
	void updateBlack(SqlSession session, Map param);
	List<Black> selectBlackAll(SqlSession session);
	void blackClear(SqlSession session, Map param);
	void updateClear(SqlSession session, Map param);
	List<Member> selectBySocial(SqlSession session, Map param);
	int selectBySocialCount(SqlSession session,String memberCategory);
	List<Touris> selectTourisAll(SqlSession session, Map param);
	int selectTourisAllCount(SqlSession session);
	List<Touris> searchTouris(SqlSession session, Map param);
	int searchTourisCount(SqlSession session, Map param);
	List<Touris> selectBytourisAreaId(SqlSession session, Map param);
	int selectBytourisAreaIdCount(SqlSession session, Map param);
	int selectTourisTempAllCount(SqlSession session);
	void tourisUpdate(SqlSession session);
	
//	======지환======
	List<Food> searchFood(SqlSession session, Map param);
	int selectFoodCount(SqlSession session);
}
