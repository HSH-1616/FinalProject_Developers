package com.dev.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dev.member.model.dto.Member;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Override
	public Member selectByMemberPk(SqlSession session, String memberPk) {
		return session.selectOne("member.selectByMemberPk", memberPk);
	}

	@Override
	public void insertMember(SqlSession session, Member m) {
			session.insert("member.insertMember", m);
		
	}

	@Override
	public int deleteMember(SqlSession session, int memberId) {
		return session.delete("member.deleteMember", memberId);
	}
	
	

}
