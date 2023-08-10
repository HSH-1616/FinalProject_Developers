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
	        <h3 class="fs-4 mb-3">관광지 관리</h3>
				<div id="search-container" class="d-flex w-30 h-10" style="float:right;">
		        	검색타입 : &nbsp;
		        	<select id="searchType" class="mb-3" style="width:100px; height:30px;">
		        		<option value="tourisName" ${not empty tourisName ? "selected":""}>관광지명</option>
		        		<option value="tourisAreaId" ${not empty tourisAreaId ? "selected":""}>지역</option>
		        	</select>
		        	<div id="search-tourisName">
		        		<form action="${path}/admin/searchTouris" method="get">
		        			<input type="hidden" name="" value="" >
		        			<input type="text" name="tourisName" size="25" placeholder="검색할 관광지명을 입력하세요" 
		        			value="${not empty tourisName ? tourisName:''}">
		        			<button type="submit">검색</button>
		        		</form>
		        	</div>
		        	<div id="search-tourisAreaId">
						<select class="mb-3" aria-label="Default select example" id="m-selectTouris" onchange="selectTouris();" style="width:100px; height:30px;">
							  <option value="0">전체지역</option>
							  <option value="1" ${tourisAreaId eq '1' ?"selected":""}>서울</option>
							  <option value="2" ${tourisAreaId eq '2' ?"selected":""}>인천</option>
							  <option value="3" ${tourisAreaId eq '3' ?"selected":""}>대전</option>
							  <option value="4" ${tourisAreaId eq '4' ?"selected":""}>대구</option>
							  <option value="5" ${tourisAreaId eq '5' ?"selected":""}>광주</option>
							  <option value="6" ${tourisAreaId eq '6' ?"selected":""}>부산</option>
							  <option value="7" ${tourisAreaId eq '7' ?"selected":""}>울산</option>
							  <option value="8" ${tourisAreaId eq '8' ?"selected":""}>세종</option>
							  <option value="31" ${tourisAreaId eq '31' ?"selected":""}>경기도</option>
							  <option value="32" ${tourisAreaId eq '32' ?"selected":""}>강원도</option>
							  <option value="33" ${tourisAreaId eq '33' ?"selected":""}>충청북도</option>
							  <option value="34" ${tourisAreaId eq '34' ?"selected":""}>충청남도</option>
							  <option value="35" ${tourisAreaId eq '35' ?"selected":""}>경상북도</option>
							  <option value="36" ${tourisAreaId eq '36' ?"selected":""}>경상남도</option>
							  <option value="37" ${tourisAreaId eq '37' ?"selected":""}>전라북도</option>
							  <option value="38" ${tourisAreaId eq '38' ?"selected":""}>전라남도</option>
							  <option value="39" ${tourisAreaId eq '39' ?"selected":""}>제주도</option>
						</select>
		        	</div>
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
                    	<c:choose>
	                    	<c:when test="${t.tourisAreaId eq '1'}">
	                        	<th scope="row">서울</th>
	                        </c:when>
         			        <c:when test="${t.tourisAreaId eq '2'}">
	                        	<th scope="row">인천</th>
	                        </c:when>
	                        <c:when test="${t.tourisAreaId eq '3'}">
	                        	<th scope="row">대전</th>
	                        </c:when>
	                        <c:when test="${t.tourisAreaId eq '4'}">
	                        	<th scope="row">대구</th>
	                        </c:when>
	                        <c:when test="${t.tourisAreaId eq '5'}">
	                        	<th scope="row">광주</th>
	                        </c:when>
	                        <c:when test="${t.tourisAreaId eq '6'}">
	                        	<th scope="row">부산</th>
	                        </c:when>
	                        <c:when test="${t.tourisAreaId eq '7'}">
	                        	<th scope="row">울산</th>
	                        </c:when>
	                        <c:when test="${t.tourisAreaId eq '8'}">
	                        	<th scope="row">세종</th>
	                        </c:when>
	                        <c:when test="${t.tourisAreaId eq '31'}">
	                        	<th scope="row">경기도</th>
	                        </c:when>
	                        <c:when test="${t.tourisAreaId eq '32'}">
	                        	<th scope="row">강원도</th>
	                        </c:when>
	                        <c:when test="${t.tourisAreaId eq '33'}">
	                        	<th scope="row">충청북도</th>
	                        </c:when>
	                        <c:when test="${t.tourisAreaId eq '34'}">
	                        	<th scope="row">충청남도</th>
	                        </c:when>
	                        <c:when test="${t.tourisAreaId eq '35'}">
	                        	<th scope="row">경상북도</th>
	                        </c:when>
	                        <c:when test="${t.tourisAreaId eq '36'}">
	                        	<th scope="row">경상남도</th>
	                        </c:when>
	                        <c:when test="${t.tourisAreaId eq '37'}">
	                        	<th scope="row">전라북도</th>
	                        </c:when>
	                        <c:when test="${t.tourisAreaId eq '38'}">
	                        	<th scope="row">전라남도</th>
	                        </c:when>
	                        <c:when test="${t.tourisAreaId eq '39'}">
	                        	<th scope="row">제주도</th>
	                        </c:when>
                        </c:choose>
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
	                        <td><button type="button" class="btn btn-outline-dark btn-sm" onclick="">삭제</button></td>
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