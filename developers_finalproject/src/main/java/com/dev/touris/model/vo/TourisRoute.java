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
public class TourisRoute {
	private int routeId;
	private TourisMember tuesId;
	private Touris tourisesId;
	private int tourisrouteLevel;
	private String tourisrouteDate;
	
}
