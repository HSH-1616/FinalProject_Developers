package com.dev.notice.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.dev.notice.model.dto.Notice;

public interface NoticeService {

	List<Notice> noticeList();
	Notice noticeView(int no,HttpServletRequest req, HttpServletResponse res);
	int insertNotice(Notice m);
	int deleteNotice(int no);
	int deleteNoticeFile(String fileName);
	String saveFile(MultipartFile file,HttpSession session);
	int updateNotice(Notice n);
	List<Notice> searchNotice(Map<String, Object> params);
}
