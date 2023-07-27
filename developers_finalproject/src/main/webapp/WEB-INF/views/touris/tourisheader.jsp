<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관광지경로에 해당하는 헤더</title>
<!-- 지도api -->
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ab598319bacded85b90db7133a8bfffa"></script>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=APIKEY&libraries=LIBRARY"></script>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=APIKEY&libraries=services"></script>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=APIKEY&libraries=services,clusterer,drawing"></script>
<!-- 지도api -->
<!--내꺼 메인 css  -->
<link rel="stylesheet" href="${path }/css/touris/touris.css" />
<link rel="stylesheet" href="${path }/css/touris/defaulttouris.css">
<!--  -->
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
<!--  -->
<!-- 여기서는 지도 라이브러리 -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<!-- <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" /> -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
</head>
<body>
	<header>
      <div class="offcanvas offcanvas-start" id="demo">
        <div class="offcanvas-header">
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="offcanvas"
          ></button>
        </div>
        <div id="userProfile">
          <img src="./image/user.jpg" alt="" />
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
        <img id="headerLogo" src="./image/logo.png" alt="" />
        <div class="container-fluid">
          <ion-icon
            id="fluidcicon"
            name="menu"
            class="btn btn-primary"
            type="button"
            data-bs-toggle="offcanvas"
            data-bs-target="#demo"
          ></ion-icon>

          <ul class="navbar-nav">
            <li class="nav-item">
              <a class="nav-link active" href="#">Home</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="">Login</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="">MyPage</a>
            </li>
          </ul>
        </div>

        <div id="mainHeader">
          <ul class="main-menu">
            <li class="item">
              <div class="item__name">TRAVEL&FOOD</div>
              <div class="item__contents">
                <div class="contents__menu">
                  <ul class="inner">
                    <li>
                      <h4>여행</h4>
                      <ul>
                        <li>내용</li>
                        <li>내용</li>
                        <li>내용</li>
                      </ul>
                    </li>
                    <li>
                      <h4>맛집</h4>
                      <ul>
                        <li>내용</li>
                        <li>내용</li>
                        <li>내용</li>
                      </ul>
                    </li>
                  </ul>
                </div>
              </div>
            </li>
            <li class="item">
              <div class="item__name">ACCOMMODATION</div>
              <div class="item__contents">
                <div class="contents__menu">
                  <ul class="inner">
                    <li>
                      <h4>숙박업소</h4>
                      <ul>
                        <li>내용</li>
                        <li>내용</li>
                        <li>내용</li>
                      </ul>
                    </li>
                  </ul>
                </div>
              </div>
            </li>
            <li class="item">
              <div class="item__name">NOTICE</div>
              <div class="item__contents">
                <div class="contents__menu">
                  <ul class="inner">
                    <li>
                      <h4>공지사항</h4>
                      <ul>
                        <li>내용</li>
                        <li>내용</li>
                        <li>내용</li>
                      </ul>
                    </li>
                  </ul>
                </div>
              </div>
            </li>
            <li class="item">
              <div class="item__name">COMMUNITY</div>
              <div class="item__contents">
                <div class="contents__menu">
                  <ul class="inner">
                    <li>
                      <h4>커뮤니티</h4>
                      <ul>
                        <li>내용</li>
                        <li>내용</li>
                        <li>내용</li>
                      </ul>
                    </li>
                  </ul>
                </div>
              </div>
            </li>
          </ul>
        </div>
      </nav>
    </header>
</body>
</html>