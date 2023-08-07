package com.dev.food.model.dto;

import java.sql.Date;

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
	private String foodNo;
	private String userId;
	private String frContent;
	private int frGrade;
	private Date frWriterDate;

}
