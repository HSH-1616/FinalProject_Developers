<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>
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
						<button class="pcBtn">
						 <ion-icon name="card-outline"></ion-icon>
						 <span>카드 결제</span>
						</button>
						<button class="pcBtn">
						<img alt="" src="${path}/images/accommodation/naverPay.jpg">
						</button>
						<button class="pcBtn" onclick="kakaoPay()">
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
										<img
											src="${path }/images/upload/accommodation/${f.afName}"
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
										</span>x <span id="exDay"></span>박
										</span> <span> ₩ <span id="resultPrice"></span>
										</span>
									</div>
								</div>
							</div>
							<div class="hrContainer">
								<hr />
							</div>
							<div class="hotelPayRealInfo">
								<span>총합계</span> <span> ₩ <span id="realResultPrice"></span>
								</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	
	function kakaoPay() {
	var IMP = window.IMP; // 생략가능
	IMP.init('imp01205556');
	// i'mport 관리자 페이지 -> 내정보 -> 가맹점식별코드
	// ''안에 띄어쓰기 없이 가맹점 식별코드를 붙여넣어주세요. 안그러면 결제창이 안뜹니다.
	IMP.request_pay({
		pg: 'kakaopay.TC0ONETIME',
		pay_method: 'card',
		merchant_uid: 'merchant_' + new Date().getTime(),
		/* 
		 *  merchant_uid에 경우 
		 *  https://docs.iamport.kr/implementation/payment
		 *  위에 url에 따라가시면 넣을 수 있는 방법이 있습니다.
		 */
		name: '숙박업소 : ${ap.acTitle} ',
		// 결제창에서 보여질 이름
		// name: '주문명 : ${auction.a_title}',
		// 위와같이 model에 담은 정보를 넣어 쓸수도 있습니다.
		amount: $("#realResultPrice").text().replace(",",""),
		// amount: ${bid.b_bid},
		// 가격 
		buyer_name: '이름',
		// 구매자 이름, 구매자 정보도 model값으로 바꿀 수 있습니다.
		// 구매자 정보에 여러가지도 있으므로, 자세한 내용은 맨 위 링크를 참고해주세요.
		buyer_postcode: '123-456',
	}, function(rsp) {
		console.log(rsp);
		if (rsp.success) {
			var msg = '결제가 완료되었습니다.';
			msg += '결제 금액 : ' + rsp.paid_amount;
			// success.submit();
			// 결제 성공 시 정보를 넘겨줘야한다면 body에 form을 만든 뒤 위의 코드를 사용하는 방법이 있습니다.
			// 자세한 설명은 구글링으로 보시는게 좋습니다.
		} else {
			var msg = '결제에 실패하였습니다.';
			msg += '에러내용 : ' + rsp.error_msg;
		}
		alert(msg);
	});

}
	
</script>
	<script src="${path }/js/accommodation/acPay.js"></script>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />