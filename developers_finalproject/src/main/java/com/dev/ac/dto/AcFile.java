package com.dev.ac.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"afMain","acId"})
public class AcFile {
	private int afId;
	private int acId;
	private String afName;
	private char afMain;
}
