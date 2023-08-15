$('.favorite').slick({
	lazyLoad: 'ondemand',
	slidesToShow: 3,
	slidesToScroll: 1
});

function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));
};

$(document).ready(function() {
	$.ajax({
		url: "/ac/acMyPage",
		method: "get",
		dataType: "json",
		success: function(data) {
			console.log(data)

			$(data).each(function(i, l) {
				var form = $('<form class="acPayForm" action="/ac/acRefundApply" method="post">')
				var acPay= $('<div class="acPayForm">')
				var hidden = $('<input type="hidden" name="orderId" value="' + l.apOrderId + '"/>')
				var listCon = $('<div class="acPayListCon">')
				var listHead = $('<div class="acPayListHead">')
				var img = ""
				$(l.acDetail.acFiles).each(function(i2, l2) {
					if (l2.afMain == "Y") {
						img = $('<img alt="" src="/images/upload/accommodation/' + l2.afName + '">')
					}
				})
				var content = $('<div class="payListContent">')
				var orderId = $('<h5>' + l.apOrderId + '</h5>')
				
				var card = ""
				if (l.apOrderId.substr(0, 1) == "C") {
					card = "카드"
				} else {
					card = "카카오 페이"
				}
								
				var contentDiv = $('<div><div><h6>' + l.acDetail.acTitle + '</h6><h6>숙박기간 : ' + l.arv.checkIn + ' ~ ' + l.arv.checkOut + '</h6></div><div><h6>결제일자 : ' + l.apDate + '</h6><h6>결제수단 : ' + card + '</h6></div></div>')
				var hr = $('<hr>')
				
				var oldDate = new Date(l.arv.checkIn);
				var newDate = new Date(l.arv.checkOut);
				let diff = Math.abs(newDate.getTime() - oldDate.getTime());
				diff = Math.ceil(diff / (1000 * 60 * 60 * 24));
				var price = ""
				if (l.apPeople > 2) {
					price = l.acDetail.acPrice + (20000 * (l.apPeople - 2))
				} else {
					price = l.acDetail.acPrice
				}
				var listDetail = $('<div class="acPayListDetail">')
				/////////////////////////////////
				
				if (l.apCancel == 'N') {					
					var listInfo = $('<div class="payInfo">')
					var infoContent = $('<h5>결제 상세</h5><div><div><ion-icon name="people-outline"></ion-icon><span>' + l.apPeople + '명</span></div><div><ion-icon name="time-outline"></ion-icon><span>' + diff + '일</span></div></div>')
					var payPrice = $('<div class="payPrice"><div><h6>₩' + String(price).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + ' x ' + diff + '박</h6><h6>=</h6><h6>₩' + String(l.apPrice).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '</h6></div></div>')
					var cancelBtn = $('<button class="cancelPay">예약 취소</button>')

					content.append(orderId).append(contentDiv)
					listHead.append(img).append(content)
					listInfo.append(infoContent)
					listDetail.append(listInfo).append(payPrice).append(cancelBtn)
					listCon.append(listHead).append(hr).append(listDetail)
					form.append(hidden).append(listCon)
					$(".payListArea").append(form)
				} else if (l.apCancel == 'R') {

					var status = $('<h5 class="refundStatus">검토중</h5>')					
					var refundInfo = $('<div class="refundInfo">')
					
					oldDate =new Date();
					newDate =new Date(l.arv.checkIn);
					diff = Math.abs(newDate.getTime() - oldDate.getTime());
					diff = Math.ceil(diff / (1000 * 60 * 60 * 24));
					
					if(diff<=3&&diff>1){
						risk=Math.round(l.apPrice*0.5)
						refundPrice=Math.round(l.apPrice*0.5)
					}else if(diff<=1){
						risk=Math.round(l.apPrice*0.8)
						refundPrice=Math.round(l.apPrice*0.2)
					}else{
						risk=0
						refundPrice=l.apPrice
					}
					console.log(risk)
					
					var refundPrice=$('<h5>예약 취소 정보</h5><div class="refundPrice"><span>결제금액 : ₩'+String(l.apPrice).replace(/\B(?=(\d{3})+(?!\d))/g, ",")+'</span><span>공제액 : ₩'+String(risk).replace(/\B(?=(\d{3})+(?!\d))/g, ",")
					+'</span></div><h6>환불 예정 금액 : ₩'+String(refundPrice).replace(/\B(?=(\d{3})+(?!\d))/g, ",")+'</h6>')
					var refundReason=$('<div class="refundReason"><h5>예약 취소 사유</h5><div><h6>취소 사유 : '+l.apr.aprReason+'</h6><pre>내용 : '+l.apr.aprContent+'</pre></div></div>')
					content.append(orderId).append(status).append(contentDiv)
					listHead.append(img).append(content)
					refundInfo.append(refundPrice)
					listDetail.append(refundInfo).append(refundReason)
					listCon.append(listHead).append(hr).append(listDetail)
					acPay.append(listCon)
					$(".refundCheck").append(acPay)
				}
			})
		},
		error: function(result) {

		}
	})

})

$(".value.ac").on("click",function(){
	$(".payListArea").hide()
	$(".refundListArea").hide()
	$('.'+$(this).attr("id")).show()
})
