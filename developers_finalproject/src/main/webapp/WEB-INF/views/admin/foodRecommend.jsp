<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<jsp:include page="/WEB-INF/views/admin/common/adminheader.jsp"/>
<!-- 본문 내용-->
<style>
	#search-tourisAreaId{
	display:none;
	}
</style>

<section>
	<div class="container-fluid full-width row">
	    <div class="col my-5">
	        <h3 class="fs-4 mb-3">맛집 추천</h3>
	        <div class="col">
	            <table class="table bg-white rounded shadow-sm table-hover text-center align-middle">
	                <thead>
	                    <tr>
	                        <th scope="col">No</th>
	                        <th scope="col">이름</th>
	                        <th scope="col">장소</th>
	                        <th scope="col">승인하기</th>
	                        <th scope="col">반려하기</th>
	                    </tr>
	                </thead>
	                <tbody>
	                <c:forEach var="f" items="${foods}" varStatus="no">
	                    <tr>
							<td>${f.foodNo}</td>
	                        <td>${f.foodName}</td>
	                        <td>${f.foodAddress}</td>
	                        <td><button type="button" class="btn btn-outline-dark btn-sm" onclick="fn_foodInfo(${f.foodNo});">승인</button></td>
	                        <td><button type="button" class="btn btn-outline-dark btn-sm" onclick="fn_deleteFood(${f.foodNo});">반려</button></td>
	                    </tr>
	                </c:forEach>
	                </tbody>
	            </table>
	            <div class="pageBar">
	            <c:if test="${not empty pageBar }">
	            	${pageBar}
	           	</c:if>
	            </div>
	        </div>
	    </div>
	    
	</div>
	
	</section>
<script>
function fn_foodInfo(foodNo){
		location.assign('${path}/admin/selectFoodByFoodNo?foodNo='+foodNo);
		const result=confirm("승인하시겠습니까?");
		if(result==true){
			location.assign('${path}/admin/foodRecommend')
		}
	}
	function fn_deleteFood(foodNo){
		const result = confirm("반려하시겠습니까?");
		if(result == true){
			location.assign('${path}/admin/deleteFoodByFoodNo?foodNo='+foodNo);
		}
	}
</script>