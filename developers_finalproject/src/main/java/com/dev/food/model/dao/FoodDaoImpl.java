package com.dev.food.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dev.food.model.dto.Food;

@Repository
public class FoodDaoImpl implements FoodDao {

	

	@Override
	public int insertFood(SqlSession session, Food f) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Food> selectFoodAll(SqlSession session, Map<String, Object> paging) {
		
		int cPage=(int)paging.get("cPage");
		int numPerpage=(int)paging.get("numPerpage");
		return session.selectList("food.foodList",null,new RowBounds((cPage-1)*numPerpage, numPerpage));
	}

	@Override
	public int selectFoodCount(SqlSession session) {
		// TODO Auto-generated method stub
		return session.selectOne("food.selectFoodCount");
	}

	@Override
	public Food selectFoodByNo(SqlSession session, int no) {
		// TODO Auto-generated method stub
		return session.selectOne("food.selectFoodByNo",no);
	}

	@Override
	public List<Food> searchFood(SqlSession session, Map<String, Object> params, Map<String, Object> paging) {
		int cPage=(int)paging.get("cPage");
		int numPerpage=(int)paging.get("numPerpage");
		return session.selectList("food.foodSearch",params,new RowBounds((cPage-1)*numPerpage, numPerpage));
	}

	
	/*
	 * @Override public List<Food> getSortedFoods(SqlSession session, String
	 * sortFilter, int cPage, int numPerpage){
	 * 
	 * RowBounds rn=new RowBounds((cPage-1)*numPerpage,numPerpage); return
	 * session.selectList("food.getSortedFoods",sortFilter,rn); }
	 */
	
}
