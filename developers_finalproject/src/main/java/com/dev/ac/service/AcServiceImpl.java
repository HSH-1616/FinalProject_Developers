package com.dev.ac.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.ac.dao.AcDao;
import com.dev.ac.dto.Accommodation;

@Service
public class AcServiceImpl implements AcService {
	
	@Autowired
	private AcDao dao;
	
	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public List<Accommodation> acListAll() {
		return dao.acListAll(session);
	}

}
