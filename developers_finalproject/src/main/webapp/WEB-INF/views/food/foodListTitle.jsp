<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="path" value="${pageContext.request.contextPath }"/>
<link rel="stylesheet" href="${path }/css/food/foodList.css" />
		<c:if test="${not empty foods }">
			<c:forEach var="f" items="${foods }">
				<div class="food_list">
					<div class="con-like" style="position: relative; z-index: 2; ">
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
								<img alt="대표이미지" src="${fp.fpName}"
								style="width: 300px; height: 300px;"><br>
							</a>
						</c:if>
						<c:if test="${fp.fpMain != 1}"></c:if>
					</c:forEach>
					<div class="food_menu">${f.foodName }</div>
					<div class="food_address" style="color: #828282;">${f.foodAddress}</div>
					<div class="countDiv">
						<img class="heart" src="${path }/images/food/fillheart.svg">
						<span class="heart_count">0</span> <img class="comment"
							src="${path }/images/food/comment.png"> <span
							class="comment_count">${f.foodReadCount}</span>
						<!-- <span class="star"> ★★★★★ <span>★★★★★</span> -->
						<!-- <input type="range" oninput="drawStar(this)" value="1" step="1" min="0" max="10"></span> -->
					</div>
				</div>
			</c:forEach>
		</c:if>


	<div id="pageBar">
		<c:out value="${pageBar }" escapeXml='false' />
	</div>
	<!-- /페이지바 -->

