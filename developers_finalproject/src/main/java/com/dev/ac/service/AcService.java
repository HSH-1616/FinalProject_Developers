package com.dev.ac.service;

import java.util.List;
import java.util.Map;

import com.dev.ac.dto.Accommodation;

public interface AcService {
	
	List<Accommodation> acListAll();
	
	List<Accommodation> searchListAll(Map param);
	
	Accommodation acDetail(int no);
	
	Accommodation acPay(int no);
}
