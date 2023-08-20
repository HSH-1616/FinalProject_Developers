<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<jsp:include page="/WEB-INF/views/admin/common/adminheader.jsp"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<!-- 본문 내용-->
	<div class="container-fluid px-4">
	    <div class="row my-5">
	        <h3 class="fs-4 mb-3">메인화면</h3>
	        <div class="col">

                <div class="d-flex justify-content-around mx-5">
                    <div class="card text-dark bg-light mb-3" style="width: 17rem;">
                        <div class="card-header">Touris</div>
                        <div class="card-body d-flex justify-content-between">
                            <h5 class="card-title text-center">관광지 수: ${tour}개</h5>
                            <i class="fas fa-compass me-2 align-middle" style="font-size:26px;"></i>
                        </div>
                    </div>
    
                    <div class="card text-dark bg-light mb-3" style="width: 17rem;">
                        <div class="card-header">Food</div>
                        <div class="card-body d-flex justify-content-between">
                            <h5 class="card-title text-center">맛집 수: ${foodCount}개</h5>
                            <i class="fas fa-pizza-slice me-2 align-middle" style="font-size:26px;"></i>
                        </div>
                    </div>
    
                    <div class="card text-dark bg-light mb-3" style="width: 17rem;">
                        <div class="card-header">Accommodation</div>
                        <div class="card-body d-flex justify-content-between">
                            <h5 class="card-title text-center">숙박업소 수: ${ac}개</h5>
                            <i class="fas fa-hotel me-2 align-middle" style="font-size:26px;"></i>
                        </div>
                    </div>

                    <div class="card text-dark bg-light mb-3" style="width: 17rem;">
                        <div class="card-header">Member</div>
                        <div class="card-body d-flex justify-content-between">
                            <h5 class="card-title text-center">회원 수: ${member}명</h5>
                            <i class="fas fa-user me-2 align-middle" style="font-size:26px;"></i>
                        </div>
                    </div>
                </div>
	        </div>
	    </div>
	</div>

<jsp:include page="/WEB-INF/views/admin/common/adminfooter.jsp"/>