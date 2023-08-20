package com.dev.food.model.service;

import java.util.List;
import java.util.Map;

import com.dev.food.model.dto.Food;
import com.dev.food.model.dto.FoodBlackList;
import com.dev.food.model.dto.FoodHeart;
import com.dev.food.model.dto.FoodPhoto;
import com.dev.food.model.dto.FoodPhotoTemp;
import com.dev.food.model.dto.FoodReview;
import com.dev.food.model.dto.FoodReviewPhoto;
import com.dev.food.model.dto.FoodTemp;

public interface FoodService {

	int insertFood(FoodTemp food);
	
	int insertFoodPhoto(FoodPhotoTemp fp);

	int updateFood(FoodTemp food);
	
	int updateFoodOnNull(FoodTemp food);
	
	int insertFoodPhotoOnNull(FoodPhotoTemp fp);
	
	int updateFoodOnAdmin(Food food);
	
	int deleteFoodOnAdmin(int foodNo);
	
	void mergeFood();
	
	void mergeFoodPhoto();
	
	void deleteFoodTemp(int foodNo);
	
	void deleteFoodPhotoTemp(int foodNo);
	
	int deleteFoodTempAll();
	
	int deleteFoodPhotoTempAll();
	
	List<Food> selectFoodAll (Map<String,Object> param);
	
	List<Food> foodListTitle(Map<String,Object> param);
	
	List<Food> selectFoodAllTest (int count);
	
	List<Food> selectFoodByFoodNo (int foodNo);
	
	List<Food> selectFoodTempByFoodNo(int foodNo);
	
	int selectFoodCount();
	
	Food selectFoodByNo(int no);
	
	int selectFoodPhotoByNo(String fpName);
	
	String searchByFoodNo(int foodNo);
	
	int copyFoodtoFoodTemp(int foodNo);
	
	int copyFPtoFPTemp(int foodNo);

	List<Food> searchFood(Map<String, Object> params);
	
	FoodHeart getFoodById(String memberId);
	
	int insertHeart(Map param);
	
	int deleteHeart(Map param);
	
	int updateHeart(Map param);
	
	int cancleHeart(Map param);
	
	/* int toggleHeartAndGetCount(Map params); */

	/* list<food> getsortedfoods(string sortfilter,int cpage, int numperpage); */
	
	int insertFoodReview(FoodReview fr);
	
	int deleteFoodReview(int frNo);
	
	int deleteFoodReviewPhoto(int frNo);
	
	//List<FoodReview> selectFoodReviewByFoodNo(int foodNo);
	
	List<FoodReviewPhoto> selectFoodReviewPhotoByFoodNo(int frNo);
	
	int updateFoodReview(FoodReview fr);

	List<Food> foodHeartList(int memberId);
	
	//int updateReviewPhoto(FoodReviewPhoto rp);
	
	//List searchByRpNo(int frNo);
	
	int insertFoodBlackList(FoodBlackList fb);
	
	int selectFoodBlackListCount();
	
	List<Food> selectFoodReviewByFoodNo(int memberId);

	List<Food> addFood(Map param);
	
	List<Food> selectFoodReviewByFoodNo(int memberId, Map<String, Object> params);
	
	int selectFoodReviewByFoodNoCount(int memberId);
	
	String searchFoodNameByNo(int foodNo);
}
