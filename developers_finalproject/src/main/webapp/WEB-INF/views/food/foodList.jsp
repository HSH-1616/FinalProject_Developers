<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<link rel="stylesheet" href="${path }/css/food/foodList.css" />

<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet" />
<script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="module"
	src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>

<script nomodule
	src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
	
	
	<!-- 페이지 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/paginationjs/2.1.4/pagination.min.js"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/paginationjs/2.1.4/pagination.css"/>

<link rel="stylesheet" href="css/header.css" />
<link rel="stylesheet" href="css/foodList.css" />
<script src="${path }/js/food/foodList.js" />
</head>

<jsp:include page="/WEB-INF/views/common/header.jsp" />

<section>

	<!-- 맛집 리스트 순서 -->
	<div id="foodList_theme">
		<div id="food_main_theme">
			<p>맛집 목록</p>
		</div>
		<div class="container">
		<nav id="food_menu">
		<input type="hidden" id="sortFilter" value="sortFilter">
			<ul>
				<li class="bar">|</li>
				<li class="menu_style" id="all"><span class="color_ch">제목순</span></li>
				<li class="bar">|</li>
				<li class="menu_style" id="popular"><span class="color_ch">조회순</span></li>
				<li class="bar">|</li>
				<li class="menu_style" id="review"><span class="color_ch">리뷰순</span></li>
				<li class="bar">|</li>
			</ul>
		</nav>
		</div>
		<!-- /맛집 리스트 순서 -->

		<!-- 맛집 검색 -->
		<!-- <div id="store_search">
			<form id="searchType" name="searchType">
			<select name="searchType">
				<option value="total">전체</option>
				<option value="foodName">상호명</option>
				<option value="foodAddress">주소</option>
			</select>
			</form>
			<input id="input_search_text" type="text" placeholder="Search...">
			<div id="search_button">
				<svg width="24" height="24" viewBox="0 0 24 24" fill="none"
					xmlns="http://www.w3.org/2000/svg">
                        <path
						d="M21.5 23.25L13.625 15.375C13 15.875 12.2812 16.2708 11.4688 16.5625C10.6562 16.8542 9.79167 17 8.875 17C6.60417 17 4.6825 16.2133 3.11 14.64C1.5375 13.0667 0.750833 11.145 0.75 8.875C0.75 6.60417 1.53667 4.6825 3.11 3.11C4.68333 1.5375 6.605 0.750833 8.875 0.75C11.1458 0.75 13.0675 1.53667 14.64 3.11C16.2125 4.68333 16.9992 6.605 17 8.875C17 9.79167 16.8542 10.6562 16.5625 11.4688C16.2708 12.2812 15.875 13 15.375 13.625L23.25 21.5L21.5 23.25ZM8.875 14.5C10.4375 14.5 11.7658 13.9529 12.86 12.8588C13.9542 11.7646 14.5008 10.4367 14.5 8.875C14.5 7.3125 13.9529 5.98417 12.8588 4.89C11.7646 3.79583 10.4367 3.24917 8.875 3.25C7.3125 3.25 5.98417 3.79708 4.89 4.89125C3.79583 5.98542 3.24917 7.31333 3.25 8.875C3.25 10.4375 3.79708 11.7658 4.89125 12.86C5.98542 13.9542 7.31333 14.5008 8.875 14.5Z"
						fill="white" />
                    </svg>
			</div>
		</div> -->
		<div class="search-notice text-end mt-3">
			<form class="search-form">
				<!-- action="/food/searchFood.do" -->
				<select name="type">
					<option value="titile">상호명</option>
					<option value="content">주소</option>
				</select> 
				<input type="text" name="keyword" placeholder="검색어를 입력해주세요">
				<button type="button" class="" onclick="searchFood();">검색</button>
			</form>
		</div>
		
		<!-- /맛집 검색 -->
	</div>

	<div class="food_main_list">
		<c:if test="${not empty foods }">
			<c:forEach var="f" items="${foods }">
				<div class="food_list">
					<div class="con-like" style="position:relative;z-index:2;">
						<input title="like" type="checkbox" class="like">
						<div class="checkmark">
							<svg viewBox="0 0 24 24" class="outline"
								xmlns="http://www.w3.org/2000/svg">
                  <path
									d="M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Zm-3.585,18.4a2.973,2.973,0,0,1-3.83,0C4.947,16.006,2,11.87,2,8.967a4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,11,8.967a1,1,0,0,0,2,0,4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,22,8.967C22,11.87,19.053,16.006,13.915,20.313Z"></path>
                </svg>
							<svg viewBox="0 0 24 24" class="filled"
								xmlns="http://www.w3.org/2000/svg">
                  <path
									d="M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Z"></path>
                </svg>
							<svg class="celebrate" width="100" height="100"
								xmlns="http://www.w3.org/2000/svg">
                  <polygon points="10,10 20,20" class="poly"></polygon>
                  <polygon points="10,50 20,50" class="poly"></polygon>
                  <polygon points="20,80 30,70" class="poly"></polygon>
                  <polygon points="90,10 80,20" class="poly"></polygon>
                  <polygon points="90,50 80,50" class="poly"></polygon>
                  <polygon points="80,80 70,70" class="poly"></polygon>
                </svg>
						</div>
					</div>
					
					<c:forEach var="fp" items="${f.foodPhoto}">
						<c:if test="${fp.fpMain == 1}">
							<a class="pig" href="${path}/food/foodDetail.do?no=${f.foodNo}">
								<img alt="대표이미지" src="${fp.fpName}" style="width: 300px; height: 300px;"><br>
							</a>
						</c:if>
						<c:if test="${fp.fpMain != 1}"></c:if>
					</c:forEach>
					<div class="food_menu">${f.foodName }</div>
						<div class="food_address" style="color: #828282;">${f.foodAddress}</div>
					<div class="countDiv">
						<img class="heart" src="${path }/images/food/fillheart.svg">
						<span class="heart_count">1</span> 
						<img class="comment" src="${path }/images/food/comment.png"> 
						<span class="comment_count">${f.foodReadCount}</span> 
						<!-- <span class="star"> ★★★★★ <span>★★★★★</span> -->
						<!-- <input type="range" oninput="drawStar(this)" value="1" step="1" min="0" max="10"></span> -->
					</div>
				</div>
			</c:forEach>
		</c:if>
	<!-- /음식 목록 -->

	<!-- 맛집 추천버튼 -->
	<div id="pageBar">
		<c:out value="${pageBar }" escapeXml='false'/>
	</div>
	<!-- /맛집 추천버튼 -->

	<!-- 페이지바 -->
	<div class="board-pasing">
		<nav aria-label="notice-pagenav">
		</nav>
	</div>
	<!-- /페이지바 -->

	</div>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
