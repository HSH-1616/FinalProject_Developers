package com.dev.food.model.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodReview {
	
	private int frNo;
	private int foodNo;
	private int memberId;
	private String frContent;
	private double frGrade;
	private Date frWriterDate;
	private List<FoodReviewPhoto> foodReviewPhoto = new ArrayList();
}
