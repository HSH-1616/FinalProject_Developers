package com.dev.food.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.dev.food.model.dto.Food;
import com.dev.food.model.dto.FoodPhoto;

public interface FoodDao {
	
	List<Food> selectPage(SqlSession session, int cPage, int numPerpage);

	int insertFood(SqlSession session, Food f);
	
	int insertFoodPhoto(SqlSession session, FoodPhoto fp);
	
	List<Food> selectFoodAll(SqlSession session, Map<String,Object> param);
	
	List<Food> selectFoodAllTest(SqlSession session);
	
	int selectFoodCount(SqlSession session);
	
	Food selectFoodByNo(SqlSession session, int no);
}
