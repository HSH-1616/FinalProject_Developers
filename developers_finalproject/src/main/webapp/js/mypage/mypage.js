$('.favorite').slick({
	lazyLoad: 'ondemand',
	slidesToShow: 3,
	slidesToScroll: 1
});

$(document).ready(function() {
	$.ajax({
		url: "/ac/acMyPage",
		method: "get",
		contentType: "application/json;charset=utf-8",
		dataType: "json",
		success: function(data) {
			console.log(data)
			var card = $('<div class="mypagehotelcard">')
			var box = $('<div class="hotelcardimboxarea">')
			var img = $('<img alt="" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT2C26H5J5MLk5XylBHGd6b5FZAdFZYdMH-EQ&usqp=CAU">')
			var content = $('<div class="hotelcardcontent">')
			var title = $('<p class="paytitle">한옥스테이 “화응(花應)”</p>')
			var orderId = $('<span class="paynumber">결제번호 : <p>K2023081219936811</p></span>')
			var price = $('<span class="pay">결제금액 : <p>8원</p></span>')
			var checkDay = $('<span class="hotelday">숙박기간 : <p>ㅇㅇㅇㅇ~ㅇㅇㅇㅇ</p></span>')
			var btnDiv = $('<div class="hotelcardday">')
			var btn = $('<button class="cancelbtn">결제취소</button>')
			
			box.append(img)
			content.append(title).append(orderId).append(price).append(checkDay)
			btnDiv.append(btn)
			card.append(box).append(content).append(btnDiv)
			$(".myschedulcontentarea").last().append(card)
		},
		error: function(result) {

		}
	})

})