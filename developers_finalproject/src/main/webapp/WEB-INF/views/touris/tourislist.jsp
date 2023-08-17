<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/paginationjs/2.1.4/pagination.min.js"></script>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/paginationjs/2.1.4/pagination.css" />

<link rel="stylesheet" href="css/header.css" />

</head>

<jsp:include page="/WEB-INF/views/common/header.jsp" />

<section>

	<!-- 맛집 리스트 순서 -->
	<div id="foodList_theme">
		<div id="food_main_theme">
			<p>관광지 목록</p>
		</div>
	</div>
	<BR>
	<div class="food_main_list">
		<c:if test="${not empty tourislist }">
			<c:forEach var="t" items="${tourislist }">
				<div class="food_list">
					<a class="pig" href="${path}/tourisDetail/selectById?tourisId=${t.tourisId}">
						<img alt="대표이미지" src="${t.tourismainImge}" style="width: 300px; height: 300px;"><br>
					</a>
					<div class="food_menu">${t.tourisName }</div>
					<div class="food_address" style="color: #828282;">${t.tourisAddress}</div>
				</div>
			</c:forEach>
		</c:if>
	</div>
	<BR>
	<!-- 페이지바 -->
	<div id="pageBar">
		<c:out value="${pageBar }" escapeXml='false' />
	</div>
	<!-- /페이지바 -->

</section>
<script src="${path }/js/touris/tourislist.js" />

<jsp:include page="/WEB-INF/views/common/footer.jsp" />