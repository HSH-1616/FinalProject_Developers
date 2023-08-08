package com.dev.touris.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourisHeart {
	
	private String tourisHeartId;
	private String tourisId;
	private int memberId;
}
