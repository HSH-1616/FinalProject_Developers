package com.dev.community.model.dto;

import com.dev.member.model.dto.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply {

	private int replyNo;
	
	private int communityNo;
	private String replyContent;
	private int replyLevel;
	private int replyRef;
	private Member memberId;
}
