package com.dev.member.service;

import com.dev.member.model.dto.Member;

public interface MemberService {
	
	Member selectByEmail(String email);
	void insertMember(Member m);
}
