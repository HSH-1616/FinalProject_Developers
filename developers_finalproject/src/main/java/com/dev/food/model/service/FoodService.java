package com.dev.food.model.service;

import java.util.List;
import java.util.Map;

import com.dev.food.model.dto.Food;
import com.dev.food.model.dto.FoodPhoto;
import com.dev.food.model.dto.FoodPhotoTemp;
import com.dev.food.model.dto.FoodTemp;

public interface FoodService {

	void insertFood(FoodTemp food,FoodPhotoTemp fp) throws Exception;
	
	void updateFood(FoodTemp food) throws Exception;
	
	void insertFoodPhoto(FoodPhotoTemp fp) throws Exception;
	
	void mergeFood();
	
	void mergeFoodPhoto();
	
	void deleteFoodTemp();
	
	void deleteFoodPhotoTemp();
	
	List<Food> selectFoodAll (Map<String,Object> paging);
	
	List<Food> selectFoodAllTest ();
	
	List<Food> selectFoodByFoodNo (int foodNo);
	
	int selectFoodCount();
	
	Food selectFoodByNo(int no);
	
	String searchByFoodNo(int foodNo);

	/* List<Food> getSortedFoods(String sortFilter,int cPage, int numPerpage); */

	List<Food> searchFood(Map<String, Object> params,Map<String, Object>paging);
	
}
