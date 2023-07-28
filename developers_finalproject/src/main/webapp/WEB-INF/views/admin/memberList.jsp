<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<jsp:include page="/WEB-INF/views/admin/common/adminheader.jsp"/>
<!-- 본문 내용-->
	<div class="container-fluid px-4">
	    <div class="row my-5">
	        <h3 class="fs-4 mb-3">회원관리</h3>
	        <div class="col">
	            <table class="table bg-white rounded shadow-sm  table-hover text-center align-middle">
	                <thead>
	                    <tr>
	                        <th scope="col" width="50">NO</th>
	                        <th scope="col">닉네임</th>
	                        <th scope="col">이메일</th>
	                        <th scope="col">회원분류</th>
	                        <th scope="col">가입일자</th>
	                        <th scope="col">회원상태</th>
	                        <th scope="col">회원관리</th>
	                    </tr>
	                </thead>
	                <tbody>
	                    <tr>
	                        <th scope="row">1</th>
	                        <td>닉네임</td>
	                        <td>이메일</td>
	                        <td>회원분류</td>
	                        <td>가입일자</td>
	                        <td>회원상태</td>
	                        <td><button type="button" class="btn btn-outline-dark btn-sm">블랙</button></td>
	                    </tr>
	                </tbody>
	            </table>
	        </div>
	    </div>
	</div>    
<jsp:include page="/WEB-INF/views/admin/common/adminfooter.jsp"/>