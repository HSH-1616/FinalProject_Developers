package com.dev.member.dao;

import org.apache.ibatis.session.SqlSession;

import com.dev.member.model.dto.Member;

public interface MemberDao {
	
	Member selectByEmail(SqlSession session,String email);
	void insertMember(SqlSession session, Member m);
}
