package com.dev.food.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.SelectBuilder.SelectOn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dev.food.model.dto.Food;
import com.dev.food.model.dto.FoodBlackList;
import com.dev.food.model.dto.FoodHeart;
import com.dev.food.model.dto.FoodPhoto;
import com.dev.food.model.dto.FoodPhotoTemp;
import com.dev.food.model.dto.FoodReview;
import com.dev.food.model.dto.FoodReviewPhoto;
import com.dev.food.model.dto.FoodTemp;

@Repository
public class FoodDaoImpl implements FoodDao {
	

	@Autowired
	private SqlSession sqlSession;
	
	private final JdbcTemplate jdbcTemplate;

    public FoodDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public List<Food> foodHeartList(SqlSessionTemplate session, int memberId) {
    	return session.selectList("food.foodHeartList", memberId);
    }
    
    
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
	public int updateFoodOnNull(SqlSession session, FoodTemp food) {
		return session.update("food.updateFoodOnNull",food);
	}
	
	@Override
	public int insertFoodPhotoOnNull(SqlSession session, FoodPhotoTemp fp) {
		return session.insert("food.insertFoodPhotoOnNull",fp);
	}
	
	@Override
	public int updateFoodOnAdmin(SqlSession session, Food food) {
		return session.update("food.updateFoodOnAdmin",food);
	}
	
	@Override
	public int deleteFoodOnAdmin(SqlSession session, int foodNo) {
		return session.delete("food.deleteFoodOnAdmin",foodNo);
	}
	
	@Override
	public int mergeFood(SqlSession session) {
		return session.update("food.mergeFood");
	}
	
	@Override
	public int mergeFoodPhoto(SqlSession session) {
		return session.update("food.mergeFoodPhoto");
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
	public int deleteFoodTempAll(SqlSession session) {
		return session.delete("food.deleteFoodTempAll");
	}
	
	@Override
	public int deleteFoodPhotoTempAll(SqlSession session) {
		return session.delete("food.deleteFoodPhotoTempAll");
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
	public List<Food> selectFoodAllTest(SqlSession session,int count) {
		return session.selectList("food.selectFoodAllTest",count);
	}
	
	@Override
	public List<Food> selectFoodByFoodNo(SqlSession session,int foodNo) {
		return session.selectList("food.selectFoodByFoodNo",foodNo);
	}
	
	@Override
	public List<Food> selectFoodTempByFoodNo(SqlSession session, int foodNo) {
		return session.selectList("food.selectFoodTempByFoodNo",foodNo);
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
	public int selectFoodPhotoByNo(SqlSession session, String fpName) {
		return session.selectOne("food.selectFoodPhotoByNo",fpName);
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
	public int copyFoodtoFoodTemp(SqlSession session, int foodNo) {
		return session.insert("food.copyFoodtoFoodTemp",foodNo);
	}
	
	@Override
	public int copyFPtoFPTemp(SqlSession session, int foodNo) {
		return session.insert("food.copyFPtoFPTemp",foodNo);
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
	public int insertFoodReview(SqlSession session, FoodReview fr) {
		return session.insert("food.insertFoodReview",fr);
	}
	
	@Override
	public int insertFoodReviewPhoto(SqlSession session, FoodReviewPhoto rp) {
		return session.insert("food.insertFoodReviewPhoto",rp);
	}
	
//	@Override
//	public List searchByRpNo(SqlSession session, int frNo) {
//		return session.selectList("food.searchByRpNo",frNo);
//	}
	
	@Override
	public int updateFoodReview(SqlSession session, FoodReview fr) {
		return session.update("food.updateFoodReview",fr);
	}
	
	@Override
	public int updateFoodReviewPhoto(SqlSession session, FoodReviewPhoto rp) {
		return session.insert("food.updateFoodReviewPhoto",rp);
	}
	
	@Override
	public int deleteFoodReview(SqlSession session, int frNo) {
		return session.delete("food.deleteFoodReview",frNo);
	}
	
	@Override
	public int searchFoodReivewPhoto(SqlSession session, int frNo) {
		return session.insert("food.searchFoodReivewPhoto",frNo);
	}
	
	@Override
	public int deleteFoodReviewPhoto(SqlSession session, int frNo) {
		return session.delete("food.deleteFoodReviewPhoto",frNo);
	}
	
//	@Override
//	public List<FoodReview> selectFoodReviewByFoodNo(SqlSession session, int foodNo) {
//		return session.selectList("food.selectFoodReviewByFoodNo",foodNo);
//	}
	
	@Override
	public List<FoodReviewPhoto> selectFoodReviewPhotoByFoodNo(SqlSession session, int frNo) {
		return session.selectList("food.selectFoodReviewPhotoByFoodNo",frNo);
	}

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
	public int updateHeart(SqlSessionTemplate session, Map param) {
		return session.update("food.updateHeart",param);
	}
	
	@Override
	public int cancleHeart(SqlSessionTemplate session, Map param) {
		return session.delete("food.cancleHeart",param);
	}
	
	/*
	 * @Override public int getHeartCount(SqlSession session, Map params) { return
	 * session.selectOne("food.getHeartCount", params); }
	 */

	@Override
	public List<Food> getFoodsSortedByTitle(SqlSession session, Map<String, Object> params, String sortType) {
		return null;
	}

	@Override
	public int insertFoodBlackList(SqlSession session, FoodBlackList fb) {
		return session.insert("food.insertFoodBlackList",fb);
	}
	
	@Override
	public int selectFoodBlackListCount(SqlSession session) {
		return session.selectOne("food.selectFoodBlackListCount");
	}
	
	@Override
	public List<Food> selectFoodReviewByFoodNo(SqlSession session, int memberId, Map<String, Object> params) {
		int cPage=(int)params.get("cPage");
		int numPerpage=(int)params.get("numPerpage");
		return session.selectList("food.selectFoodReviewByFoodNo",memberId, new RowBounds((cPage-1)*numPerpage, numPerpage));
	}

	@Override
	public int selectFoodReviewByFoodNoCount(SqlSession session, int memberId) {
		return session.selectOne("food.selectFoodReviewByFoodNoCount", memberId);
	}
	
	@Override
	public String searchFoodNameByNo(SqlSession session, int foodNo) {
		return session.selectOne("food.searchFoodNameByNo",foodNo);
	}

}
