<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet" />
<script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
<script type="module"
	src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule
	src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
<script src="${path }/js/jquery-3.7.0.min.js"></script>
<link rel="stylesheet" href="${path }/css/default.css" />
<!DOCTYPE html>
<html>
<head>
<title>여행은 살아보는거야</title>
</head>
<body>
	<header>
		<div class="offcanvas offcanvas-start" id="demo">
			<div class="offcanvas-header">
				<button type="button" class="btn-close" data-bs-dismiss="offcanvas"></button>
			</div>
			<div id="userProfile">
				<img src="${path }/images/common/user.jpg" alt="" />
				<p>UserId</p>
				<button>MyPage</button>
				<b> | </b>
				<button>Logout</button>
			</div>
			<div class="offcanvas-body">
				<p>예약내역</p>
			</div>
		</div>

		<nav class="navbar navbar-expand-sm navbar-dark fixed-top drop">
		<a href="${path}/">
			<img id="headerLogo" src="${path }/images/common/logo.png" alt="" />			
		</a>	
			<div class="container-fluid">
				<ion-icon name="menu" class="btn btn-primary" type="button"
					data-bs-toggle="offcanvas" data-bs-target="#demo"></ion-icon>

				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link active" href="#">Home</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="">Login</a></li>
					<li class="nav-item"><a class="nav-link" href="">MyPage</a></li>
				</ul>
			</div>

			<div id="mainHeader">
				<ul class="main-menu">
					<li class="item">
						<button class="item__name">TRAVEL&FOOD</button>
					</li>
					<li class="item">
						<button class="item__name"
							onclick="location.href='${path }/ac/acList'">ACCOMMODATION</button>
					</li>
					<li class="item">
						<button class="item__name">NOTICE</button>
					</li>
					<li class="item">
						<button class="item__name">COMMUNITY</button>
					</li>
				</ul>
			</div>
		</nav>
	</header>