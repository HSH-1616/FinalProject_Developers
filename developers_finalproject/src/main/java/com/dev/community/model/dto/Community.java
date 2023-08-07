package com.dev.community.model.dto;

import java.sql.Date;

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
	private int memberId;
	private String communityTitle;
	private String communityContent;
	private Date createDate;
	private Date modifyDate;
	private int communityViews;
	private int likeCount;
	private CommunityFile files;
}
