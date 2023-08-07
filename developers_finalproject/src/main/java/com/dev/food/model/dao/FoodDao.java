package com.dev.food.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.dev.food.model.dto.Food;
import com.dev.notice.model.dto.Notice;

public interface FoodDao {
	
	int insertFood(SqlSession session, Food f);
	
	List<Food> selectFoodAll(SqlSession session, Map<String,Object> paging);
	
	int selectFoodCount(SqlSession session);
	
	Food selectFoodByNo(SqlSession session, int no);
	
	List<Food> searchFood(SqlSession session, Map<String, Object> params,Map<String,Object> paging);

	/*
	 * List<Food> getSortedFoods(SqlSession session, String sortFilter, int cPage,
	 * int numPerpage);
	 */
	

}
