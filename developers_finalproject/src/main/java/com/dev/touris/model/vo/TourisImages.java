package com.dev.touris.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourisImages {
	
	private int tourisImagesId;
	private String tourisImages;
	private String tourisId;
}
