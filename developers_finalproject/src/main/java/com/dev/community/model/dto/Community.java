package com.dev.community.model.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.dev.member.model.dto.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Community {

	private int communityNo;
	
	private String communityTitle;
	private String communityContent;
	private Date createDate;
	private Date modifyDate;
	private int communityViews;
	private int likeCount;
	private String thumbnail;
	private Member memberId;
	private List<CommunityFile> files=new ArrayList<CommunityFile>();
}
