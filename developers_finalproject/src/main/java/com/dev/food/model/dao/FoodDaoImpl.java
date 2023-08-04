package com.dev.food.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dev.food.model.dto.Food;
import com.dev.food.model.dto.FoodPhotoTemp;
import com.dev.food.model.dto.FoodTemp;

@Repository
public class FoodDaoImpl implements FoodDao {

	@Override
	public List<Food> selectPage(SqlSession session, int cPage, int numPerpage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertFood(SqlSession session, FoodTemp f) {
		return session.insert("food.insertFood", f);
	}
	@Override
	public int insertFoodPhoto(SqlSession session, FoodPhotoTemp fp) {
		return session.insert("food.insertFoodPhoto", fp);
	}
	
	@Override
	public int updateFood(SqlSession session, FoodTemp f) {
		return session.update("food.updateFood",f);
	}
	
	@Override
	public int mergeFood(SqlSession session) {
		return session.insert("food.mergeFood");
		//merge한 이후엔 temp테이블 지우기
	}
	@Override
	public int mergeFoodPhoto(SqlSession session) {
		return session.insert("food.mergeFoodPhoto");
		//merge한 이후엔 temp테이블 지우기
	}

	@Override
	public List<Food> selectFoodAll(SqlSession session, Map<String, Object> param) {
		
		int cPage=(int)param.get("cPage");
		int numPerpage=(int)param.get("numPerpage");
		RowBounds rb=new RowBounds((cPage-1)*numPerpage,numPerpage);
		
		return session.selectList("food.selectFoodAll",null,rb);
	}
	
	@Override
	public List<Food> selectFoodAllTest(SqlSession session) {
		return session.selectList("food.selectFoodAllTest");
	}
	
	@Override
	public List<Food> selectFoodByFoodNo(SqlSession session,int foodNo) {
		return session.selectList("food.selectFoodByFoodNo",foodNo);
	}

	@Override
	public int selectFoodCount(SqlSession session) {
		// TODO Auto-generated method stub
		return session.selectOne("food.selectFoodCount");
	}

	@Override
	public Food selectFoodByNo(SqlSession session, int no) {
		// TODO Auto-generated method stub
		return session.selectOne("food.selectFoodByNo",no);
	}

	@Override
	public int searchByFoodNo(SqlSession session,int foodNo) {
		return session.selectOne("food.searchByFoodNo",foodNo);
	}

	@Override
	public boolean searchByBoolean(SqlSession session) {
		return session.selectOne("food.searchByBoolean");
	}
	
}
