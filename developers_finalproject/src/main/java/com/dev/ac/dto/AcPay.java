package com.dev.ac.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AcPay {
	private int apId;
	private int apPrice;
	private Date apDate;
	private Date apReDate;
	private Date checkIn;
	private Date checkOut;
	private int apPeople;
	private char apCancel;
}
