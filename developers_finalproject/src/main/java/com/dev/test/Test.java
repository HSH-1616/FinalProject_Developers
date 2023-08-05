package com.dev.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Test {
	
	private String tourisId;
	private String tourisName;
	private String tourisAddress;
	private String tourisContent;
	private String tourisLatitude;
	private String tourisLongitude;
	private String tourisLevel;
	private String tourisMainImage;
	private String tourisPage;
	private String tourisPhone;
	private String tourisExperience;
	private String tourisTel;
	private String tourisUsemonth;
	private String tourisUsetime;
	private String tourisParking;
	private String tourisStartday;
	private String tourisDayoff;
	private String areaId;

}
