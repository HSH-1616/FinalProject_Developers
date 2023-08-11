<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<section style="margin-top: 50px; margin-bottom: 50px">
	<c:if test="${ not empty apl }">
		<div style="display: flex;">
			<c:forEach var="apl" items="${apl}">
				<div style="margin-right: 80px;">
					<c:forEach var="arf" items="${apl.acDetail.acFiles}">
						<c:if test="${fn:contains(arf.afMain,'Y')}">
							<img style="width: 200px; height: 200px" alt=""
								src="${path }/images/upload/accommodation/${arf.afName}">
						</c:if>
					</c:forEach>
					<h6>${apl.acDetail.acTitle }</h6>
					<p>결제번호 : ${apl.apOrderId }</p>
					<p>결제자아이디 : ${apl.member.memberId }</p>
					<p>결제자 : ${apl.member.memberNickname }</p>
					<input id="apId" type="hidden" value="${apl.apId }"> <input
						id="totalPrice" type="hidden" value="${apl.apPrice }">
					<p>결제금액 : ${apl.apPrice }</p>
					<c:choose>
						<c:when test="${fn:contains(apl.apOrderId,'K')}">
							<button style="display: block" class="cancelBtn kakao">결제취소</button>
						</c:when>
						<c:when test="${fn:contains(apl.apOrderId,'C')}">
							<button style="display: block" class="cancelBtn card">결제취소</button>
						</c:when>
					</c:choose>

				</div>
			</c:forEach>
		</div>
	</c:if>
	<c:if test="${empty apl }">
		<h2>결제목록이 없습니다.</h2>
	</c:if>
</section>
<script>
	$(".cancelBtn").on("click", function() {
		var apId = $(this).parent().children("#apId").val()
		var totalPrice = $(this).parent().children("#totalPrice").val()

		if ($(this).hasClass("kakao")) {
			$.ajax({
				url : "/pay/kakaoRefund",
				method : "POST",
				data : {
					apId : apId,
					acPrice : totalPrice,
				},
				success : function(data) {
					alert("결제금액 환불완료");
					document.location.href = document.location.href;
				},
				error : function(result) {
					alert("결제금액 환불실패.");
					document.location.href = document.location.href;
				}
			})
		} else {
			$.ajax({
				url : "/pay/refundIamport",
				type : "POST",
				data : {
					apId : apId,
					acPrice : totalPrice,
				},
				success : function(result) {
					alert("결제금액 환불완료");
					document.location.href = document.location.href;
				},
				error : function(result) {
					alert("결제금액 환불실패. 이유: " + result.responseText);
					document.location.href = document.location.href;
				}
			});
		}
	})
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />