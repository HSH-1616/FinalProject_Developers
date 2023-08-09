package com.dev.food.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dev.food.model.dto.Food;
import com.dev.food.model.dto.FoodPhotoTemp;
import com.dev.food.model.dto.FoodTemp;

@Repository
public class FoodDaoImpl implements FoodDao {

	
	
	@Override
	public int insertFood(SqlSession session, FoodTemp f) {
		return session.insert("food.insertFood", f);
	}
	@Override
	public int insertFoodPhoto(SqlSession session, FoodPhotoTemp fp) {
		return session.insert("food.insertFoodPhoto", fp);
	}
	
	@Override
	public int updateFood(SqlSession session, FoodTemp f) {
		return session.update("food.updateFood",f);
	}
	
	@Override
	public int mergeFood(SqlSession session) {
		return session.insert("food.mergeFood");
		//merge한 이후엔 temp테이블 지우기
	}
	@Override
	public int mergeFoodPhoto(SqlSession session) {
		return session.insert("food.mergeFoodPhoto");
		//merge한 이후엔 temp테이블 지우기
	}
	
	@Override
	public void deleteFoodTemp(SqlSession session,int foodNo) {
		session.delete("food.deleteFoodTemp",foodNo);
	}
	@Override
	public void deleteFoodPhotoTemp(SqlSession session,int foodNo) {
		session.delete("food.deleteFoodPhotoTemp",foodNo);
	}

	@Override
	public List<Food> selectFoodAll(SqlSession session, Map<String, Object> paging) {
		
		int cPage=(int)paging.get("cPage");
		int numPerpage=(int)paging.get("numPerpage");
		return session.selectList("food.foodList",null,new RowBounds((cPage-1)*numPerpage, numPerpage));
	}
	
	@Override
	public List<Food> selectFoodAllTest(SqlSession session,int count) {
		return session.selectList("food.selectFoodAllTest",count);
	}
	
	@Override
	public List<Food> selectFoodByFoodNo(SqlSession session,int foodNo) {
		return session.selectList("food.selectFoodByFoodNo",foodNo);
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
	@Override
	public String searchByFoodNo(SqlSession session,int foodNo) {
		return session.selectOne("food.searchByFoodNo",foodNo);
	}
	@Override
	public int insertFood(SqlSession session, Food f) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<Food> selectPage(SqlSession session, int cPage, int numPerpage) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
