package com.dev.ac.dto;

import java.sql.Date;
import java.util.List;

import com.dev.member.model.dto.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AcReview {
	private int arId;
	private String arContent;
	private double arGrade;
	private Date arDate;
	private Member member;
	private List<ArFile> arFiles;
}
