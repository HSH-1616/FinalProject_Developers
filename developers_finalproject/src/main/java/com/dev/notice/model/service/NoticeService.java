package com.dev.notice.model.service;

import java.util.List;

import com.dev.notice.model.dto.Notice;

public interface NoticeService {

	List<Notice> NoticeList();
	int insertNotice(Notice m);
	int deleteNotice(String no);
	
}
