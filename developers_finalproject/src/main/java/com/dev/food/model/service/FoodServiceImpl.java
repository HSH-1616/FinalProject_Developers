package com.dev.food.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.food.model.dao.FoodDao;
import com.dev.food.model.dto.Food;
import com.dev.food.model.dto.FoodPhoto;

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
	public void insertFood(Food f,FoodPhoto fp) throws Exception {
		dao.insertFood(session, f);
		dao.insertFoodPhoto(session, fp);
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
	public int selectFoodCount() {
		// TODO Auto-generated method stub
		return dao.selectFoodCount(session);
	}


	@Override
	public Food selectFoodById(int no) {
		// TODO Auto-generated method stub
		return dao.selectFoodByNo(session, no);
	}


	
	
	
}
