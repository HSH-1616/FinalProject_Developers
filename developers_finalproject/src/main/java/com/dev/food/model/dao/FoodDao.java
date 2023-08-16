package com.dev.food.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.dev.food.model.dto.Food;
import com.dev.food.model.dto.FoodHeart;
import com.dev.food.model.dto.FoodPhotoTemp;
import com.dev.food.model.dto.FoodReview;
import com.dev.food.model.dto.FoodReviewPhoto;
import com.dev.food.model.dto.FoodTemp;

public interface FoodDao {
	
	int insertFood(SqlSession session, Food f);
	
	List<Food> selectPage(SqlSession session, int cPage, int numPerpage);

	int insertFood(SqlSession session, FoodTemp f);
	
	int insertFoodPhoto(SqlSession session, FoodPhotoTemp fp);
	
	int updateFood(SqlSession session, FoodTemp f);
	
	int updateFoodOnAdmin(SqlSession session, Food food);
	
	int deleteFoodOnAdmin(SqlSession session, int foodNo);
	
	int mergeFood(SqlSession session);
	
	int mergeFoodPhoto(SqlSession session);
	
	void deleteFoodTemp(SqlSession session,int foodNo);
	void deleteFoodPhotoTemp(SqlSession session,int foodNo);
	
	List<Food> selectFoodAll(SqlSession session, Map<String,Object> param);
	
	List<Food> foodListTitle(SqlSession session, Map<String,Object> param);
	

	List<Food> selectFoodAllTest(SqlSession session,int count);
	
	List<Food> selectFoodByFoodNo(SqlSession session,int foodNo);
	
	int selectFoodCount(SqlSession session);
	
	Food selectFoodByNo(SqlSession session, int no);
	
	List<Food> searchFood(SqlSession session, Map<String, Object> params);

	String searchByFoodNo(SqlSession session,int foodNo);
	
	int insertFoodReview(SqlSession session,FoodReview fr);
	
	int insertFoodReviewPhoto(SqlSession session,FoodReviewPhoto rp);
	
	//List searchByRpNo(SqlSession session,int frNo);
	
	int updateFoodReview(SqlSession session,FoodReview fr);
	
	int updateFoodReviewPhoto(SqlSession session,FoodReviewPhoto rp);
	
	int deleteFoodReview(SqlSession session, int frNo);
	
	int searchFoodReivewPhoto(SqlSession session,int frNo);
	
	int deleteFoodReviewPhoto(SqlSession session, int frNo);
	
	//List<FoodReview> selectFoodReviewByFoodNo(SqlSession session, int foodNo);
	
	List<FoodReviewPhoto> selectFoodReviewPhotoByFoodNo(SqlSession session, int frNo);

	FoodHeart getFoodById(SqlSession session, String memberId);
	
	/*
	 * void incrementLikeCount(SqlSession session, String memberId, int phNo);
	 * 
	 * void decrementLikeCount(SqlSession session, String memberId, int phNo);
	 */
	
	List<Food> getFoodsSortedByTitle(SqlSession session, Map<String, Object> params, String sortType);

	boolean checkHeart(SqlSession session, Map params);
    
	void insertHeart(SqlSession session, Map params);
    
	void deleteHeart(SqlSession session, Map params);
    
	int getHeartCount(SqlSession session, Map params);
}
