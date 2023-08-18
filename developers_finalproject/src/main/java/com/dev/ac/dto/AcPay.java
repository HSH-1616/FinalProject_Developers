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
	private int acId;
	private String memberId;
	private int apPrice;
	private Date apDate;
	private Date apReDate;
	private Date apCancelDate;
	private int apPeople;
	private char apCancel;
	private String apOrderId;
	private String apKeyId;
	AcReservation arv;
}
