package com.dev.ac.dto;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AcFacilities {
	private int afaId;
	private int afaCamera;
	private int afaAircon;
	private int afaKitchen;
	private int afaWifi;
	private int afaWasher;
	private int afaParking;
	private List<AfaList> afal;
}
