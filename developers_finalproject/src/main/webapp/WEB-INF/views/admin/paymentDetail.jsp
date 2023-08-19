<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<jsp:include page="/WEB-INF/views/admin/common/adminheader.jsp" />
<script src="sweetalert2.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<link rel="stylesheet" href="sweetalert2.min.css">
<link rel="stylesheet"
	href="${path }/css/accommodation/paymentDetail.css" />
<section>
	<div class="hotelPayCon">
		<div class="hotelPay">
			<div class="hotelPayDetailCon">
				<div class="hotelPayDetail">
					<div>
						<div id="hotelPayDetailTitle">
							<c:forEach var="af" items="${ra.acDetail.acFiles }">
								<c:if test="${fn:contains(af.afMain,'Y')}">
									<img src="${path }/images/upload/accommodation/${af.afName}"
										alt="" />
								</c:if>
							</c:forEach>
							
							<c:set var="now" value="<%=new java.util.Date()%>" />
							<c:set var="nowDate">
								<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" />
							</c:set>
							<fmt:parseDate value="${nowDate}" var="nowCheck"
								pattern="yyyy-MM-dd" />
							<fmt:parseNumber value="${nowCheck.time / (1000*60*60*24)}"
								integerOnly="true" var="nowRefund"></fmt:parseNumber>
							<fmt:parseDate value="${ra.arv.checkIn }" var="checkInDate"
								pattern="yyyy-MM-dd" />
							<fmt:parseNumber value="${checkInDate.time / (1000*60*60*24)}"
								integerOnly="true" var="checkIn"></fmt:parseNumber>
							<fmt:parseDate value="${ra.arv.checkOut }" var="checkOutDate"
								pattern="yyyy-MM-dd" />
							<fmt:parseNumber value="${checkOutDate.time / (1000*60*60*24)}"
								integerOnly="true" var="checkOut"></fmt:parseNumber>
							
							<c:if test="${checkIn-nowRefund>3}">
								<c:set var="refundPrice" value="${ra.apPrice}"></c:set>
							</c:if>

							<c:if test="${checkIn-nowRefund<=3 && nowRefund-checkIn>1 }">
								<c:set var="refundPrice">
									<fmt:formatNumber value="${ra.apPrice*0.5}" pattern="0" />
								</c:set>
							</c:if>

							<c:if test="${checkIn-nowRefund<=1&&nowRefund<checkIn }">
								<c:set var="refundPrice">
									<fmt:formatNumber value="${ra.apPrice*0.2}" pattern="0" />
								</c:set>
							</c:if>
							<c:if test="${checkIn<nowRefund}">
								<c:set var="refundPrice" value="0">
								</c:set>
							</c:if>

							<input type="hidden" name="apId" value="${ra.apId }">
							<input type="hidden" name="refundPrice" value="${refundPrice}" />">
							
							<div>
								<div class="payInfoTitle">
									<div class="payInfoType">
										<span>결제 번호</span> <span id="orderId">${ra.apOrderId}</span>
									</div>
									<h4>${ra.acDetail.acTitle }</h4>
								</div>

							</div>

						</div>
						<div class="hrContainer">
							<hr />
						</div>
						<div class="hotelPayDetailInfo">
							<div class="payDetailInfo">
								<h4>결제 정보</h4>
								<div>
									<div class="payInfoTypeCon">
										<div class="payInfoType">
											<span>결제자 ID</span> <span>${ra.member.memberId }</span>
										</div>
									</div>
									<div class="payInfoTypeCon">
										<div class="payInfoType">
											<span>결제인</span> <span>${ra.member.memberNickname}</span>
										</div>
									</div>
									<div class="payInfoTypeCon">
										<div class="payInfoType">
											<span>결제 수단</span>
											<c:if test="${fn:substring(ra.apOrderId,0,1)=='C' }">
												<span>카드 결제</span>
											</c:if>
											<c:if test="${fn:substring(ra.apOrderId,0,1)=='K' }">
												<span>카카오 페이</span>
											</c:if>
										</div>
									</div>
									<div class="payInfoTypeCon">
										<div class="payInfoType">
											<span>결제일자</span> <span>${ra.apDate}</span>
										</div>
									</div>
								
									<div class="payInfoTypeCon">
										<div class="payInfoType">
											<span>숙박일</span> 
											<span>${ra.arv.checkIn} ~ ${ra.arv.checkOut}</span>
										</div>
									</div>
									<div class="payInfoTypeCon">
										<div class="payInfoType">
											<span>인원</span> 
											<span>${ra.apPeople}</span>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="hrContainer">
							<hr />
						</div>
						<div class="hotelPayDetailInfo">
							<div class="payDetailPrice">
								<h4>결제 금액</h4>
								<div>
									<h4>
										총합 :
										<fmt:formatNumber value="${ra.apPrice}" type="currency"
											currencySymbol="₩" />
									</h4>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="hotelPayBtnCon">
			<c:if test="${checkIn>nowRefund}">
				<c:if test="${fn:substring(ra.apOrderId,0,1)=='C' }">
					<button class="hotelPayBtn" style="background-color : #b31312" onclick="refundCard(${ra.apId},${refundPrice},${checkIn},${nowRefund})">결제 취소</button>
				</c:if>
				<c:if test="${fn:substring(ra.apOrderId,0,1)=='K' }">
					<button class="hotelPayBtn" style="background-color : #b31312" onclick="refundKaKao(${ra.apId},${refundPrice},${checkIn},${nowRefund})">결제 취소</button>
				</c:if>
			</c:if>	
				<button class="hotelPayBtn" style="background-color : #afafaf" onclick="javascript:history.back()">뒤로 가기</button>
			</div>
		</div>
	</div>
	</div>

	<script>
	
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
								location.replace("${path}admin/paymentList");
							}
						})
					},
					error: function(result) {
						alert("결제금액 환불실패.");
						location.replace("${path}admin/paymentList");
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
								location.replace("${path}admin/paymentList");
							}
						})
					},
					error: function(result) {
						alert("결제금액 환불실패.");
						location.replace("${path}admin/paymentList");
					}
				})					
			}
		})
	}
	
	</script>
</section>
<jsp:include page="/WEB-INF/views/admin/common/adminfooter.jsp" />