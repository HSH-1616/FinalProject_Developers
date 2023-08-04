package com.dev.touris.model.vo;

import java.util.List;

import org.apache.ibatis.annotations.One;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"tourises"})
public class TourisArea {
	private String areaId;
	private String areaName;
	private String areaLatitude;
	private String areaLongitude;
	private String areaEng;
	private String areaImg;
	private String areaContent;
	List<Touris> tourises;
}
