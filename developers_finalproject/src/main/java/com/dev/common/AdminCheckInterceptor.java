package com.dev.common;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.dev.admin.model.dto.Admin;
public class AdminCheckInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session=request.getSession();
		Admin loginAdmin=(Admin)session.getAttribute("loginAdmin");
		if(loginAdmin==null) {
			request.setAttribute("msg","관리자 로그인 후 이용할 수 있는 서비스입니다!!!");
			request.setAttribute("loc","/");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
			return false;
		}
		return true;
	}
}
