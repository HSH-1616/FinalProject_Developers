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
import com.dev.food.model.dto.FoodReview;
import com.dev.food.model.dto.FoodReviewPhoto;
import com.dev.food.model.dto.FoodTemp;

@Service
public class FoodServiceImpl implements FoodService{

	/*
	 * private FoodDao dao; private SqlSession session;
	 * 
	 * public FoodServiceImpl(FoodDao dao, SqlSession session) { this.dao = dao;
	 * this.session = session; }
	 */
	
	@Override
	public List<Food> foodHeartList(int memberId) {
		// TODO Auto-generated method stub
		return dao.foodHeartList(session, memberId);
	}

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
	public int updateFoodOnAdmin(Food food) {
		return dao.updateFoodOnAdmin(session, food);
	}
	
	@Override
	public int deleteFoodOnAdmin(int foodNo) {
		return dao.deleteFoodOnAdmin(session,foodNo);
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
	public void deleteFoodTemp(int foodNo) {
		dao.deleteFoodTemp(session,foodNo);
	}
	
	@Override
	public void deleteFoodPhotoTemp(int foodNo) {
		dao.deleteFoodPhotoTemp(session,foodNo);
	}
	
	@Override
	public List<Food> selectFoodAll(Map<String, Object> param) {

		return dao.selectFoodAll(session, param);
	}
	
	@Override
	public List<Food> selectFoodAllTest(int count) {
		return dao.selectFoodAllTest(session,count);
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
		
		
		int result=dao.insertHeart(session, param);
		int result2=dao.updateHeart(session, param);
		System.out.println(result);
		System.out.println(result2);
		return result2;
	}

	@Override
	public int deleteHeart(Map param) {
		return dao.deleteHeart(session, param);
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
	public int updateHeart(Map param) {
		return dao.updateHeart(session, param);
	}

	@Override
	public int cancleHeart(Map param) {
		return dao.cancleHeart(session, param);
	}

	@Override
	public List<Food> foodListTitle(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	@Override
	public int insertFoodReview(FoodReview fr) {
		int result = dao.insertFoodReview(session,fr);
		if(result>0) {
			if(fr.getFoodReviewPhoto().size()>0) {
				for(FoodReviewPhoto rp:fr.getFoodReviewPhoto()) {
					rp.setFrNo(fr.getFrNo());
					result+=dao.insertFoodReviewPhoto(session,rp);
				}
			}
		}
		//트렌젝션 처리
		if(result!=fr.getFoodReviewPhoto().size()+1)throw new RuntimeException("업로드의 문제가 발생했습니다.");
		return result;
	}
	
//	@Override
//	public List searchByRpNo(int frNo) {
//		return dao.searchByRpNo(session,frNo);
//	}
	
	@Override
	public int updateFoodReview(FoodReview fr) {
		int result = dao.updateFoodReview(session,fr);
		if(result>0) {
			if(fr.getFoodReviewPhoto().size()>0) {
				for(FoodReviewPhoto rp:fr.getFoodReviewPhoto()) {
					rp.setFrNo(fr.getFrNo());
					result+=dao.updateFoodReviewPhoto(session,rp);
				}
			}
		}
		//트렌젝션 처리
		if(result!=fr.getFoodReviewPhoto().size()+1)throw new RuntimeException("업로드의 문제가 발생했습니다.");
		return result;
	}
	
	@Override
	public int deleteFoodReview(int frNo) {
		int result = dao.deleteFoodReview(session,frNo);
		int result2 = 1;
		System.out.println("first : "+result);
		if(result>0) {
			int count = dao.searchFoodReivewPhoto(session,frNo);
			System.out.println("count : "+count);
			if(count>0) {
				result2 = dao.deleteFoodReviewPhoto(session,frNo);	//문제가 생길 때에만 0출력			
				System.out.println("second : "+result2);
			}
		}
		//트렌젝션 처리
		if(result<0||result2<0)throw new RuntimeException("삭제의 문제가 발생했습니다.");
		return result;
	}
	
	@Override
	public int deleteFoodReviewPhoto(int frNo) {
		int result = dao.deleteFoodReviewPhoto(session,frNo);
		return result;
	}
	
//	@Override
//	public List<FoodReview> selectFoodReviewByFoodNo(int foodNo) {
//		return dao.selectFoodReviewByFoodNo(session,foodNo);
//	}
	
	@Override
	public List<FoodReviewPhoto> selectFoodReviewPhotoByFoodNo(int frNo) {
		return dao.selectFoodReviewPhotoByFoodNo(session,frNo);
	}
	
	 @Override
	public int addFood(Food food) {
	     return dao.insertFood(session, food);
	    }
}
