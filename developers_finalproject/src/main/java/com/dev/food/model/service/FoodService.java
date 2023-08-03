package com.dev.food.model.service;

import java.util.List;
import java.util.Map;

import com.dev.food.model.dto.Food;

public interface FoodService {

	int insertFood(Food f);
	
	List<Food> selectFoodAll (Map<String,Object> param);
	
	int selectFoodCount();
	
	Food selectFoodById(int no);

	List<Food> getSortedFoods(String sortFilter,int cPage, int numPerpage);

}
