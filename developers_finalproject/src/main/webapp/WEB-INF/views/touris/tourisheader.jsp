<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!-- 지도api -->
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ab598319bacded85b90db7133a8bfffa&libraries=services,clusterer,drawing"></script>
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
<!--내꺼 메인 css  -->
<link rel="stylesheet" href="${path }/css/touris/touris.css" />
<link rel="stylesheet" href="${path }/css/touris/defaulttouris.css"/>
<link rel="stylesheet" href="${path }/css/login/login.css" />
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Montserrat:500,800" />
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script src="https://accounts.google.com/gsi/client" async defer></script>
<!-- 여기서는 지도 라이브러리 -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.min.js" integrity="sha256-eTyxS0rkjpLEo16uXTS0uVCS4815lc40K2iVpWDvdSY=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui-touch-punch/0.2.3/jquery.ui.touch-punch.min.js" integrity="sha512-0bEtK0USNd96MnO4XhH8jhv3nyRF0eK87pJke6pkYf3cM0uDIhNJy9ltuzqgypoIFXw3JSuiy04tVk4AjpZdZw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
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
							onclick="location.href='${path }'">TOURIS</button>
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
	<script src="${path }/js/login/login.js"></script>