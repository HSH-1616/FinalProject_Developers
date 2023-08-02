package com.dev.food.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dev.food.model.dto.Food;

@Repository
public class FoodDaoImpl implements FoodDao {

	@Override
	public List<Food> selectPage(SqlSession session, int cPage, int numPerpage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertFood(SqlSession session, Food f) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Food> selectFoodAll(SqlSession session, Map<String, Object> param) {
		
		int cPage=(int)param.get("cPage");
		int numPerpage=(int)param.get("numPerpage");
		RowBounds rb=new RowBounds((cPage-1)*numPerpage,numPerpage);
		
		return session.selectList("food.selectFoodAll",null,rb);
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

	

	
	
}
