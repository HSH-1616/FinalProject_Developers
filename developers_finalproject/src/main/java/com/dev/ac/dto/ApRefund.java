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
public class ApRefund {
	private int aprId;
	private int apId;
	private String aprReason;
	private String aprContent;
	private String aprComment;
}
