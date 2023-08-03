package com.dev.food.model.service;

import java.util.List;
import java.util.Map;

import com.dev.food.model.dto.Food;
import com.dev.food.model.dto.FoodPhoto;

public interface FoodService {

	void insertFood(Food f,FoodPhoto fp) throws Exception;
	
	List<Food> selectFoodAll (Map<String,Object> param);
	
	List<Food> selectFoodAllTest ();
	
	int selectFoodCount();
	
	Food selectFoodById(int no);

}
