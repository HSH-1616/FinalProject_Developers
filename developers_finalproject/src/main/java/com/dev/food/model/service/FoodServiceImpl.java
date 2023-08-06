package com.dev.food.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.food.model.dao.FoodDao;
import com.dev.food.model.dto.Food;
import com.dev.food.model.dto.FoodPhoto;
import com.dev.food.model.dto.FoodPhotoTemp;
import com.dev.food.model.dto.FoodTemp;

@Service
public class FoodServiceImpl implements FoodService{

	private FoodDao dao;
	private SqlSession session;
	
	
	public FoodServiceImpl(FoodDao dao, SqlSession session) {
		this.dao = dao;
		this.session = session;
	}


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
	public List<Food> selectFoodAll(Map<String,Object> param) {
		// TODO Auto-generated method stub
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

	@Override
	public String searchByFoodNo(int foodNo) {
		return dao.searchByFoodNo(session,foodNo);
	}
	
}
