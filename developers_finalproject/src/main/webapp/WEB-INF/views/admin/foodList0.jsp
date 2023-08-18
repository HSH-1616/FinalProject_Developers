<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<jsp:include page="/WEB-INF/views/admin/common/adminheader.jsp"/>
<script src="${path}/js/admin/foodList.js"></script>
<!-- 본문 내용-->
<style>
	#search-tourisAreaId{
	display:none;
	}
</style>
	<div class="container-fluid full-width row">
	    <div class="col my-5">
	        <h3 class="fs-4 mb-3">맛집 관리</h3>
	        <div>
	        	<span>총 ${nd+totalData}개(미승인 : ${nd})</span>
	        </div>
			<input type="hidden" class="allowFlag" value="${allow}">

			<ul class="nav nav-tabs" id="myTab" role="tablist">
				<li class="nav-item" role="presentation">
					<button class="nav-link active" id="non-approve-tab" data-bs-toggle="tab"
						data-bs-target="#non-approve-tab-pane" type="button" role="tab"
						aria-controls="non-approve-tab-pane" aria-selected="true">미승인 목록</button>
				</li>
				<li class="nav-item" role="presentation">
					<button class="nav-link" id="approve-tab" data-bs-toggle="tab"
						data-bs-target="#approve-tab-pane" type="button" role="tab"
						aria-controls="approve-tab-pane" aria-selected="false">승인 목록</button>
				</li>
			</ul>

				<div id="search-container" class="d-flex w-30 h-10" style="float:right;">
		        	<!-- 검색타입 : &nbsp;
		        	<select id="searchType" class="mb-3" style="width:100px; height:30px;">
		        		<option value="tourisName" ${not empty tourisName ? "selected":""}>맛집 이름</option>
		        		<option value="tourisAreaId" ${not empty tourisAreaId ? "selected":""}>메뉴</option>
		        	</select>
		        	<div id="search-tourisName">
		        		<form action="${path}/admin/selectFoodList" method="get">
		        			<input type="hidden" name="" value="" >
		        			<input type="text" name="tourisName" size="25" placeholder="검색할 키워드를 입력하세요" 
		        			value="${not empty tourisName ? tourisName:''}">
		        			<button type="submit" class="btn btn-outline-dark btn-sm">검색</button>
		        		</form>
		        	</div> -->
		        </div>	
	        <div class="col">
				<!-- 미승인 목록 -->
				<div class="tab-content" id="myTabContent">
					<div class="tab-pane fade show active" id="non-approve-tab-pane"
						role="tabpanel" aria-labelledby="non-approve-tab" tabindex="0">
							<table class="table bg-white rounded shadow-sm table-hover text-center align-middle">
								<thead>
									<tr>
										<th scope="col">No</th>
										<th scope="col">이름</th>
										<th scope="col">장소</th>
										<th scope="col">연락처</th>
										<th scope="col">상세보기</th>
										<th scope="col">삭제하기</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${nf[0].allow != 0}">
										<td colspan="6">처리할 데이터가 없습니다.</td>
									</c:if>
									<c:if test="${nf[0].allow == 0}">
										<c:forEach var="n" items="${nf}" varStatus="no">
											<tr>
												<td>${n.foodNo}</td>
												<td>${n.foodName}</td>
												<td>${n.foodAddress}</td>
												<td>${n.foodPhone}</td>
												<td><button type="button" class="btn btn-outline-danger btn-sm" onclick="fn_foodInfo(${f.foodNo});">상세보기</button></td>
												<td><button type="button" class="btn btn-outline-dark btn-sm" onclick="fn_deleteFood(${f.foodNo});">삭제하기</button></td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>

						<div class="pageBar">
							<c:if test="${not empty pageBar }">
								${npageBar}
							</c:if>
						</div>
					</div>
					
					<!-- 승인 목록 -->
					<!-- <div class="tab-pane fade" id="approve-tab-pane"
						role="tabpanel" aria-labelledby="approve-tab" tabindex="0">
						<table class="table bg-white rounded shadow-sm table-hover text-center align-middle">
							<thead>
								<tr>
									<th scope="col">No</th>
									<th scope="col">이름</th>
									<th scope="col">장소</th>
									<th scope="col">연락처</th>
									<th scope="col">상세보기</th>
									<th scope="col">삭제하기</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="f" items="${foods}" varStatus="no">
									<tr>
										<td>${f.foodNo}</td>
										<td>${f.foodName}</td>
										<td>${f.foodAddress}</td>
										<td>${f.foodPhone}</td>
										<td><button type="button" class="btn btn-outline-danger btn-sm" onclick="fn_foodInfo(${f.foodNo});">상세보기</button></td>
										<td><button type="button" class="btn btn-outline-dark btn-sm" onclick="fn_deleteFood(${f.foodNo});">삭제하기</button></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class="pageBar" onclick="fn_changeAllow();">
							<c:if test="${not empty pageBar }">
								${pageBar}
							</c:if>
						</div>
					</div> -->
				</div>
	        </div>
	    </div>
	    
	</div>

	<script>
		
		// function getContextPath() {
			// 	var hostIndex = location.href.indexOf(location.host) + location.host.length;
			// 	return location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));
			// };
			
			// function pageDetail(tourisId){
// 	location.assign(getContextPath()+'/tourisDetail/selectById?tourisId='+tourisId);
// }
// $(()=>{
//    	$("#searchType").change(e=>{
//    		const type=$(e.target).val();
// 		$(e.target).parent().find("div").css("display","none");
// 			$("#search-"+type).css("display","inline-block");
//    	});
//    	$("#searchType").change();

// })

// // ======================= 지환 ====================
// function fn_foodInfo(foodNo){
// 	location.assign(getContextPath()+'/admin/selectFoodByFoodNo?foodNo='+foodNo);
// }
// function fn_deleteFood(foodNo){
// 	const result = confirm("정말 삭제하시겠습니까?");
// 	if(result == true){
// 		location.assign(getContextPath()+'/admin/deleteFoodByFoodNo?foodNo='+foodNo);
// 	}
// }

function fn_foodInfo(foodNo){
	location.assign(getContextPath()+'/selectFoodByFoodNo?foodNo='+foodNo);
}
function fn_deleteFood(foodNo){
	const result = confirm("정말 삭제하시겠습니까?");
	if(result == true){
		location.assign(getContextPath()+'/deleteFoodByFoodNo?foodNo='+foodNo);
	}
}



let allow = '${allow}';

$("#approve-tab").click(function(){
	allow = 1;
	console.log(allow);
	location.assign(getContextPath()+'/selectFoodList?allow='+allow);
});

$("#non-approve-tab").click(function(){
	allow = 0;
	console.log(allow);
	location.assign(getContextPath()+'/selectFoodList?allow='+allow);
});

function fn_changeAllow(){
	const flag = confirm("1추가?");
	if(flag == true){
		
		
	}
};

$(document).ready(function(){
	console.log(allow);
	if(allow == 1){	
		$("#approve-tab").click();
	}
});
</script>
<jsp:include page="/WEB-INF/views/admin/common/adminfooter.jsp"/>