package com.dev.food.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.dev.food.model.dao.FoodDao;
import com.dev.food.model.dto.Food;

@Service
public class FoodServiceImpl implements FoodService{

	private FoodDao dao;
	private SqlSession session;
	
	
	public FoodServiceImpl(FoodDao dao, SqlSession session) {
		this.dao = dao;
		this.session = session;
	}


	@Override
	public int insertFood(Food f) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<Food> selectFoodAll(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return dao.selectFoodAll(session, param);
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
