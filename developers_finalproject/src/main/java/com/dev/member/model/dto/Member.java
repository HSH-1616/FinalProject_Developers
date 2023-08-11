package com.dev.member.model.dto;

import java.sql.Date;
import java.util.List;

import com.dev.touris.model.vo.TourisHeart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {

	private int memberId;
	private String memberNickname;
	private String memberImage;
	private String memberCategory;
	private String memberEmail;
	private Date memberEnrolldate;
	private String memberStatus;
	private String memberPk;
	private List<TourisHeart> tourisHeartlist;
}
