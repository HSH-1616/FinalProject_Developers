package com.dev.touris.model.service;

import java.util.List;
import java.util.Map;

import com.dev.member.model.dto.Member;
import com.dev.touris.model.vo.Touris;
import com.dev.touris.model.vo.TourisTemp;

public interface TourisDetailService {

	Touris selectById(String id);
	void insertDetail(Map param);
	void insertImage(Map param);
	void insertHeart(Map param);
	void deleteHeart(Map param);
	Member selectByIdforMember(Map param);
	void insertTourisTemp(TourisTemp touris);
	void deleteTourisTemp();
}
