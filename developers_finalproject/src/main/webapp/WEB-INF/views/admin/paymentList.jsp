<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script src="sweetalert2.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<link rel="stylesheet" href="sweetalert2.min.css">
<c:set var="path" value="${pageContext.request.contextPath }" />
<jsp:include page="/WEB-INF/views/admin/common/adminheader.jsp" />
<!-- 본문 내용-->
<div class="container-fluid full-width row">
	<div class="col my-5">
		<h3 class="fs-4 mb-3">결제 관리</h3>
			 <div class="col">
				<table
					class="table bg-white rounded shadow-sm table-hover text-center align-middle">
					<thead>
						<tr>
							<th scope="col">결제번호</th>
							<th scope="col">숙박업소 ID</th>
							<th scope="col">결제자 ID</th>
							<th scope="col">결제자 이름</th>
							<th scope="col">결제 수단</th>
							<th scope="col">결제 금액</th>
							<th scope="col">환불 가능 금액</th>
							<th scope="col">결제 일자</th>
							<th scope="col">숙박일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="ap" items="${ap}">
							<c:set var="now" value="<%=new java.util.Date()%>" />
							<c:set var="nowDate">
								<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" />
							</c:set>
							<fmt:parseDate value="${nowDate}" var="nowCheck"
								pattern="yyyy-MM-dd" />
							<fmt:parseNumber value="${nowCheck.time / (1000*60*60*24)}"
								integerOnly="true" var="nowRefund"></fmt:parseNumber>
							<fmt:parseDate value="${ap.arv.checkIn }" var="checkInDate"
								pattern="yyyy-MM-dd" />
							<fmt:parseNumber value="${checkInDate.time / (1000*60*60*24)}"
								integerOnly="true" var="checkIn"></fmt:parseNumber>
							<fmt:parseDate value="${ap.arv.checkOut }" var="checkOutDate"
								pattern="yyyy-MM-dd" />
							<fmt:parseNumber value="${checkOutDate.time / (1000*60*60*24)}"
								integerOnly="true" var="checkOut"></fmt:parseNumber>

							<c:if test="${checkIn-nowRefund>3}">
								<c:set var="refundPrice" value="${ap.apPrice}"></c:set>
							</c:if>

							<c:if test="${checkIn-nowRefund<=3 && nowRefund-checkIn>1 }">
								<c:set var="refundPrice">
									<fmt:formatNumber value="${ap.apPrice*0.5}" pattern="0" />
								</c:set>
							</c:if>

							<c:if test="${checkIn-nowRefund<=1&&nowRefund<checkIn }">
								<c:set var="refundPrice">
									<fmt:formatNumber value="${ap.apPrice*0.2}" pattern="0" />
								</c:set>
							</c:if>
							<c:if test="${checkIn<nowRefund}">
								<c:set var="refundPrice" value="0">
								</c:set>
							</c:if>

							<input type="hidden" name="apId" value="${ap.apId }">
							<input type="hidden" name="refundPrice" value="${refundPrice}" />
							<tr>
								<td class="acId">${ap.apOrderId }</td>
								<td>${ap.acDetail.acId}</td>
								<td>${ap.member.memberId}</td>
								<td>${ap.member.memberNickname}</td>
								<c:if test="${fn:substring(ap.apOrderId,0,1)=='C' }">
									<td>카드 결제</td>
								</c:if>
								<c:if test="${fn:substring(ap.apOrderId,0,1)=='K' }">
									<td>카카오 페이</td>
								</c:if>
								<td><fmt:formatNumber value="${ap.apPrice}" type="currency"
										currencySymbol="₩" /></td>
								<c:if test="${checkIn>nowRefund}">
									<td><fmt:formatNumber value="${refundPrice}"
											type="currency" currencySymbol="₩" /></td>
								</c:if>
								<c:if test="${checkIn<nowRefund}">
									<td>-</td>
								</c:if>
								<td>${ap.apDate}</td>
								<td>${ap.arv.checkIn}~${ap.arv.checkOut}</td>
								<td><button type="button"
										class="btn btn-outline-dark btn-sm"
										onclick="location.href='${path}/admin/paymentDetail?orderId=${ap.apOrderId}'">상세보기</button></td>

								<c:if test="${checkIn>nowRefund}">
									<c:if test="${fn:substring(ap.apOrderId,0,1)=='C' }">
										<td><button button type="button"
												class="btn btn-danger btn-sm" onclick="refundCard(${ap.apId},${refundPrice},${checkIn},${nowRefund})">결제
												취소</button></td>
									</c:if>
									<c:if test="${fn:substring(ap.apOrderId,0,1)=='K' }">
										<td><button button type="button"
												class="btn btn-danger btn-sm" onclick="refundKaKao(${ap.apId},${refundPrice},${checkIn},${nowRefund})">결제
												취소</button></td>
									</c:if>
								</c:if>
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

/* var apId=$("input[name=apId]").val()
var refundPrice=$("input[name=refundPrice]").val()
var checkIn=$()
var nowRefund */

function refundKaKao(apId,refundPrice,checkIn,nowRefund) {
	var diff=checkIn-nowRefund
	Swal.fire({
		icon: 'success',
		iconColor: '#20c997',
		title: "체크인 기준 "+diff+"일전\n환불 가능 금액은 "+refundPrice+"원 입니다.",
		confirmButtonText: "승인",
		confirmButtonColor: "#20c997",
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax({
				url: "${path}/pay/kakaoRefundAdmin",
				method: "POST",
				data: {
					apId: apId,
					refundPrice: refundPrice,
				},
				success: function(data) {
					Swal.fire({
						icon: 'success',
						iconColor: '#20c997',
						title: "승인 완료",
						confirmButtonText: "확인",
						confirmButtonColor: "#20c997",
					}).then((result) => {
						if (result.isConfirmed) {
							 document.location.href = document.location.href;
						}
					})
				},
				error: function(result) {
					alert("결제금액 환불실패.");
					 document.location.href = document.location.href;
				}
			})					
		}
	})
	
	
}

function refundCard(apId,refundPrice,checkIn,nowRefund) {
	var diff=checkIn-nowRefund
	Swal.fire({
		icon: 'success',
		iconColor: '#20c997',
		title: "체크인 기준 "+diff+"일전\n환불 가능 금액은 "+refundPrice+"원 입니다.",
		confirmButtonText: "승인",
		confirmButtonColor: "#20c997",
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax({
				url: "${path}/pay/refundIamportAdmin",
				method: "POST",
				data: {
					apId: apId,
					refundPrice: refundPrice,
				},
				success: function(data) {
					Swal.fire({
						icon: 'success',
						iconColor: '#20c997',
						title: "승인 완료",
						confirmButtonText: "확인",
						confirmButtonColor: "#20c997",
					}).then((result) => {
						if (result.isConfirmed) {
							 document.location.href = document.location.href;
						}
					})
				},
				error: function(result) {
					alert("결제금액 환불실패.");
					 document.location.href = document.location.href;
				}
			})					
		}
	})
}

</script>

<jsp:include page="/WEB-INF/views/admin/common/adminfooter.jsp" />