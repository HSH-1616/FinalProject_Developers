package com.dev.food.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodReviewPhoto {

	private int rpNo;
	private int frNo;
	private String rpName;
	private String rpRename;
}
