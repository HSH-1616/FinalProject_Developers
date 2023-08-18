<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<jsp:include page="/WEB-INF/views/admin/common/adminheader.jsp" />
<script src="sweetalert2.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<link rel="stylesheet" href="sweetalert2.min.css">
<div class="container-fluid full-width row">
	<div class="col my-5">
		<h3 class="fs-4 mb-3">관광지 관리</h3>
		<div style="display:flex;justify-content:space-between;margin-bottom:30px">
			<span>숙박업소 : ${totalData}개</span>
			<button type="button" class="btn btn-success btn-sm"
				onclick="location.href='${path}/ac/acRegist'">숙박업소 등록</button>
		</div>
		<div class="col">
			<table
				class="table bg-white rounded shadow-sm table-hover text-center align-middle">
				<thead>
					<tr>
						<th scope="col">숙박업소ID</th>
						<th scope="col">숙박업소명</th>
						<th scope="col">주소</th>
						<th scope="col">1박당 가격</th>
						<th scope="col">최대 인원</th>
						<th scope="col">방 개수</th>
						<th scope="col">침대 개수</th>
						<th scope="col">욕실 개수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="ac" items="${ac}">
						<tr>
							<td class="acId">${ac.acId }</td>
							<td>${ac.acTitle }</td>
							<td>${ac.acAddress}</td>
							<td><fmt:formatNumber value="${ac.acPrice}" type="currency" currencySymbol="₩"  /></td>
							<td>${ac.acPeople}</td>
							<td>${ac.acRoom}</td>
							<td>${ac.acBed}</td>
							<td>${ac.acBathRoom}</td>
							<td><button type="button"
									class="btn btn-outline-dark btn-sm"
									onclick="location.href='${path}/ac/acDetail?no=${ac.acId}'">상세보기</button></td>
							<td><button type="button"
									class="btn btn-outline-dark btn-sm"
									onclick="location.href='${path}/ac/updateRegist?acId=${ac.acId}'">수정</button></td>
							<td><button type="button"
									class="btn btn-danger btn-sm"
									onclick="warningDelete('${ac.acId}')">삭제</button></td>
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
function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(
		hostIndex,
		location.href.indexOf("/", hostIndex + 1)
	);
}

function warningDelete(acId) {
	console.log(acId)
	Swal.fire({
		icon: 'warning',
		iconColor: '#b31312',
		title: "정말 삭제하시겠습니까?",
		showCancelButton: true,
		confirmButtonText: "확인",
		confirmButtonColor: "#b31312",
		cancelButtonText: '취소',
		cancelButtonColor: "#afafaf",
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax({
				url: "${path}/ac/deleteRegist",
				type: "get",
				data: { acId: acId},
				success: function(result) {
					if (result > 0) {
						Swal.fire({
							icon: 'success',
							iconColor: '#20c997',
							title: "삭제완료",
							confirmButtonText: "확인",
							confirmButtonColor: "#20c997",
						}).then((result) => {
							if (result.isConfirmed) {
								document.location.href = document.location.href;
							}
						})												
					}
				},
				error: function() {
					location.href = "${path}/ac/acError"
				}
			});
		}
	})
}
	
</script>