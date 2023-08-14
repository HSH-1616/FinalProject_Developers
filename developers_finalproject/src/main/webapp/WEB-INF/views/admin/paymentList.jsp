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
<script>
	/* console.log("${tourises[0]}"); */
	/* console.log("${members}"); */
</script>
	<div class="container-fluid full-width row">
	    <div class="col my-5">
	        <h3 class="fs-4 mb-3">결제 관리</h3>
	        <div>
	        	<ul class="nav">
				  <li class="nav-item">
				    <a class="nav-link active" aria-current="page" href="#">환불대기</a>
				  </li>
				  <li class="nav-item">
				    <a class="nav-link" href="#">환불완료</a>
				  </li>
				</ul>
	        </div>	
	        <div class="col">
	            <table class="table bg-white rounded shadow-sm table-hover text-center align-middle">
	                <thead>
	                    <tr>
	                        <th scope="col">지역명</th>
	                        <th scope="col">관광지명</th>
	                        <th scope="col">관광지주소</th>
	                        <th scope="col">메인이미지</th>
	                        <th scope="col">홈페이지</th>
	                        <th scope="col">전화번호</th>
	                        <th scope="col">관광지관리</th>
	                    </tr>
	                </thead>
	                <tbody>
	                <c:forEach var="t" items="${tourises}" varStatus="no">
	                    <tr>
	                        <td>${t.tourisName}</td>
	                        <td>${t.tourisAddress}</td>
	                        <c:choose>
		                        <c:when test="${not empty t.tourismainImge}">
			                        <td>있음</td>
		                        </c:when>
		                        <c:otherwise>
			                        <td>-</td>
		                        </c:otherwise>
	                        </c:choose>
	                        <c:choose>
		                        <c:when test="${not empty t.tourispage }">
		                       		<td>있음</td>
		                        </c:when>
		                        <c:otherwise>
			                        <td>-</td>
		                        </c:otherwise>
	                        </c:choose>
	                        <c:choose>
	                        	<c:when test="${not empty t.tourisPhone or not empty t.tourisTel}">
			                        <td>${t.tourisPhone} ${t.tourisTel}</td>
		                        </c:when>
		                        <c:otherwise>
		                        	<td>-</td>
		                        </c:otherwise>
	                        </c:choose>
	                        <td><button type="button" class="btn btn-outline-dark btn-sm" onclick="pageDetail('${t.tourisId}')">상세보기</button></td>
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
$(()=>{
   	$("#searchType").change(e=>{
   		const type=$(e.target).val();
		$(e.target).parent().find("div").css("display","none");
			$("#search-"+type).css("display","inline-block");
   	});
   	$("#searchType").change();

})
	function selectTouris(){
		let index = $("#m-selectTouris option").index($("#m-selectTouris option:selected"));
		let tourisAreaId=$("#m-selectTouris").val();
		if(index==0){
			location.replace('${path}/admin/selectTourisAll');
		}else if(index==1){
			location.assign('${path}/admin/selectBytourisAreaId?tourisAreaId='+tourisAreaId);
		}else if(index==2){
			location.assign('${path}/admin/selectBytourisAreaId?tourisAreaId='+tourisAreaId);
		}else if(index==3){
			location.assign('${path}/admin/selectBytourisAreaId?tourisAreaId='+tourisAreaId);
		}else if(index==4){
			location.assign('${path}/admin/selectBytourisAreaId?tourisAreaId='+tourisAreaId);
		}else if(index==5){
			location.assign('${path}/admin/selectBytourisAreaId?tourisAreaId='+tourisAreaId);
		}else if(index==6){
			location.assign('${path}/admin/selectBytourisAreaId?tourisAreaId='+tourisAreaId);
		}else if(index==7){
			location.assign('${path}/admin/selectBytourisAreaId?tourisAreaId='+tourisAreaId);
		}else if(index==8){
			location.assign('${path}/admin/selectBytourisAreaId?tourisAreaId='+tourisAreaId);
		}else if(index==9){
			location.assign('${path}/admin/selectBytourisAreaId?tourisAreaId='+tourisAreaId);
		}else if(index==10){
			location.assign('${path}/admin/selectBytourisAreaId?tourisAreaId='+tourisAreaId);
		}else if(index==11){
			location.assign('${path}/admin/selectBytourisAreaId?tourisAreaId='+tourisAreaId);
		}else if(index==12){
			location.assign('${path}/admin/selectBytourisAreaId?tourisAreaId='+tourisAreaId);
		}else if(index==13){
			location.assign('${path}/admin/selectBytourisAreaId?tourisAreaId='+tourisAreaId);
		}else if(index==14){
			location.assign('${path}/admin/selectBytourisAreaId?tourisAreaId='+tourisAreaId);
		}else if(index==15){
			location.assign('${path}/admin/selectBytourisAreaId?tourisAreaId='+tourisAreaId);
		}else if(index==16){
			location.assign('${path}/admin/selectBytourisAreaId?tourisAreaId='+tourisAreaId);
		}else if(index==17){
			location.assign('${path}/admin/selectBytourisAreaId?tourisAreaId='+tourisAreaId);
		}
	};

/* 	function black(memberId){
		console.log(memberId);
		open("${path}/admin/blackpage?memberId="+memberId
				,"_blank","width=600, height=200, top=500,left=800");
	}
	function blackClear(memberId){
		console.log(memberId);
		$.get("${path}/admin/blackClear?memberId="+memberId,data=>{
			alert("블랙회원을 삭제하셨습니다.");
			location.assign("${path}/admin/selectTourisAll");
			location.reload();
		})
	} */
</script>
<jsp:include page="/WEB-INF/views/admin/common/adminfooter.jsp"/>