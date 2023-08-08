package com.dev.ac.dto;

import java.io.Serializable;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AcReservation{
	private int arvId;
	private int acId;
	private int apId;
	private Date checkIn;
	private Date checkOut;
}
