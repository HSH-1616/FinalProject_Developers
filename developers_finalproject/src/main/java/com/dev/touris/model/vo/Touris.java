package com.dev.touris.model.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Touris {
	private String tourisId;
	private String tourisName;
	private String tourisAddress;
	private String tourisContent;
	private String tourislatitude;
	private String tourisLongitude;
	private String tourisLevel;
	private String tourismainImge;
	private String tourispage;
	private String tourisPhone;
	private String tourisExperience;
	private String tourisTel;
	private String tourisUsemonth;
	private String tourisUsetime;
	private String tourisParking;
	private String tourisStartday;
	private String tourisDayoff;
	private String tourisAreaId;
	private List<TourisImages> tourisImages;
}
