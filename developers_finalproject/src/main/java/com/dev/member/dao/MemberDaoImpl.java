package com.dev.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dev.member.model.dto.Member;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Override
	public Member selectByEmail(SqlSession session, String email) {
		return session.selectOne("member.selectByEmail", email);
	}

	@Override
	public void insertMember(SqlSession session, Member m) {
			session.insert("member.insertMember", m);
		
	}
	
	

}
