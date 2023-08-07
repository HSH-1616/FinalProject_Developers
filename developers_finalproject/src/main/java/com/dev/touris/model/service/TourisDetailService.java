package com.dev.touris.model.service;

import java.util.Map;

import com.dev.touris.model.vo.Touris;

public interface TourisDetailService {

	Touris selectById(String id);
	void insertDetail(Map param);
	void insertImage(Map param);
	void insertHeart(Map param);
	void deleteHeart(Map param);
}
