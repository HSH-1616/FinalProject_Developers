package com.dev.food.model.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.food.model.dao.FoodDao;
import com.dev.food.model.dto.Food;
import com.dev.food.model.dto.FoodHeart;
import com.dev.food.model.dto.FoodPhotoTemp;
import com.dev.food.model.dto.FoodTemp;

@Service
public class FoodServiceImpl implements FoodService{

	/*
	 * private FoodDao dao; private SqlSession session;
	 * 
	 * public FoodServiceImpl(FoodDao dao, SqlSession session) { this.dao = dao;
	 * this.session = session; }
	 */
	
	@Autowired
	private FoodDao dao;

	@Autowired
	private SqlSessionTemplate session;

	@Override
	@Transactional(rollbackFor = {Exception.class})
	public void insertFood(FoodTemp f,FoodPhotoTemp fp) throws Exception {
		dao.insertFood(session, f);
		dao.insertFoodPhoto(session, fp);
	}
	
	@Override
	@Transactional(rollbackFor = {Exception.class})
	public void updateFood(FoodTemp f) throws Exception {
		dao.updateFood(session,f);
	}
	
	@Override
	@Transactional(rollbackFor = {Exception.class})
	public void insertFoodPhoto(FoodPhotoTemp fp) throws Exception {
		dao.insertFoodPhoto(session, fp);
	}

	@Override
	public void mergeFood() {
		dao.mergeFood(session);
	}
	
	@Override
	public void mergeFoodPhoto() {
		dao.mergeFoodPhoto(session);		
	}
	
	@Override
	public void deleteFoodTemp() {
		dao.deleteFoodTemp(session);
	}
	
	@Override
	public void deleteFoodPhotoTemp() {
		dao.deleteFoodPhotoTemp(session);
	}
	
	@Override
	public List<Food> selectFoodAll(Map<String, Object> param) {

		return dao.selectFoodAll(session, param);
	}
	
	@Override
	public List<Food> selectFoodAllTest() {
		return dao.selectFoodAllTest(session);
	}
	
	@Override
	public List<Food> selectFoodByFoodNo(int foodNo) {
		return dao.selectFoodByFoodNo(session,foodNo);
	}

	@Override
	public int selectFoodCount() {
		// TODO Auto-generated method stub
		return dao.selectFoodCount(session);
	}

	@Override
	public Food selectFoodByNo(int no) {
		// TODO Auto-generated method stub
		return dao.selectFoodByNo(session, no);
	}

	/*
	 * @Override public List<Food> getSortedFoods(String sortFilter,int cPage,int
	 * numPerpage){
	 * 
	 * return dao.getSortedFoods(session, sortFilter, cPage, numPerpage); }
	 */
	
	@Override
	public String searchByFoodNo(int foodNo) {
		return dao.searchByFoodNo(session,foodNo);
	}

	@Override
	public List<Food> searchFood(Map<String, Object> params) {

		return dao.searchFood(session, params);
	}

	@Override
	public FoodHeart getFoodById(String memberId) {

		return dao.getFoodById(session, memberId);
	}
	
	@Override
	public int insertHeart(Map param) {
		return dao.insertHeart(session, param);
	}

	@Override
	public int deleteHeart(Map param) {
		return dao.deleteHeart(session, param);
	}

	@Override
	public List<FoodHeart> fdHeart(int no){
		return dao.fdHeart(session, no);
	}
	
	/*
	 * @Override public int toggleHeartAndGetCount(Map params) { boolean isLiked =
	 * dao.checkHeart(session, params);
	 * 
	 * if (isLiked) { dao.deleteHeart(session, params); } else {
	 * dao.insertHeart(session, params); } return dao.getHeartCount(session,
	 * params); }
	 */

	@Override
	public List<Food> foodListTitle(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
