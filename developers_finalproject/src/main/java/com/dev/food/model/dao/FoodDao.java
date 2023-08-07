package com.dev.food.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.dev.food.model.dto.Food;

import com.dev.food.model.dto.FoodPhotoTemp;
import com.dev.food.model.dto.FoodTemp;

public interface FoodDao {
	
	int insertFood(SqlSession session, Food f);
	List<Food> selectPage(SqlSession session, int cPage, int numPerpage);

	int insertFood(SqlSession session, FoodTemp f);
	int insertFoodPhoto(SqlSession session, FoodPhotoTemp fp);
	
	int updateFood(SqlSession session, FoodTemp f);
	
	int mergeFood(SqlSession session);
	int mergeFoodPhoto(SqlSession session);
	
	void deleteFoodTemp(SqlSession session);
	void deleteFoodPhotoTemp(SqlSession session);
	
	List<Food> selectFoodAll(SqlSession session, Map<String,Object> paging);
	
	List<Food> selectFoodAllTest(SqlSession session,int count);
	List<Food> selectFoodByFoodNo(SqlSession session,int foodNo);
	
	int selectFoodCount(SqlSession session);
	
	Food selectFoodByNo(SqlSession session, int no);
	
	List<Food> searchFood(SqlSession session, Map<String, Object> params,Map<String,Object> paging);

	/*
	 * List<Food> getSortedFoods(SqlSession session, String sortFilter, int cPage,
	 * int numPerpage);
	 */
	

	String searchByFoodNo(SqlSession session,int foodNo);
}
