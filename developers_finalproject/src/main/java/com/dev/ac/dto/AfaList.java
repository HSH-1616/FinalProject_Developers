package com.dev.ac.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"afalName","afaId"})
public class AfaList {
	private int afalId;
	private int afaId;
	private String afalName;
	private String afalImg;
}
