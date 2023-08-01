package com.dev.notice.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeFile {

	private int fileNo;
	private int NoticeNo;
	private String oriName;
	private String fileName;
	private Date uploadDate;
}
