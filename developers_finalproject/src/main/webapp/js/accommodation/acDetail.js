$(document).ready(function() {
	if ($(".detailHotelInfoCon pre").height() > 300) {
		$(".detailHotelInfoBtn").css("display", "flex");
		$(".detailHotelInfoCon pre").height(300);
	}
});

function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));
};

$(document).on("click", ".detailHotelInfoBtn button", function(e) {
	if ($(".detailHotelInfoCon pre").height() <= 300) {
		// $(".detailHotelInfoCon pre").height("auto");
		$(".detailHotelInfoCon pre").animate({ height: "1200px" }, 700);
		$(this).text("접기");
	} else {
		$(".detailHotelInfoCon pre").animate({ height: "300px" }, 700);
		$(this).text("더보기");
	}
});

$(document).on("click", "#detailHeart", function(e) {
	if ($("#detailHeartOn").css("display") == "none") {
		$("#detailHeartOn").show();
		$("#detailHeartOff").hide();
		swal({
			title: "찜하기에 등록되었습니다!",
			imageUrl: "../../images/accommodation/heartIcon.png",
			imageWidth: 200,
			imageHeight: 200,
			confirmButtonText: "확인",
			confirmButtonColor: "#b31312",
		});

	} else {
		$("#detailHeartOn").hide();
		$("#detailHeartOff").show();
	}
});

$(document).on("click", "#hotelImageBtn", function(e) {
	$(".detailHotelImage").fadeIn(500, "linear");
	$("#blurSearchBar").show();
	if ($(".detailHotelImage").css("display") == "block") {
		$("body").css("overflow", "hidden");
	}
});

$(document).on("click", ".images img", function(e) {
	$(".overImg.main img").prop("src", $(this).attr("src"));
	$(".overImg.main").show();
	$(".overImg.review").hide();
	$(".detailHotelImage").hide();
	$(".overImgCon").css("display", "flex");
	$(".overImg.main button").click(function(e) {
		$(".overImgCon").hide();
		$(".detailHotelImage").show();
	});
});

$(document).on("click", ".detailReviewImg img", function(e) {
	$(".overImg.review img").prop("src", $(this).attr("src"));
	$(".overImg.review").show();
	$(".overImg.main").hide();
	$(".overImgCon").css("display", "flex");
	$("#blurSearchBar").show();
	$("body").css("overflow", "hidden");
	$(".overImg.review button").click(function(e) {
		$(".overImgCon").hide();
		$("#blurSearchBar").hide();
		$("body").css("overflow", "auto");
	});
});

$(document).on("click", "#detailSharing", function() {
	$("#shareCon").show()
	$("#shareHead button").click(function() {
		$("#shareCon").hide()
	})
})

const url = encodeURI(window.location.href);

function naverShare() {
	window.open("https://share.naver.com/web/shareView?url=" + url);
}

function facebookShare() {
	window.open("http://www.facebook.com/sharer/sharer.php?u=" + url);
}

function twitterShare() {
	const text = 'Developers'
	window.open("https://twitter.com/intent/tweet?text=" + text + "&url=" + url)
}

function linkShare() {
	var url = '';
	var textarea = document.createElement("textarea");
	document.body.appendChild(textarea);
	url = window.document.location.href;
	textarea.value = url;
	textarea.select();
	document.execCommand("copy");
	document.body.removeChild(textarea);
	alert("URL이 복사되었습니다.")
}

$(document).on("click", ".detailHotelPeople", function() {
	if ($(".detailHotelPeople ion-icon").first().css("display") == "block") {
		$(".detailHotelPeople>div").css({
			"border": "2px solid black"
			, "border-bottom-left-radius": "10px"
			, "border-bottom-right-radius": "10px"
		})
		$(".detailHotelPeople ion-icon").first().hide()
		$(".detailHotelPeople ion-icon").last().show()
		$(".searchPeople.detail").show()
	} else {
		$(".detailHotelPeople>div").css({
			"border": "1px solid #999"
			, "border-bottom-left-radius": "10px"
			, "border-bottom-right-radius": "10px"
		})
		$(".detailHotelPeople ion-icon").first().show()
		$(".detailHotelPeople ion-icon").last().hide()
		$(".searchPeople.detail").hide()
	}
})

$(".detailHotelModal").on("click",function(){
	//$(".searchPeople.detail").hide()
})


function payCheck(){
	var checkIn=$("#detailHotelCheckIn").val()
	var checkOut=$("#detailHotelCheckOut").val()
	var payPeople=$("#payPeople").val()
	if((checkIn!="날짜추가"||checkIn!="")&&(checkOut!="날짜추가"||checkOut!="")&&payPeople!="인원추가"){
		$(".detailHotelBtn").hide();
		$(".detailHotelBtn.on").css("display","flex");
	}
	if(checkIn=="날짜추가"||checkOut=="날짜추가"||payPeople=="인원추가"){
		$(".detailHotelBtn").css("display","flex");
		$(".detailHotelBtn.on").hide();
	}
}


$(".detailHotelCheckDay div> ion-icon").on("click", function() {
	if ($(this).attr("id") == "inDayBtn") {
		$(".day").removeClass("first").removeClass("last");
		$("#inDayBtn,#outDayBtn").hide();
		$("#detailHotelCheckIn,#detailHotelCheckOut").val("날짜추가");
		$(".day").css("background-color", "white");
		$("#fnum").val("");
		$("#lnum").val("");
		$("#exDay").text(1)
		$("#resultPrice").text($("#exPrice1").text())
		$("#realResultPrice").text($("#exPrice1").text())
	} else {
		$(".day").removeClass("last");
		$("#outDayBtn").hide();
		$("#lnum").val("");
		$("#detailHotelCheckOut").val("날짜추가");
		$(".day").not(".first").css("background-color", "white");
		$("#exDay").text(1)
		$("#resultPrice").text($("#exPrice1").text())
		$("#realResultPrice").text($("#exPrice1").text())
	}
	payCheck()
});


$(document).ready(function($) {

	$(".detailHotelCheckDay").click(function(event) {
		var offset = $(".detailHotelDayCon").offset();
		$('html, body').animate({ scrollTop: offset.top }, 100);
	});
	
	payCheck()


});

// 인원수 함수
function countFn2(type) {
	var count = $("#peopleCount").val();

	if (type == "plus") {
		if (count < $("#maxPeople").val()) {
			$("#countInfo").text("");
			$("#peopleCount").val(++count);
			$("#payPeople").val($("#peopleCount").val() + "명");

			if (count >= 3) {
				$("#exPrice1").text(
					$("#exPrice1").text((Number($("#exPrice1").text().replace(",", "")) + 20000)).text().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
				)
				$("#exPrice2").text(
					$("#exPrice2").text((Number($("#exPrice2").text().replace(",", "")) + 20000)).text().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
				)
			}
			$("#resultPrice").text(String(($("#exPrice1").text().replace(",", "")) * $("#exDay").text()).replace(/\B(?=(\d{3})+(?!\d))/g, ","))
			$("#realResultPrice").text(String(($("#exPrice1").text().replace(",", "")) * $("#exDay").text()).replace(/\B(?=(\d{3})+(?!\d))/g, ","))
		} else {
			$("#countInfo").text("최소 1명부터 "+$("#maxPeople").val()+"명까지 선택가능 합니다.");
		}
	} else {
		if (count > 1) {
			$("#countInfo").text("");
			$("#peopleCount").val(--count);
			$("#payPeople").val($("#peopleCount").val() + "명");

			if (count >= 2) {
				$("#exPrice1").text(
					$("#exPrice1").text((Number($("#exPrice1").text().replace(",", "")) - 20000)).text().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
				)
				$("#exPrice2").text(
					$("#exPrice2").text((Number($("#exPrice2").text().replace(",", "")) - 20000)).text().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
				)
			}
			$("#resultPrice").text(String(($("#exPrice1").text().replace(",", "")) * $("#exDay").text()).replace(/\B(?=(\d{3})+(?!\d))/g, ","))
			$("#realResultPrice").text(String(($("#exPrice1").text().replace(",", "")) * $("#exDay").text()).replace(/\B(?=(\d{3})+(?!\d))/g, ","))
		} else {
			$("#countInfo").text("최소 1명부터 "+$("#maxPeople").val()+"명까지 선택가능 합니다.");
		}
	}
	if (count > 1) {
		$("#peopleBtn button").first().css("color", "#b31312");
	} else {
		$("#peopleBtn button").first().css("color", "#afafaf");
	}
	if (count < $("#maxPeople").val()) {
			$("#peopleBtn button")
				.last()
				.css("color", "#b31312");
		} else {
			$("#peopleBtn button")
				.last()
				.css("color", "#afafaf");
		}
	payCheck()
}
// /인원수 함수/
setTimeout(function() {
	$(document).on("click", ".day", function() {
		if ($(this).hasClass("first")) {
			$("#detailHotelCheckIn").val($(this).children().val())

		} else if ($(this).hasClass("last")) {
			$("#detailHotelCheckOut").val($(this).children().val())
		}
		if ($(".last").length) {
			const oldDate = new Date($("#detailHotelCheckIn").val());
			const newDate = new Date($("#detailHotelCheckOut").val());
			let diff = Math.abs(newDate.getTime() - oldDate.getTime());
			diff = Math.ceil(diff / (1000 * 60 * 60 * 24));
			$("#exDay").text(diff)
			$("#resultPrice,#realResultPrice").text(String($("#exPrice2").text().replace(",", "") * diff).replace(/\B(?=(\d{3})+(?!\d))/g, ","))
		}
		payCheck()
	})
	
}, 100)

