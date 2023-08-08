$(document).ready(function() {

})

function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));
};

$(".pcBtn").on("click", function() {

	if ($(this).attr("id") == "cardPay") {
		$("#hotelPayBtn").removeClass()
		$(".pcBtn").css("border", "2px solid #d8d8d8")
		$(this).css("border", "3px solid #b31312")
		$("#hotelPayBtn").addClass("cardPay")
	} else if ($(this).attr("id") == "kakaoPay") {
		$("#hotelPayBtn").removeClass()
		$(".pcBtn").css("border", "2px solid #d8d8d8")
		$(this).css("border", "3px solid #F7E600")
		$("#hotelPayBtn").addClass("kakaoPay")
	}
})


$("#hotelPayBtn").on("click", function() {

	if ($(this).hasClass("cardPay")) {
		//주문번호 생성
		const date = new Date();
		const year = date.getFullYear();
		const month = ('0' + (date.getMonth() + 1)).slice(-2);
		const day = ('0' + date.getDate()).slice(-2);
		var str = ""

		for (let i = 0; i < 8; i++) {
			str += Math.floor(Math.random() * 10)
		}

		var uid = "C" + year + month + day + str
		var IMP = window.IMP;

		// /주문번호 생성/

		IMP.init("imp01205556");
		IMP.request_pay(
			{
				pg: "html5_inicis.INIpayTest",		//KG이니시스 pg파라미터 값
				pay_method: "card",		//결제 방법
				merchant_uid: uid,//주문번호
				name: acTitle,		//상품 명
				amount: apPrice,			//금액
				buyer_email: memberEmail,
				buyer_name: memberName,
			},
			function(rsp) {
				//rsp.imp_uid 값으로 결제 단건조회 API를 호출하여 결제결과를 판단합니다.
				if (rsp.success) {
					let data = {
						impUid: rsp.imp_uid,
						apOrderId: uid,
						acId: acId,
						memberId: memberId,
						acTitle: acTitle,
						apPrice: apPrice,
						apPeople: apPeople,
						checkIn: checkIn,
						checkOut: checkOut,
					};
					$.ajax({
						url: "/pay/cardPay",
						method: "POST",
						contentType: "application/json;charset=utf-8",
						data: JSON.stringify(data),
						dataType: "json",
						success: function(result) {
							location.href = "/ac/acPayResult"
						},
						error: function(result) {
							location.href = "/ac/acError"
						}
					})
				}
				else {
					alert("결제에 실패하였습니다. 에러 내용: " + rsp.error_msg);
				}
			}

		);
	} else if ($(this).hasClass("kakaoPay")) {
		let data = {
			acId: acId,
			memberId: memberId,
			acTitle: acTitle,
			apPrice: apPrice,
			apPeople: apPeople,
			checkIn: checkIn,
			checkOut: checkOut,
		};

		$.ajax({
			url: "/pay/kakaoPay",
			method: "POST",
			contentType: "application/json;charset=utf-8",
			data: JSON.stringify(data),
			dataType: "json",
			success: function(data) {
				//window.open(data.next_redirect_pc_url, "_blank", "width=500, height=700");
				location.href = data.next_redirect_pc_url
			}
		})
	}


})

