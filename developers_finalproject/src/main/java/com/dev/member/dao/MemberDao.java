package com.dev.member.dao;

import org.apache.ibatis.session.SqlSession;

import com.dev.member.model.dto.Member;

public interface MemberDao {
	
	Member selectByMemberPk(SqlSession session,String memberPk);
	void insertMember(SqlSession session, Member m);
}
