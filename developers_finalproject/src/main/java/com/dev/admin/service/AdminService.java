package com.dev.admin.service;

import java.util.List;
import java.util.Map;

import com.dev.admin.model.dto.Admin;
import com.dev.member.model.dto.Black;
import com.dev.member.model.dto.Member;
import com.dev.touris.model.vo.Touris;

public interface AdminService {
	
	Admin adminLogin(Map param);
	List<Member> selectMemberAll(Map param);
	int selectMemberAllCount();
	void insertBlack(Map param);
	void updateBlack(Map param);
	List<Black> selectBlackAll();
	void blackClear(Map param);
	void updateClear(Map param);
	List<Member> selectBySocial(Map param);
	int selectBySocialCount(String memberCategory);
	List<Touris> selectTourisAll(Map param);
	int selectTourisAllCount();
	List<Touris> searchTouris(Map param);
	int searchTourisCount(Map param);
	List<Touris> selectBytourisAreaId(Map param);
	int selectBytourisAreaIdCount(Map parma);
}
