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

		$.ajax({
			url: getContextPath()+"/ac/insertHeart",
			data: {
				memberId: memberId,
				acId: acId
			},
			success: function(result) {
				if (result > 0) {
					$("#detailHeartOn").show();
					$("#detailHeartOff").hide();
					swal({
						title: "찜하기에 등록되었습니다!",
						imageUrl: getContextPath()+"/images/accommodation/heartIcon.png",
						imageWidth: 200,
						imageHeight: 200,
						confirmButtonText: "확인",
						confirmButtonColor: "#b31312",
					});
				}
			}
		})
	} else {
		$.ajax({
			url: getContextPath()+"/ac/deleteHeart",
			data: {
				memberId: memberId,
				acId: acId
			},
			success: function(result) {
				if (result > 0) {
					$("#detailHeartOn").hide();
					$("#detailHeartOff").show();
				}
			}
		})

	}
});

$(document).on("click", "#hotelImageBtn", function(e) {
	$(".detailHotelImage").fadeIn(500, "linear");
	$("#blurSearchBar").show();
	if ($(".detailHotelImage").css("display") == "block") {
		$("body").css("overflow", "hidden");
	}
});

$(document).on("click", ".detailHotelImage img", function(e) {
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


$(".detailHotelModal").on("click", function() {
	//$(".searchPeople.detail").hide()
})


function payCheck() {
	var checkIn = $("#detailHotelCheckIn").val()
	var checkOut = $("#detailHotelCheckOut").val()
	var payPeople = $("#payPeople").val()
	if ((checkIn != "날짜추가" && checkIn != "") && (checkOut != "날짜추가" && checkOut != "") && payPeople != "인원추가") {
		$(".detailHotelBtn").hide();
		$(".detailHotelBtn.on").css("display", "flex");
	}
	if (checkIn == "날짜추가" || checkOut == "날짜추가" || payPeople == "인원추가") {
		$(".detailHotelBtn").css("display", "flex");
		$(".detailHotelBtn.on").hide();
	}
}


$(".detailHotelCheckDay div> ion-icon").on("click", function() {
	if ($(this).attr("id") == "inDayBtn") {
		$(".day").removeClass("first").removeClass("last");
		$(".selectDay").removeClass("first").removeClass("last");
		$("#inDayBtn,#outDayBtn").hide();
		$("#detailHotelCheckIn,#detailHotelCheckOut").val("날짜추가");
		$(".day").css({ "background-color": "white", "border-radius": "0px" });
		$(".selectDay").css({ "background-color": "white", "border-radius": "0px" });
		$(".selectDay").attr("class","day")
		$("#fnum").val("");
		$("#lnum").val("");
		$("#selectFnum").val("");
		$("#selectLnum").val("");
		$("#exDay").text(1)
		$("#resultPrice").text($("#exPrice1").text())
		$("#realResultPrice").text($("#exPrice1").text())
		$("input[name=resultPrice]").val($("#exPrice1").text().replace(",", ""))
		$(".xDay").attr("class", "day")
	} else {
		$(".day").removeClass("last");
		$(".selectDay").removeClass("last");
		$("#outDayBtn").hide();
		$("#lnum").val("");
		$("#selectLnum").val("");
		$("#detailHotelCheckOut").val("날짜추가");
		$(".day").not(".first").css({ "background-color": "white", "border-radius": "0px" });
		$(".selectDay").not(".first").css({ "background-color": "white", "border-radius": "0px" });
		$("#exDay").text(1)
		$("#resultPrice").text($("#exPrice1").text())
		$("#realResultPrice").text($("#exPrice1").text())
		$("input[name=resultPrice]").val($("#exPrice1").text().replace(",", ""))
	}
	payCheck()
});


$(document).ready(function($) {

	$(".detailHotelCheckDay").click(function(event) {
		var offset = $(".detailHotelDayCon").offset();
		$('html, body').animate({ scrollTop: offset.top - 130 }, 100);
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
			$("input[name=resultPrice]").val(String(($("#exPrice1").text().replace(",", "")) * $("#exDay").text()).replace(/\B(?=(\d{3})+(?!\d))/g, ",").replace(",", ""))
		} else {
			$("#countInfo").text("최소 1명부터 " + $("#maxPeople").val() + "명까지 선택가능 합니다.");
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
			$("input[name=resultPrice]").val(String(($("#exPrice1").text().replace(",", "")) * $("#exDay").text()).replace(/\B(?=(\d{3})+(?!\d))/g, ",").replace(",", ""))
		} else {
			$("#countInfo").text("최소 1명부터 " + $("#maxPeople").val() + "명까지 선택가능 합니다.");
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
			$("input[name=resultPrice]").val(String($("#exPrice2").text().replace(",", "") * diff).replace(/\B(?=(\d{3})+(?!\d))/g, ",").replace(",", ""))
		}
		
		//fnum, lnum을 input tye hidden값으로 재정의
		var fnum =
			$("#fnum").val() == "" ? "" : $("#fnum").val();
		var lnum =
			$("#lnum").val() == "" ? "" : $("#lnum").val();

		//날짜를 클릭했을때 체크인과 체크아웃사이에 날짜를 배열로 반환시켜주는 함수 
		var listDate = [];
		function getDateRange(fnum, lnum, listDate) {
			var dateMove = new Date(fnum);
			var strDate = fnum;
			if (fnum == lnum) {
				var strDate = dateMove.toISOString().slice(0, 10);
				listDate.push(strDate);
			} else {
				while (strDate < lnum) {
					var strDate = dateMove.toISOString().slice(0, 10);

					listDate.push(strDate);

					dateMove.setDate(dateMove.getDate() + 1);
				}
			}
			return listDate;
		}
		
		//예약일과 휴무일을 합치고 정렬
		var checkResultDay = [
			...checkInOutDay,
			...checkHolyDay
		]

		if (checkResultDay.length) {

			checkResultDay.sort(function(comp1, comp2) {
				var compDay1 = comp1.checkIn.toUpperCase();
				var compDay2 = comp2.checkIn.toUpperCase();
				if (compDay1 < compDay2) {
					return -1;
				} else if (compDay1 > compDay2) {
					return 1;
				}
				return 0;
			});



			var listDate2 = []
			//합친 배열에 사이값 구한후 class부여
			$.each(checkResultDay, function(i, l) {
				getDateRange(fnum, l.checkIn, listDate2)
				if (fnum < checkResultDay[i].checkIn) {
					/*console.log(i)
					console.log(checkResultDay[i].checkIn)*/
					$(listDate2).each(function(i2, l2) {
						$(".day").each(function(i3, l3) {
							if ($(this).children().val() == l2) {
								$(this).attr("class", "selectDay")
							}
						})

					})
					return false;
				}

			})

			sLLnum = listDate2[listDate2.length - 1]

			if ($(".selectDay").length) {
				$(".selectDay").eq(0).addClass("first")
				$("#selectFnum").val($(".selectDay").eq(0).children().val())
				$(".day").attr("class", "xDay")
			}


		}
		
		
		
		
		
		
		payCheck()
	})
	
	$(document).on("click", ".selectDay", function() {
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
			$("input[name=resultPrice]").val(String($("#exPrice2").text().replace(",", "") * diff).replace(/\B(?=(\d{3})+(?!\d))/g, ",").replace(",", ""))
		}
		payCheck()
	})
	
	



}, 100)
		
$(document).on("click", ".selectDay", function() {
	var num = $(this).children().val().replace("-", "").replace("-", "");

	//달력을 클릭했을때 첫번째는 checkIn으로 표시하고 fnum이라는 변수에 입력하고 html에 input hidden fnum에 값 대입
	//input type hidden을 만든 이유 : 이전달 다음달을 클릭하거나 form전송시 
	//달력 렌더링을 새로하기에 기존에 표시했던 checkIn,checkOut이 사라지기 떄문에 값 저장을위해 별도 생성


	//첫번째 클릭했을때 checkIn이 없을경우		
	if (!$(".selectDay.first").length) {
		//첫번째 클릭했을때 checkIn이 없지만 input hidden에 값이 있을경우 
		//예시) 7/20일을 클릭해서 checkIn이 표시됬지만 달력을 넘겨서 9월달로 갔을때
		//html상에서는 기존에 체크한 값이 출력되지 않는다 하지만 input hidden에는 값이 남아있기에 
		//9월달을 클릭했을때는 checkOut으로 표시된다. 
		if ($("#fnum").val() != "") {
			if ($("#fnum").val().replace("-", "").replace("-", "") > num) {
				$(".selectDay").not(".last").css({ "background-color": "white", "border-radius": "0px" });
				$(".selectDay").removeClass("first");
				$(this).addClass("first");
				$(this).css({ "background-color": "#b31312", "border-radius": "100%" });
				$("#fnum,#selectFnum,#checkIn").val($(".selectDay.first").children().val());
			}
			else {
				console.log(fnum)
				console.log(num)
				console.log(2)
				$(".selectDay").not(".first").css({ "background-color": "white", "border-radius": "0px" });
				$(".selectDay").removeClass("last");
				$(this).addClass("last");
				$(this).css({ "background-color": "#b31312", "border-radius": "100%" });
				$("#lnum,#selectLnum,#checkOut").val($(".selectDay.last").children().val());
				$("#outDayBtn").show();
			}
		}
		//첫번째 클릭했을때 checkIn도 없고 input hidden에 값도 없을경우 checkIn표시
		else {
			$(".selectDay").css({ "background-color": "white", "border-radius": "0px" });
			$(this).addClass("first");
			$(this).css({ "background-color": "#b31312", "border-radius": "100%" });
			$("#inDayBtn").show();
			$("#fnum,#selectFnum,#checkIn").val($(".selectDay.first").children().val());
		}
	}

	//checKIn,chekcOut의 날짜를 받는 변수 대소 비교를 위해 특수기호는 제거
	var fnum =
		$(".selectDay.first").length == 0
			? ""
			: $(".selectDay.first").children().val().replace("-", "").replace("-", "");
	var lnum =
		$(".selectDay.last").length == 0
			? ""
			: $(".selectDay.last").children().val().replace("-", "").replace("-", "");
	var sFnum =
		$(".selectDay.first").length == 0
			? ""
			: $(".selectDay.first").children().val().replace("-", "").replace("-", "");
	var sLnum =
		$(".selectDay.last").length == 0
			? ""
			: $(".selectDay.last").children().val().replace("-", "").replace("-", "");

	//달력을 클릭했을때 checkIn이 있고 checkIn이 클릭한 날짜 보다 작을 경우 checkOut 표시
	if ($(".selectDay.first").length && fnum < num) {
		$(".selectDay").not(".first").css({ "background-color": "white", "border-radius": "0px" });
		$(".selectDay").removeClass("last");
		$(this).addClass("last");
		$(this).css({ "background-color": "#b31312", "border-radius": "100%" });
		$("#lnum,#selectLnum,#checkOut").val($(".selectDay.last").children().val());
		$("#outDayBtn").show();
	}
	//달력을 클릭했을때 checkIn이 있지만 checkIn이 클릭한 날짜 보다 클 경우 새로운 checkIn으로 표시 
	else if ($(".selectDay.first").length && fnum > num) {
		$(".selectDay").not(".last").css({ "background-color": "white", "border-radius": "0px" });
		$(".selectDay").removeClass("first");
		$(this).addClass("first");
		$(this).css({ "background-color": "#b31312", "border-radius": "100%" });
		$("#fnum,#selectFnum,#checkIn").val($(".selectDay.first").children().val());
	}





	//fnum, lnum을 input tye hidden값으로 재정의
	var fnum =
		$("#fnum").val() == "" ? "" : $("#fnum").val();
	var lnum =
		$("#lnum").val() == "" ? "" : $("#lnum").val();

	//날짜를 클릭했을때 체크인과 체크아웃사이에 날짜를 배열로 반환시켜주는 함수 
	var listDate = [];
	function getDateRange(fnum, lnum, listDate) {
		var dateMove = new Date(fnum);
		var strDate = fnum;
		if (fnum == lnum) {
			var strDate = dateMove.toISOString().slice(0, 10);
			listDate.push(strDate);
		} else {
			while (strDate < lnum) {
				var strDate = dateMove.toISOString().slice(0, 10);

				listDate.push(strDate);

				dateMove.setDate(dateMove.getDate() + 1);
			}
		}
		return listDate;
	}

	//체크인과 체크아웃 사이의 날짜로 반환된 배열을 선택한기간으로 표시
	if ($("#fnum").val() != "" && $("#lnum").val() != "") {
		getDateRange(fnum, lnum, listDate);
		$(listDate).each(function(index, item) {
			$(".selectDay").each(function(index2, item2) {
				if ($(this).children().val() == item) {
					$(this)
						.not(".first,.last")
						.css({ "background-color": "#eeeeee", "border-radius": "0" });
				}
			});
		});
	}
	
	$("#detailHotelCheckIn").val($("#fnum").val());
	$("#detailHotelCheckOut").val($("#lnum").val());
})