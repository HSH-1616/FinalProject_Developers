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
import com.dev.food.model.dto.FoodReview;
import com.dev.food.model.dto.FoodReviewPhoto;
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
	public void deleteFoodTemp(int foodNo) {
		dao.deleteFoodTemp(session,foodNo);
	}
	
	@Override
	public void deleteFoodPhotoTemp(int foodNo) {
		dao.deleteFoodPhotoTemp(session,foodNo);
	}
	

	@Override
	public List<Food> searchFood(Map<String, Object> params, Map<String, Object> paging) {
		return dao.searchFood(session,params,paging);
	}

	@Override
	public List<Food> selectFoodAll(Map<String, Object> paging) {

		return dao.selectFoodAll(session, paging);
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
	
	@Override
	public List<FoodReview> selectFoodReviewByFoodNo(int foodNo) {
		return dao.selectFoodReviewByFoodNo(session,foodNo);
	}
	
}
