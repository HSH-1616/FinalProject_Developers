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
							<input type="hidden" name="apId" value="${ra.apId }"> <input
								type="hidden" name="refundPrice" value="${ra.apr.aprPrice}">
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
											<span>취소일자</span> <span>${ra.apReDate}</span>
										</div>
									</div>
									<div class="payInfoTypeCon">
										<div class="payInfoType">
											<span>결제 금액</span> <span><fmt:formatNumber
													value="${ra.apPrice}" type="currency" currencySymbol="₩" /></span>
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
								<h4>환불 예정 금액</h4>

								<div>
									<h4>
										총합 :
										<fmt:formatNumber value="${ra.apr.aprPrice}" type="currency"
											currencySymbol="₩" />
									</h4>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="hotelPayInfoCon">
				<div class="hotelPayInfo">
					<hr />
					<div id="payInfo">
						<h3>예약 취소 사유</h3>
					</div>
					<div class="refundContent">
						<div>
							<h5>
								환불 사유 : <span style="color: #20c997;">${ra.apr.aprReason }</span></span>
							</h5>
							<textarea rows="" cols="" readonly>${ra.apr.aprContent }</textarea>
						</div>

						<hr />

					</div>
				</div>

			</div>
			<div id="hotelPayBtnCon">
				<c:if test="${fn:substring(ra.apOrderId,0,1)=='C' }">
					<button class="hotelPayBtn" onclick="refundCard()">환불 승인</button>
				</c:if>
				<c:if test="${fn:substring(ra.apOrderId,0,1)=='K' }">
					<button class="hotelPayBtn" onclick="refundKaKao()">환불 승인</button>
				</c:if>
				<button class="hotelPayBtn" onclick="rejectRefund()">환불 반려</button>
				<button class="hotelPayBtn" onclick="javascript:history.back()">취소</button>
			</div>
		</div>
	</div>
	</div>

	<script>
	
	var apId = $("input[name=apId]").val()
	var refundPrice = $("input[name=refundPrice]").val()

	function refundKaKao() {
		$.ajax({
			url: "${path}/pay/kakaoRefund",
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
						location.replace("${path}/admin/paymentList");
					}
				})
			},
			error: function(result) {
				alert("결제금액 환불실패.");
				location.replace("${path}/admin/paymentList");
			}
		})
	}

	function refundCard() {
		$.ajax({
			url:"${path}/pay/refundIamport",
			type: "POST",
			data: {
				apId: apId,
				refundPrice: refundPrice,
			},
			success: function(result) {
				Swal.fire({
					icon: 'success',
					iconColor: '#20c997',
					title: "승인 완료",
					confirmButtonText: "확인",
					confirmButtonColor: "#20c997",
				}).then((result) => {
					if (result.isConfirmed) {
						location.replace("${path}/admin/paymentList");
					}
				})
			},
			error: function(result) {
				alert("결제금액 환불실패. 이유: " + result.responseText);
				location.replace("${path}/admin/paymentList");
			}
		});
	}

	function rejectRefund() {
		(async () =>{
			const { value: comment } = await Swal.fire({
				title: '거절 사유를 입력해주세요.',
				input: 'text',
				inputPlaceholder: '사유 입력',
				confirmButtonText: "확인",
				confirmButtonColor: "#20c997",
			})

			// 이후 처리되는 내용.
			if (comment) {
				$.ajax({
					url: "${path}/ac/rejectRefund",
					type: "POST",
					data: {
						apId: apId,
						comment : comment,
					},
					success: function(result) {
						Swal.fire({
							icon: 'success',
							iconColor: '#20c997',
							title: "제출 완료",
							confirmButtonText: "확인",
							confirmButtonColor: "#20c997",
						}).then((result) => {
							if (result.isConfirmed) {
								location.replace("${path}/admin/paymentList");
							}
						})
					},
					error: function(result) {
						alert("결제금액 환불실패. 이유: " + result.responseText);
						location.replace("${path}/admin/paymentList");
					}
				});
			}
			
		})()
	}
	
	</script>
</section>
<jsp:include page="/WEB-INF/views/admin/common/adminfooter.jsp" />