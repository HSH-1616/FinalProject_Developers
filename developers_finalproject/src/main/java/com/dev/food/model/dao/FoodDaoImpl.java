package com.dev.food.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dev.food.model.dto.Food;
import com.dev.food.model.dto.FoodHeart;
import com.dev.food.model.dto.FoodPhotoTemp;
import com.dev.food.model.dto.FoodTemp;

@Repository
public class FoodDaoImpl implements FoodDao {
	
	@Autowired
	private SqlSession sqlSession;
	
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
	public void deleteFoodTemp(SqlSession session) {
		session.delete("food.deleteFoodTemp");
	}
	
	@Override
	public void deleteFoodPhotoTemp(SqlSession session) {
		session.delete("food.deleteFoodPhotoTemp");
	}

	@Override
	public List<Food> selectFoodAll(SqlSession session, Map<String, Object> param) {
		
		int cPage=(int)param.get("cPage");
		int numPerpage=(int)param.get("numPerpage");
		RowBounds rb=new RowBounds((cPage-1)*numPerpage,numPerpage);
		
		return session.selectList("food.selectFoodAll",null,rb);
	}
	
	@Override
	public List<Food> foodListTitle(SqlSession session, Map<String, Object> param){
		
		int cPage=(int)param.get("cPage");
		int numPerpage=(int)param.get("numPerpage");
		RowBounds rb=new RowBounds((cPage-1)*numPerpage,numPerpage);
		
		return session.selectList("food.foodListTitle",null,rb);
	}
	
	@Override
	public List<Food> selectFoodAllTest(SqlSession session) {
		return session.selectList("food.selectFoodAllTest");
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
	public List<Food> searchFood(SqlSession session, Map<String, Object> params) {
		int cPage=(int)params.get("cPage");
		int numPerpage=(int)params.get("numPerpage");
		
		return session.selectList("food.foodSearch",params,new RowBounds((cPage-1)*numPerpage, numPerpage));
	}
	
	/*
	 * @Override public List<Food> getSortedFoods(SqlSession session, String
	 * sortFilter, int cPage, int numPerpage) { RowBounds rn = new RowBounds((cPage
	 * - 1) * numPerpage, numPerpage); return
	 * session.selectList("food.getSortedFoods", sortFilter, rn); }
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
	
	@Override
	public FoodHeart getFoodById(SqlSession session, String memberId) {
		
		return session.selectOne("food.getFoodById", memberId);
	}
	
	/*
	 * @Override public boolean checkHeart(SqlSession session, Map params) { return
	 * session.selectOne("food.checkHeart", params); }
	 */

	@Override
	public int insertHeart(SqlSessionTemplate session,Map param) {
		return session.insert("food.insertHeart",param);
	}
	
	@Override
	public int deleteHeart(SqlSessionTemplate session,Map param) {
		return session.delete("food.deleteHeart",param);
	}
	
	@Override
	public List<FoodHeart> fdHeart(SqlSessionTemplate session, int no){
		return session.selectList("food.fdHeart",no);
	}

	/*
	 * @Override public int getHeartCount(SqlSession session, Map params) { return
	 * session.selectOne("food.getHeartCount", params); }
	 */
	
	@Override
	public List<Food> getFoodsSortedByTitle(SqlSession session, Map<String, Object> params, String sortType) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
