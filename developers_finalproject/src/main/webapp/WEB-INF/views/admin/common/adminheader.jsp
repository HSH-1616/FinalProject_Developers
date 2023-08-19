<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<html>
<head>
<meta charset="UTF-8">
<title>admin페이지</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
    <link rel="stylesheet" href="${path }/css/admin/admin.css" />
    <script src="${path }/js/jquery-3.7.0.min.js"></script>
</head>
<body>
	<div class="d-flex" id="wrapper">
        <!-- 공용헤더 -->
        <!-- 사이드바-->
        <div class="bg-white" id="sidebar-wrapper">
            <div class="sidebar-heading text-center py-4 primary-text fs-4 fw-bold text-uppercase border-bottom">
                <i class="fas fa-user-secret me-2"></i>DEVELOPERS</div>
            <div class="list-group list-group-flush my-3">
                <a href="${path }/admin/adminMain" class="list-group-item list-group-item-action bg-transparent second-text active">
                    <i class="fa-solid fa-cloud me-2"></i>main</a>
                <a href="${path}/admin/selectMemberAll" class="list-group-item list-group-item-action bg-transparent second-text fw-bold">
                    <i class="fas fa-user me-2"></i>회원관리</a>
                <a href="${path}/admin/selectFoodList" class="list-group-item list-group-item-action bg-transparent second-text fw-bold">
                    <i class="fas fa-pizza-slice me-2"></i>맛집정보관리</a>
                <a href="${path}/admin/selectAcAll" class="list-group-item list-group-item-action bg-transparent second-text fw-bold">
                    <i class="fas fa-hotel me-2"></i>숙박업소관리</a>
                <a href="${path}/admin/selectTourisAll" class="list-group-item list-group-item-action bg-transparent second-text fw-bold">
                    <i class="fas fa-compass me-2"></i>관광지관리</a>
                <div id="m-hover">
                <a href="#" class="list-group-item list-group-item-action bg-transparent second-text fw-bold">
               		<i class="fas fa-stamp me-2"></i>승인관리</a>
			    <ul class="list-group" id="m-drop" style="display:none;">
				    <li><a class="list-group-item-action second-text fw-bold" href="${path }/admin/foodRecommend" style="margin-left:60px; text-decoration-line: none;">맛집관리</a></li>
				    <li><a class="list-group-item-action second-text fw-bold" href="${path }/admin/paymentList" style="margin-left:60px; text-decoration-line: none;">결제관리</a></li>
				    <li><a class="list-group-item-action second-text fw-bold" href="${path }/admin/refundList" style="margin-left:60px; text-decoration-line: none;">환불관리</a></li>
			    </ul>
			    </div>
                <a href="${path }/admin/main" class="list-group-item list-group-item-action bg-transparent second-text fw-bold">
                    <i class="fa-solid fa-house me-2"></i>HomePage</a>
                <!-- <a href="#" class="list-group-item list-group-item-action bg-transparent text-danger fw-bold">
                    <i class="fas fa-power-off me-2"></i>Logout</a> -->
            </div>
        </div>
        <!-- 본문헤더 -->
        <div id="page-content-wrapper">
            <nav class="navbar navbar-expand-lg navbar-light bg-transparent py-4 px-4">
                <div class="d-flex align-items-center">
                    <i class="fas fa-align-left primary-text fs-4 me-3" id="menu-toggle"></i>
                    <h2 class="fs-2 m-0">main</h2>
                </div>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle second-text fw-bold" href="#" id="navbarDropdown"
                                role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <i class="fas fa-user me-2"></i>John Doe
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="#">Profile</a></li>
                                <li><a class="dropdown-item" href="#">Settings</a></li>
                                <li><a class="dropdown-item" href="#">Logout</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
<script>
	$("#m-hover").mouseover(function(){
		$("#m-drop").css("display","inline");
	})
	$("#m-hover").mouseout(function(){
		$("#m-drop").css("display","none");
	})
/* 	function mdrop(){
		$("#m-drop").css("display","inline");
	} */
</script>            