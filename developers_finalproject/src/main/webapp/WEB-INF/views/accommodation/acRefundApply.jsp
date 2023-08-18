<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<c:set var="path" value="${pageContext.request.contextPath }" />
<script src="sweetalert2.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<link rel="stylesheet" href="sweetalert2.min.css">
<link rel="stylesheet"
	href="${path }/css/accommodation/acRefundApply.css" />
<section>
	<div class="hotelPayCon">
		<div class="hotelPay">
			<div id="payTitle">
				<button type="button">
					<ion-icon name="chevron-back"></ion-icon>
				</button>
				<span>예약 취소 신청</span>
			</div>
			<div class="hotelPayDetailCon">
				<input type="hidden" name="apId" value="${ra.apId }">
				<input type="hidden" name="apOrderId" value="${ra.apOrderId }">
				<div class="hotelPayDetail">
					<div>
						<div id="hotelPayDetailTitle">
							<c:forEach var="af" items="${ra.acDetail.acFiles }">
								<c:if test="${fn:contains(af.afMain,'Y')}">
									<img src="${path }/images/upload/accommodation/${af.afName}"
										alt="" />
								</c:if>
							</c:forEach>
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
								<h4>결제 상세</h4>
								<div>
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
											<span>결제일</span> <span>${ra.apDate}</span>
										</div>
									</div>
									<div class="payInfoTypeCon">
										<div class="payInfoType">
											<span>숙박기간</span> <span>${ra.arv.checkIn} ~
												${ra.arv.checkOut}</span>
										</div>
									</div>
									<div class="payInfoTypeCon">
										<div class="payInfoType">
											<span>게스트</span> <span>${ra.apPeople }명</span>
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
									<c:if test="${ra.apPeople>2 }">
										<c:set var="price"
											value="${ra.acDetail.acPrice+20000*(ra.apPeople-2)}"></c:set>
									</c:if>
									<c:if test="${ra.apPeople<=2 }">
										<c:set var="price" value="${ra.acDetail.acPrice}"></c:set>
									</c:if>
									<fmt:parseDate value="${ra.arv.checkIn }" var="checkInDate"
										pattern="yyyy-MM-dd" />
									<fmt:parseNumber value="${checkInDate.time / (1000*60*60*24)}"
										integerOnly="true" var="checkIn"></fmt:parseNumber>
									<fmt:parseDate value="${ra.arv.checkOut }" var="checkOutDate"
										pattern="yyyy-MM-dd" />
									<fmt:parseNumber value="${checkOutDate.time / (1000*60*60*24)}"
										integerOnly="true" var="checkOut"></fmt:parseNumber>
									<span><fmt:formatNumber value="${price }"
											pattern="#,###" /> x ${checkOut-checkIn}박 = ₩<fmt:formatNumber
											value="${ra.apPrice}" pattern="#,###" /></span>
									<h4>
										총합 : ₩
										<fmt:formatNumber value="${ra.apPrice}" pattern="#,###" />
									</h4>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="hotelPayInfoCon">
				<div class="hotelPayInfo">
					<div id="payInfo">
						<h3>예약 취소 사유</h3>
					</div>
					<div class="refundContent">
						<div>
							<div class="input">
								<button type="button" class="value">
									단순 변심 <input hidden="true" type="radio" name="refundReason"
										value="단순 변심">
								</button>
								<button type="button" class="value">
									예약 실수 <input hidden="true" type="radio" name="refundReason"
										value="예약 실수">
								</button>
								<button type="button" class="value">
									기상 악화 <input hidden="true" type="radio" name="refundReason"
										value="기상 악화">
								</button>
								<button type="button" class="value">
									상세정보와 다름 <input hidden="true" type="radio" name="refundReason"
										value="상세정보와 다름">
								</button>
								<button type="button" class="value">
									일정 변경 <input hidden="true" type="radio" name="refundReason"
										value="일정 변경">
								</button>
							</div>
							<textarea rows="" cols="" name="refundContent"></textarea>
						</div>

					</div>
					<hr />
					<div class="refundPayPrice">
						<h3>환불 예정 금액</h3>
						<p>-체크인 기준 3일전에 예약 취소시 결제금액의 50% 공제후 환불이 가능합니다.</p>
						<p>-체크인 기준 1일전에 예약 취소시 결재금액의 80% 공제후 환불이 가능합니다.</p>

						<div class="resultPrice">
							<div>
								<h3>
									결제 금액(A) :
									<fmt:formatNumber value="${ra.apPrice}" pattern="#,###" />
								</h3>
								<c:set var="now" value="<%=new java.util.Date()%>" />
								<c:set var="nowDate">
									<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" />
								</c:set>
								<fmt:parseDate value="${nowDate}" var="nowCheck"
									pattern="yyyy-MM-dd" />
								<fmt:parseNumber value="${nowCheck.time / (1000*60*60*24)}"
									integerOnly="true" var="nowRefund"></fmt:parseNumber>
								<c:if test="${checkIn-nowRefund>3}">
									<h3>공제액(B) : 0</h3>
									<h3>환불 가능 금액 : (A) - (B)</h3>
							</div>
							<hr />
							<h3>
								환불 가능 금액 :
								<fmt:formatNumber value="${ra.apPrice}" type="currency" currencySymbol="₩" />
								<input type="hidden" name=refundPrice value="${ra.apPrice}">
							</h3>
							</c:if>
							<c:if test="${checkIn-nowRefund<=3 && nowRefund-checkIn>1}">
								<h3>
									공제액(B) :
									<fmt:formatNumber value="${ra.apPrice*0.5}" type="currency" currencySymbol="₩" />
								</h3>
								<h3>환불 가능 금액 : (A) - (B)</h3>
						</div>
						<hr />
						<h3>
							환불 가능 금액 :
							<fmt:formatNumber value="${ra.apPrice*0.5}" type="currency" currencySymbol="₩" />
							<input type="hidden" name=refundPrice value="<fmt:formatNumber value="${ra.apPrice*0.5}" pattern="0"/>">
						</h3>
						</c:if>
						<c:if test="${checkIn-nowRefund<=1 }">
							<h3>
								공제액(B) :
								<fmt:formatNumber value="${ra.apPrice*0.8}" type="currency" currencySymbol="₩"  />
							</h3>
							<h3>환불 가능 금액 : (A) - (B)</h3>
					</div>
					<hr />
					<h3>
						환불 가능 금액 :
						<fmt:formatNumber value="${ra.apPrice*0.2}" type="currency" currencySymbol="₩"  />						 
						<input type="hidden" name=refundPrice value="<fmt:formatNumber value="${ra.apPrice*0.2}" pattern="0"/>"> 						
					</h3>
					</c:if>


				</div>
			</div>
			<div id="hotelPayBtnCon">
				<button id="hotelPayBtn" onclick="checkRefund()">예약 취소하기</button>
			</div>
		</div>

	</div>
	</div>
	</div>

	<script src="${path }/js/accommodation/acRefundApply.js"></script>
</section>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />