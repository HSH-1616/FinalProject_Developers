package com.dev.member.service;

import com.dev.member.model.dto.Member;

public interface MemberService {
	
	Member selectByMemberPk(String memberPk);
	
	void insertMember(Member m);
	
	int deleteMember(int memberId);
}
