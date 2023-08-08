package com.dev.ac.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AcFile {
	private int afId;
	private int acId;
	private String afName;
	private char afMain;
}
