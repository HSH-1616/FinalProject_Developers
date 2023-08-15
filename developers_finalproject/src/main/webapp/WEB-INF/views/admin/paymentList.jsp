<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<jsp:include page="/WEB-INF/views/admin/common/adminheader.jsp"/>
<!-- 본문 내용-->
	<div class="container-fluid full-width row">
	    <div class="col my-5">
	        <h3 class="fs-4 mb-3">결제 관리</h3>
		     <ul class="nav nav-tabs" id="myTab" role="tablist">
			  <li class="nav-item" role="presentation">
			    <button class="nav-link active" id="home-tab" data-bs-toggle="tab" data-bs-target="#home-tab-pane" type="button" role="tab" aria-controls="home-tab-pane" aria-selected="true">환불대기</button>
			  </li>
			  <li class="nav-item" role="presentation">
			    <button class="nav-link" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile-tab-pane" type="button" role="tab" aria-controls="profile-tab-pane" aria-selected="false">환불완료</button>
			  </li>
			</ul>
			
			<div class="tab-content" id="myTabContent">
			  <div class="tab-pane fade show active" id="home-tab-pane" role="tabpanel" aria-labelledby="home-tab" tabindex="0">
			  	<table>
			  	<tr>1</tr>	
			  	</table>
			  </div>
			  <div class="tab-pane fade" id="profile-tab-pane" role="tabpanel" aria-labelledby="profile-tab" tabindex="0">
			  	<table>
			  	<tr>2</tr>
			  	</table>
			  </div>
			</div>
	    </div>
	</div>
<script>
function tourisUpdate(){
	$.get('${path}/admin/tourisUpdate',data=>{
		alert("데이터 업데이트 완료 됐습니다.");
		location.reload();
	})
	
}
function pageDetail(tourisId){
	location.assign('${path}/tourisDetail/selectById?tourisId='+tourisId);
}
</script>
<jsp:include page="/WEB-INF/views/admin/common/adminfooter.jsp"/>