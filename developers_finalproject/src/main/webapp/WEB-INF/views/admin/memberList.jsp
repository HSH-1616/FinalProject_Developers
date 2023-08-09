<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<jsp:include page="/WEB-INF/views/admin/common/adminheader.jsp"/>
<!-- 본문 내용-->
<script>
	console.log("${blacks}");
	/* console.log("${members}"); */
	console.log("${memberCategory}");
</script>
	<div class="container-fluid full-width row">
	    <div class="col my-5">
	        <h3 class="fs-4 mb-3">회원관리</h3>
			
			<div class="d-flex" style="float:right;">
				<div class="" style="width:150px;">
					<select class="form-select mb-3" aria-label="Default select example" id="m-selectMember" onchange="selectMember();">
					  <option value="A">전체회원</option>
					  <option value="K" ${memberCategory eq 'K' ?"selected":""}>카카오회원</option>
					  <option value="N" ${memberCategory eq 'N' ?"selected":""}>네이버회원</option>
					  <option value="G" ${memberCategory eq 'G' ?"selected":""}>구글회원</option>
					</select>
				</div>	        
			</div>
<%-- 			<div class="dropdown mb-2">
		  	<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
		    	회원분류
		  	</button>
			 	<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
				 	<li><a class="dropdown-item" href="${path}/admin/selectMemberAll">전체회원</a></li>
				    <li><a class="dropdown-item" href="${path}/admin/selectBySocial?memberCategory=K">카카오회원</a></li>
				    <li><a class="dropdown-item" href="${path}/admin/selectBySocial?memberCategory=N">네이버회원</a></li>
				    <li><a class="dropdown-item" href="${path}/admin/selectBySocial?memberCategory=G">구글회원</a></li>
			  	</ul>
			</div> --%>
	        <div class="col">
	            <table class="table bg-white rounded shadow-sm table-hover text-center align-middle">
	                <thead>
	                    <tr>
	                        <th scope="col" width="50">NO</th>
	                        <th scope="col">닉네임</th>
	                        <th scope="col">이메일</th>
	                        <th scope="col">회원분류</th>
	                        <th scope="col">가입일자</th>
	                        <th scope="col">회원상태</th>
	                        <th scope="col">회원관리</th>
	                    </tr>
	                </thead>
	                <tbody>
	                <c:forEach var="m" items="${members}" varStatus="no">
	                    <tr>
	                        <th scope="row">${no.index+1}</th>
	                        <td>${m.memberNickname}</td>
	                        <td>${m.memberEmail}</td>
	                        <c:choose>
		                        <c:when test="${m.memberCategory eq 'K'}">
			                        <td>카카오회원</td>
		                        </c:when>
		                        <c:when test="${m.memberCategory eq 'G'}">
			                        <td>구글회원</td>
		                        </c:when>
		                        <c:otherwise>
			                        <td>네이버회원</td>
		                        </c:otherwise>
	                        </c:choose>
	                        <td>${m.memberEnrolldate}</td>
	                        <c:choose>
	                        	<c:when test="${m.memberStatus eq '1'}">
			                        <td>정상회원</td>
		                        </c:when>
		                        <c:otherwise>
		                        	<td>블랙회원</td>
		                        </c:otherwise>
	                        </c:choose>
	                        <td><button type="button" class="btn btn-outline-dark btn-sm" onclick="black('${m.memberId}');">블랙</button></td>
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
   	    <div class="row my-5">
	        <h3 class="fs-4 mb-3">블랙회원관리</h3>
	        <div class="col">
	            <table class="table bg-white rounded shadow-sm table-hover text-center align-middle">
	                <thead>
	                    <tr>
	                        <th scope="col" width="50">NO</th>
	                        <th scope="col">닉네임</th>
	                        <th scope="col">이메일</th>
	                        <th scope="col">회원분류</th>
	                        <th scope="col">가입일자</th>
	                        <th scope="col">회원상태</th>
	                        <th scope="col">블랙사유</th>
	                        <th scope="col">블랙일자</th>
	                        <th scope="col">회원관리</th>
	                    </tr>
	                </thead>
	                <tbody>
	                <c:forEach var="b" items="${blacks}" varStatus="no">
	                    <tr>
	                        <th scope="row">${no.index+1}</th>
	                        <td>${b.blackList.memberNickname}</td>
	                        <td>${b.blackList.memberEmail}</td>
	                        <c:choose>
		                        <c:when test="${b.blackList.memberCategory eq 'K'}">
			                        <td>카카오회원</td>
		                        </c:when>
		                        <c:when test="${b.blackList.memberCategory eq 'G'}">
			                        <td>구글회원</td>
		                        </c:when>
		                        <c:otherwise>
			                        <td>네이버회원</td>
		                        </c:otherwise>
	                        </c:choose>
	                        <td>${b.blackList.memberEnrolldate}</td>
	                        <c:choose>
	                        	<c:when test="${b.blackList.memberStatus eq '1'}">
			                        <td>정상회원</td>
		                        </c:when>
		                        <c:otherwise>
		                        	<td>블랙회원</td>
		                        </c:otherwise>
	                        </c:choose>
	                        <td>${b.blackReason }</td>
	                        <td>${b.blackDate }</td>
	                        <td><button type="button" class="btn btn-success btn-sm" onclick="blackClear('${b.blackList.memberId}');">해제</button></td>
	                    </tr>
	                </c:forEach>
	                </tbody>
	            </table>
	        </div>
	    </div>
	    
	</div>
	<script>
	const selectMember=()=>{
		let index = $("#m-selectMember option").index($("#m-selectMember option:selected"));
		let memberSt=$("#m-selectMember").val();
		if(index==0){
			location.replace('${path}/admin/selectMemberAll');
		}else if(index==1){
			location.assign('${path}/admin/selectBySocial?memberCategory=K');
		}else if(index==2){
			location.assign('${path}/admin/selectBySocial?memberCategory=N');
		}else if(index==3){
			location.assign('${path}/admin/selectBySocial?memberCategory=G');
		}
	};
	function black(memberId){
		console.log(memberId);
		open("${path}/admin/blackpage?memberId="+memberId
				,"_blank","width=600, height=200, top=500,left=800");
	}
	function blackClear(memberId){
		console.log(memberId);
		$.get("${path}/admin/blackClear?memberId="+memberId,data=>{
			alert("블랙회원을 삭제하셨습니다.");
			location.assign("${path}/admin/selectMemberAll");
			location.reload();
		})
	}
	</script>
<jsp:include page="/WEB-INF/views/admin/common/adminfooter.jsp"/>