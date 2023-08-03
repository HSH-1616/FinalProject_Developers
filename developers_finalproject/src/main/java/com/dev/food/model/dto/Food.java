package com.dev.food.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Food {

	private int foodNo;
	private String foodName;
	private String foodAddress;
	private String foodType;
	private String foodOpenTime;
	private String foodMenu;
	private String foodPhone;
	private int allow;
	private int foodReadCount;

}
