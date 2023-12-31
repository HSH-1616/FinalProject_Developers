<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="path" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${path }/css/food/foodUpdate.css" />

<head>

   <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet"/>
    <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script
      type="module"
      src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"
    >
	</script>
    <script
      nomodule
      src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"
    >
    </script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

    <link rel="stylesheet" href="css/header.css"/>
    <link rel="stylesheet" href="css/foodUpdate.css"/>
    
</head>

<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<section>
	
	<div class="food_update_theme">
    <p>맛집 추천하기</p>
  </div>
	<div class="food_update">

		<form id="foodRecommendForm" action="${pageContext.request.contextPath}/food/foodRegist" method="post" enctype="multipart/form-data">
			<table class="table">
				<tr>
					<th>상호명</th>
					<td><input type="text" class="" placeholder="상호명을 입력해주세요."
						name="foodName" id="" required></td>
				</tr>
				<tr>
					<th>상세 위치</th>
					<td><div style="display: flex;">
							<input type="text" id="sample6_postcode" placeholder="우편번호"
								name="foodAddress"> <input type="button"
								onclick="sample6_execDaumPostcode()" value="주소찾기"
								style="margin-left: 10px;">
						</div> <input type="text" id="sample6_address" placeholder="주소"><br>
						<input type="text" id="sample6_detailAddress" placeholder="상세주소"><br>
						<input type="text" id="sample6_extraAddress" placeholder="참고항목">
					</td>
				</tr>
				<tr>
					<th>추천 메뉴</th>
					<td><input type="text" class="" placeholder="메뉴 입력해주세요."
						name="" id="" required></td>
				</tr>
				<tr>
					<th>첨부사진</th>
					<td>
						<input name="file" type="file" class="real-upload" accept="image/*" required multiple>
						<div class="upload"></div>
						<ul class="image-preview"></ul>
					</td>
				</tr>
				<div id="registBtn">
					<input type="submit" value="등록하기">
				</div>
			</table>
		</form>
	</div>

	<!-- 맛집 등록하는 버튼 -->
    <<!-- div class="registBtnCon">
      <div class="registBtn">
        <button type=button id="registOkBtn" onclick="submitFoodRecommendForm();">등록하기</button>
      </div>
    </div> -->
    
    <!-- /맛집 등록하는 버튼 -->

</section>

<script src="${path }/js/food/foodUpdate.js"/>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
