package com.dev.food.model.service;

import java.util.List;
import java.util.Map;

import com.dev.food.model.dto.Food;
import com.dev.food.model.dto.FoodHeart;
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
	
	List<Food> selectFoodAll (Map<String,Object> param);
	
	List<Food> foodListTitle(Map<String,Object> param);
	
	List<Food> selectFoodAllTest ();
	
	List<Food> selectFoodByFoodNo (int foodNo);
	
	int selectFoodCount();
	
	Food selectFoodByNo(int no);
	
	String searchByFoodNo(int foodNo);

	List<Food> searchFood(Map<String, Object> params);
	
	FoodHeart getFoodById(String memberId);
	
	int insertHeart(Map param);
	
	int deleteHeart(Map param);
	
	List<FoodHeart> fdHeart(int no);
	
	/* int toggleHeartAndGetCount(Map params); */

	/* list<food> getsortedfoods(string sortfilter,int cpage, int numperpage); */
	
}
