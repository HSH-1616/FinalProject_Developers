package com.dev.member.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.dev.member.dao.MemberDao;
import com.dev.member.model.dto.Member;

@Service
public class MemberServiceImpl implements MemberService {

	private MemberDao dao;
	private SqlSession session;
	
	
	public MemberServiceImpl(MemberDao dao, SqlSession session) {
		this.dao=dao;
		this.session=session;
	}
	
	@Override
	public Member selectByEmail(String email) {
		return dao.selectByEmail(session,email);
	}

	@Override
	public void insertMember(Member m) {
		dao.insertMember(session,m);
	}
	
	

}
