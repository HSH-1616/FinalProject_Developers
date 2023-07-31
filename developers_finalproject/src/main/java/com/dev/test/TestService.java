package com.dev.test;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

@Service
public class TestService {
	
	private TestDao dao=new TestDao();
	private SqlSession session;
	
	public TestService(TestDao dao, SqlSession session) {
		this.dao=dao;
		this.session=session;
	}
	
	public int insertTest(Test test) {
		int result=dao.insertTest(session,test);
		return result;
	}
	
}
