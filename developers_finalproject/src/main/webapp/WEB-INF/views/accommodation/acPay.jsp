<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<script src="https://nsp.pay.naver.com/sdk/js/naverpay.min.js"></script>
<link rel="stylesheet" href="${path }/css/accommodation/acSearchBar.css" />
<link rel="stylesheet" href="${path }/css/accommodation/acPay.css" />
<section>
	<div class="hotelPayCon">
		<div class="hotelPay">
			<div id="payTitle">
				<button onclick="javascript:history.back()">
					<ion-icon name="chevron-back"></ion-icon>
				</button>
				<span>확인 및 결제</span>
			</div>
			<div class="hotelPayInfoCon">
				<div class="hotelPayInfo">
					<div id="payInfo">
						<h3>예약정보</h3>
						<div class="payInfoTypeCon">
							<div class="payInfoType">
								<span>날짜</span> <span>${checkIn} ~ ${checkOut}</span>
							</div>
							<button class="modifyBtn">수정</button>
						</div>
						<div class="payInfoTypeCon">
							<div class="payInfoType">
								<span>게스트</span> <span>게스트 ${people}명</span>
							</div>
							<button class="modifyBtn">수정</button>
						</div>
					</div>
					<hr />
					<div id="payCategory">						
						<button class="pcBtn" type="button" id="cardPay">
							<ion-icon name="card-outline"></ion-icon>
							<span>카드 결제</span>
						</button>

						<button class="pcBtn" type="button" id="naverPay">
							<img alt="" src="${path}/images/accommodation/naverPay.jpg">
						</button>

						<button class="pcBtn" type="button" id="kakaoPay">
							<img alt="" src="${path}/images/accommodation/kakaoPay2.jpeg">
						</button>
					</div>

					<hr />
					<div id="hotelPayBtnCon">
						<button id="hotelPayBtn">결제하기</button>
					</div>
				</div>
				<div class="hotelPayDetailCon">
					<div class="hotelPayDetail">
						<div>
							<div id="hotelPayDetailTitle">
								<c:forEach var="f" items="${ap.acFiles}">
									<c:if test="${fn:contains(f.afMain,'Y')}">
										<img src="${path }/images/upload/accommodation/${f.afName}"
											alt="" />
									</c:if>
								</c:forEach>
								<div>
									<pre>
									${ap.acTitle }
                      				</pre>
									<div class="starContainer">
										<c:set var="star" value="20" />
										<span class="star"> ★★★★★ <span
											style="width:${ap.reviewGrade*star}%">★★★★★</span>
										</span> <span>(후기 ${ap.reviewCount}개)</span>
									</div>
								</div>
							</div>
							<div class="hrContainer">
								<hr />
							</div>
							<div class="hotelPayDetailInfo">
								<div class="payDetailInfo">
									<h4>결제 금액</h4>
									<div>
										<span>₩ <span id="exPrice"> <fmt:formatNumber
													value="${ap.acPrice }" pattern="#,###" />
										</span>x <span id="exDay">${diff}</span>박
										</span> <span> ₩ <span id="resultPrice"><fmt:formatNumber
													value="${resultPrice }" pattern="#,###" /></span>
										</span>
									</div>
								</div>
							</div>
							<div class="hrContainer">
								<hr />
							</div>
							<div class="hotelPayRealInfo">
								<span>총합계</span> <span> ₩ <span id="realResultPrice"><fmt:formatNumber
													value="${resultPrice }" pattern="#,###" /></span>
								</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		const acId=${ap.acId}
		const acTitle='${ap.acTitle}'
		const acPrice=${resultPrice}
		const people=${fn:replace(people,"명","")}
		const checkIn='${checkIn}'
		const checkOut='${checkOut}'
		const memberId='${loginMember.memberId}'
		const memberName='${loginMember.memberNickname}'
		const memberEmail='${loginMember.memberEmail}'
		
	</script>
	<script src="${path }/js/accommodation/acPay.js"></script>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
