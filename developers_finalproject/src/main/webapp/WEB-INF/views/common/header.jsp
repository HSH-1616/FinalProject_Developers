<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
	<script>
		var path="${path}";
	</script>
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
<link rel="stylesheet" href="${path }/css/login/login.css" />
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Montserrat:500,800" />
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script src="https://accounts.google.com/gsi/client" async defer></script>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon" href="${path}/images/common/favicon.png">
<title>여행은 살아보는거야</title>
</head>
<body>
	<header>
		<div class="offcanvas offcanvas-start" id="demo">
			<div class="offcanvas-header">
				<button type="button" class="btn-close" data-bs-dismiss="offcanvas"></button>
			</div>
			<div id="userProfile">
				<c:if test="${empty loginMember }">
					<p>로그인 후 이용해주세요</p>
				</c:if>
				<c:if test="${not empty loginMember }">
					<img src="${loginMember.memberImage}" alt="" />
					<p>${loginMember.memberNickname }</p>
					<button>MyPage</button>
					<b> | </b>
					<button><a class="nav-link active" href="${path }/member/logout">Logout</a></button>
				</c:if>
			</div>
			<c:if test="${not empty loginMember }">
			<div class="offcanvas-body">
				<p>예약내역</p>
			</div>
			</c:if>
		</div>
		<nav class="navbar navbar-expand-sm navbar-dark fixed-top drop">
			<a href="${path}/"> <img id="headerLogo"
				src="${path }/images/common/logo.png" alt="" />
			</a>
			<div class="container-fluid">
				<ion-icon name="menu" class="btn btn-primary" type="button"
					data-bs-toggle="offcanvas" data-bs-target="#demo"></ion-icon>
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link active" href="#">Home</a>
					</li>

				<c:if test="${empty loginMember && empty loginAdmin }">
					<li class="nav-item"><a class="nav-link"
						href="javascript:void(0)" id="btn-modal">Login</a></li>
				</c:if>
				<c:if test="${not empty loginMember || not empty loginAdmin}">
					<li class="nav-item"><a class="nav-link active" href="${path }/member/logout">LogOut</a>
					</li>
				</c:if>
				<c:if test="${not empty loginMember}">
					<li class="nav-item"><a class="nav-link" href="${path }/mypage/mypage">MyPage</a></li>
				</c:if>
				<c:if test="${empty loginMember && not empty loginAdmin }">
					<li class="nav-item"><a class="nav-link" href="${path }/admin/adminMain">AdminPage</a></li>
				</c:if>
				</ul>
			</div>

			<div id="mainHeader">
				<ul class="main-menu">
					<li class="item">
						<button class="item__name"
							onclick="location.href='${path }/touris/tourislist'">TOURIS</button>
					</li>
					<li class="item">
						<button class="item__name"
							onclick="location.href='${path }/food/foodList.do'">FOOD</button>
					</li>
					<li class="item">
						<button class="item__name"
							onclick="location.href='${path }/ac/acList'">ACCOMMODATION</button>
					</li>
					<li class="item">
						<button class="item__name"
							onclick="location.href='${path }/notice/noticeList.do'">NOTICE</button>
					</li>
					<li class="item">
						<button class="item__name"
							onclick="location.href='${path }/community/communityList.do'">COMMUNITY</button>
					</li>
				</ul>
			</div>
		</nav>
	</header>
	<div id="modal" class="modal-overlay">
		<div class="modal-window">
			<div class="close-area">X</div>
			<div class="content">
				<div class="login-container">
					<!-- Heading -->
					<!-- <h1>DEVELOPERS</h1> -->
					<div>
						<img alt="" src="${path}/images/common/logo.png">
					</div>
					<div class="m-btn-container">
						<!-- 카카오 button -->
						<img class="socialbtn" src="${path }/images/login/kakaobtn.png"
							alt="어딧니?" onclick="kakaologin();">
						<!-- 네이버 button -->
						<img class="socialbtn" src="${path }/images/login/naverbtn.png"
							alt="어딧니?" onclick="naverlogin();">
						<!-- google button -->
						<img class="socialbtn" src="${path }/images/login/googlebtn.png"
							alt="어딧니?" onclick="googlelogin();">
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="test-kakao-chanel-button"></div>
	<script src="${path }/js/login/login.js"></script>
	<script src="${path }/js/notice/notice.js"></script>  