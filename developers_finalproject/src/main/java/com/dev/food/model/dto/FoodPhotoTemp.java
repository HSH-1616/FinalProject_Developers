package com.dev.food.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodPhotoTemp {

	private int fpNo;
	private int foodNo;
	private String fpName;
	private int fpMain;
	private String fpId;
}
