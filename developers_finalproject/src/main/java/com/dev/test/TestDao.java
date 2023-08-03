package com.dev.test;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class TestDao {
	
	public int insertTest(SqlSession session,Test test) {
		return session.insert("test.insertTest",test);
	}
}
