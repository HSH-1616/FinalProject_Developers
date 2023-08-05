package com.dev.ac.dto;

import java.sql.Date;
import java.util.List;

import com.dev.member.model.dto.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArFile {
	private int arfId;
	private String arfName;
}
