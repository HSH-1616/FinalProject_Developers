$(document).ready(function() {
	calender();
	$(".calBtn.prev").hide()

	/*console.log(i)
/* console.log($("#input-right").val());
console.log($("#input-right").attr("max"));
var lval = $("#input-left").val();
var rval = $("#input-right").val();
var max = $("#input-right").attr("max");
var rp = (1 - rval / max) * 100 + "%";
console.log(rp);
$(".thumb.right").css("right", rp);
$("#minVal").val(lval);
$("#maxVal").val(rval);
var ad = $(".thumb").css("left");
console.log(ad);*/
});
var checkResultDay = []
var sLLnum = 0;

/*$(document).on("input", "#input-left", function (e) {
  var lval = $("#input-left").val();
  var rval = $("#input-right").val();
  $("#minVal").val(lval);
  $("#maxVal").val(rval);
});*/

/*$(window).scroll(function() {
	var fh = $("footer").outerHeight();
	var mc = $("#mapContainer");
	var w = $(window).scrollTop();
	var d = $(document).height();
	var h = d - $(window).height() - fh;
	if (w >= h) {
		mc.addClass("ab");
	} else {
		mc.removeClass("ab");
	}
});*/

// 검색바
$(document).on("click", "#searchMini span", function(e) {
	$(".searchLabel").removeClass("active");
	$("#searchContainer").css("display", "flex");
	$("#blurSearchBar").css("display", "block");
	$(".main-menu").css("display", "none");

	var order = $(this).index() / 2;
	var id = $(this).attr("id");
	$(".search" + id + ".detail").css({
		display: "flex",
		justifyContent: "end",
	});
	$(".searchLabel").eq(order).addClass("active");
});

$(document).on("click", ".searchLabel", function(e) {
	$(".searchLabel").removeClass("active");
	$(this).addClass("active");
	if ($(this).hasClass("active")) {
		var id = $(this).children().attr("id");
		$(".detail").css("display", "none");
		$("." + id + ".detail").css({
			display: "flex",
			justifyContent: "end",
		});
	}
});

$(document).on("click", "#blurSearchBar", function(e) {
	$(".detail").css("display", "none");
	$("#searchContainer").css("display", "none");
	$("#filterDetail").removeClass("on");
	$("body").css("overflow", "scroll");
	$(this).css("display", "none");
	$(".main-menu").css("display", "flex");
	$(".detailHotelImage").fadeOut(500, "linear");
	$(".overImgCon").hide();
});
// /검색바/

// 지역함수
$(document).on("mouseover", ".searchMap path", function(e) {
	//   $(".searchMap path").css("fill", "#afafaf");
	//   $(this).css({fill: "#b31312", cursor: "pointer"});
});

$(document).on("mouseout", ".searchMap path", function(e) {
	//   $(".searchMap path").css("fill", "#afafaf");
});

$(document).on("click", ".searchMap path", function(e) {
	$(".searchMap path").css("fill", "#afafaf");
	$(this).css("fill", "#b31312");
	const text = $(this).attr("id") + "Text";
	$("#searchLocInput").val(
		$("#" + text)
			.html()
			.trim()
	);
});
$(document).on("click", ".TEXT", function(e) {
	$(".searchMap path").css("fill", "#afafaf");

	var text = $(this).attr("id");
	var textLeng = text.length;
	var textSub = text.substr(0, textLeng - 4);
	console.log(textSub);

	$("#" + textSub).css("fill", "#b31312");

	$("#searchLocInput").val($(this).html().trim());
});

// /지역함수/

//  달력함수
function calender() {
	//date 객체는 로컬 시간대를 이용해 날짜를 출력
	//어디서든 동일하게 출력하기 위해 utc와로컬 시간의 차이를 계산해서 출력해야됨

	//현재 날짜
	var date = new Date();
	// uct 표준시
	var utc = date.getTime() + date.getTimezoneOffset() * 60 * 1000;
	//한국 기준시간
	var korTime = 9 * 60 * 60 * 1000;
	//한국 시간 date
	var today = new Date(utc + korTime);

	//달력 표기 날짜객체
	var thisMonth = new Date(
		today.getFullYear(),
		today.getMonth(),
		today.getDate()
	);

	var nextMonth = new Date(
		today.getFullYear(),
		today.getMonth() + 1,
		today.getDate()
	);

	// 달력에서 표기하는 년,월,일
	var currentYear = thisMonth.getFullYear();
	var currentMonth = thisMonth.getMonth();
	var currentDate = thisMonth.getDate();

	//달력 렌더링
	renderCalender(thisMonth);


	function renderCalender(thisMonth, fnum, lnum, sFnum, sLnum, sLLnum) {
		//새로 들어오는 변수를 위한 데이터 정의
		currentYear = thisMonth.getFullYear();
		currentMonth = thisMonth.getMonth();
		currentDate = thisMonth.getDate();

		nextMonth = thisMonth.getMonth() + 1;
		// 이전 달의 마지막 날 날짜와 요일
		var startDay = new Date(currentYear, currentMonth, 0);
		var prevDate = startDay.getDate();
		var prevDay = startDay.getDay();

		var startNextDay = new Date(currentYear, nextMonth, 0);
		var prevNextDate = startNextDay.getDate();
		var prevNextDay = startNextDay.getDay();

		// 이번 달의 마지막날 날짜와 요일
		var endDay = new Date(currentYear, currentMonth + 1, 0);
		var nextDate = endDay.getDate();
		var nextDay = endDay.getDay();

		var endNextDay = new Date(currentYear, nextMonth + 1, 0);
		var nextNextDate = endNextDay.getDate();
		var nextNextDay = endNextDay.getDay();

		//현재월 출력
		$(".calMonth").text(currentYear + "년 " + (currentMonth + 1) + "월");
		//다음월 출력
		if (nextMonth >= 12) {
			$(".calNextMonth").text(currentYear + 1 + "년 1월");
		} else {
			$(".calNextMonth").text(currentYear + "년 " + (nextMonth + 1) + "월");
		}

		var nowdate = "";
		var nextdate = "";

		var val =
			currentYear +
			"-" +
			(currentMonth < 9 ? "0" + (currentMonth + 1) : currentMonth + 1) +
			"-";
		var nextval = ""
		if (currentMonth == 11) {
			nextval = currentYear + 1 + "-01-";
		} else {
			nextval = currentYear +
				"-" +
				(currentMonth < 8 ? "0" + (currentMonth + 2) : currentMonth + 2) +
				"-";
		}
		//왼쪽 달력
		// 지난달

		if (prevDay >= 6) {

		} else {
			for (var i = prevDate - prevDay; i <= prevDate; i++) {
				//지난달의 날짜는 notday class로 설정 클릭 불가능
				nowdate = nowdate + "<div class='notday'></div>";
				$(".hotelDates.now").html(nowdate);

			}
		}



		// 이번달
		for (var i = 1; i <= nextDate; i++) {
			if (i < today.getDate() && currentMonth + 1 == today.getMonth() + 1) {
				//오늘 날짜보다 이전 날짜는 notday class로 설정 클릭 불가능
				nowdate =
					nowdate +
					"<div class='notday'>" +
					i +
					"<input type='hidden' class='daysVal' value='" +
					val +
					(i < 10 ? "0" + i : i) +
					"'/>" +
					"</div>";

				$(".hotelDates.now").html(nowdate);
			} else {
				if (fnum == val + (i < 10 ? "0" + i : i)) {
					//checkin 전역함수가 있을 경우 체크인 표시		
					//이전달 다음달을 눌렀을때도 유지 되도록 달력출력하는 함수에도 별도 처리			
					nowdate =
						nowdate +
						"<div class='day first'>" +
						i +
						"<input type='hidden' class='daysVal' value='" +
						val +
						(i < 10 ? "0" + i : i) +
						"'/>" +
						"</div>";
					$(".hotelDates.now").html(nowdate);
				} else if (lnum == val + (i < 10 ? "0" + i : i)) {
					//checkOut 전역함수가 있을 경우 체크아웃 표시
					nowdate =
						nowdate +
						"<div class='day last'>" +
						i +
						"<input type='hidden' class='daysVal' value='" +
						val +
						(i < 10 ? "0" + i : i) +
						"'/>" +
						"</div>";
					$(".hotelDates.now").html(nowdate);
				} else {
					nowdate =
						nowdate +
						"<div class='day'>" +
						i +
						"<input type='hidden' class='daysVal' value='" +
						val +
						(i < 10 ? "0" + i : i) +
						"'/>" +
						"</div>";
					$(".hotelDates.now").html(nowdate);
				}
			}
		}
		// 다음달
		for (var i = 1; i <= (7 - nextDay == 7 ? 0 : 7 - nextDay - 1); i++) {
			//다음달의 날짜는 notday class로 설정 클릭 불가능
			nowdate = nowdate + "<div class='notday'></div>";
			$(".hotelDates.now").html(nowdate);
		}

		//오른쪽 달력
		if (prevNextDay >= 6) {

		} else {
			for (var i = prevNextDate - prevNextDay; i <= prevNextDate; i++) {
				nextdate = nextdate + "<div class='notday'></div>";
				$(".hotelDates.next").html(nextdate);
			}
		}
		for (var i = 1; i <= nextNextDate; i++) {
			if (fnum == nextval + (i < 10 ? "0" + i : i)) {
				nextdate =
					nextdate +
					"<div class='day first'>" +
					i +
					"<input type='hidden' class='daysVal' value='" +
					nextval +
					(i < 10 ? "0" + i : i) +
					"'/>" +
					"</div>";
				$(".hotelDates.next").html(nextdate);
			} else if (lnum == nextval + (i < 10 ? "0" + i : i)) {
				nextdate =
					nextdate +
					"<div class='day last'>" +
					i +
					"<input type='hidden' class='daysVal' value='" +
					nextval +
					(i < 10 ? "0" + i : i) +
					"'/>" +
					"</div>";
				$(".hotelDates.next").html(nextdate);
			} else {
				nextdate =
					nextdate +
					"<div class='day'>" +
					i +
					"<input type='hidden' class='daysVal' value='" +
					nextval +
					(i < 10 ? "0" + i : i) +
					"'/>" +
					"</div>";
				$(".hotelDates.next").html(nextdate);
			}
		}
		for (var i = 1; i <= (7 - nextNextDay == 7 ? 0 : 7 - nextNextDay - 1); i++) {
			nextdate = nextdate + "<div class='notday'></div>";
			$(".hotelDates.next").html(nextdate);
		}

		//이전달 다음달을 눌렀을 때에도 선택한 사이의 기간이 표시 되도록 출력하는 함수에도 별도 처리
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

		if ($("#fnum").val() != "" && $("#lnum").val() != "") {
			getDateRange(fnum, lnum, listDate);
			$(listDate).each(function(index, item) {
				$(".day").each(function(index2, item2) {
					if ($(this).children().val() == item) {
						$(this)
							.not(".first,.last")
							.css({ "background-color": "#eeeeee", "border-radius": "0" });
					}
				});
			});
		}

		//DB에서 받아온 예약 날짜 배열을 outDay클래스로 변환
		var listDate2 = []
		if (checkInOutDay.length) {
			$.each(checkInOutDay, function(i, l) {
				fnum = l.checkIn
				lnum = l.checkOut
				if (fnum != '' && lnum != '') {
					getDateRange(fnum, lnum, listDate2);
					$(listDate2).each(function(index, item) {
						$(".day").each(function(index2, item2) {
							if ($(this).children().val() == item) {
								$(this).attr("class", "outDay")
							}
						});
						$(".notday").each(function(index3, item3) {
							if ($(this).children().val() == item) {
								$(this).attr("class", "outDay")
							}
						});
					});
				}

			})
		}

		//DB에서 받아온 휴무 날짜 배열을 holyDay클래스로 변환
		var listDate3 = []
		if (checkHolyDay.length) {
			$.each(checkHolyDay, function(i, l) {
				fnum = l.checkIn
				lnum = l.checkOut
				if (fnum != '' && lnum != '') {
					getDateRange(fnum, lnum, listDate3);
					$(listDate3).each(function(index, item) {
						$(".day").each(function(index2, item2) {
							if ($(this).children().val() == item) {
								$(this).attr("class", "holyDay")
							}
						});
						$(".notday").each(function(index3, item3) {
							if ($(this).children().val() == item) {
								$(this).attr("class", "holyDay")
							}
						});
					});
				}
			})
		}
		if (sFnum != "" && sLLnum != "") {
			var listDate4 = []
			if ($("#selectFnum").val() != "") {
				getDateRange(sFnum, sLLnum, listDate4);
				$(listDate4).each(function(index, item) {
					$(".day").each(function(index2, item2) {
						if ($(this).children().val() == item) {
							$(this).attr("class", "selectDay")

						}
						if ($(this).children().val() == $("#selectFnum").val()) {
							$(this).addClass("first")
						}
						if ($(this).children().val() == $("#selectLnum").val()) {
							$(this).addClass("last")
						}
					});

				});
				$(".day").attr("class", "xDay")
			}
		}

	}

	//달력 기간 구하기
	$(document).on("click", ".day", function() {
		var num = $(this).children().val().replace("-", "").replace("-", "");

		//달력을 클릭했을때 첫번째는 checkIn으로 표시하고 fnum이라는 변수에 입력하고 html에 input hidden fnum에 값 대입
		//input type hidden을 만든 이유 : 이전달 다음달을 클릭하거나 form전송시 
		//달력 렌더링을 새로하기에 기존에 표시했던 checkIn,checkOut이 사라지기 떄문에 값 저장을위해 별도 생성


		//첫번째 클릭했을때 checkIn이 없을경우		
		if (!$(".day.first").length) {
			//첫번째 클릭했을때 checkIn이 없지만 input hidden에 값이 있을경우 
			//예시) 7/20일을 클릭해서 checkIn이 표시됬지만 달력을 넘겨서 9월달로 갔을때
			//html상에서는 기존에 체크한 값이 출력되지 않는다 하지만 input hidden에는 값이 남아있기에 
			//9월달을 클릭했을때는 checkOut으로 표시된다. 
			if ($("#fnum").val() != "") {
				if ($("#fnum").val().replace("-", "").replace("-", "") > num) {
					$(".day").not(".last").css({ "background-color": "white", "border-radius": "0px" });
					$(".day").removeClass("first");
					$(this).addClass("first");
					$(this).css({ "background-color": "#b31312", "border-radius": "100%" });
					$("#fnum,#checkIn").val($(".day.first").children().val());
				}
				else {
					$(".day").not(".first").css({ "background-color": "white", "border-radius": "0px" });
					$(".day").removeClass("last");
					$(this).addClass("last");
					$(this).css({ "background-color": "#b31312", "border-radius": "100%" });
					$("#lnum,#checkOut").val($(".day.last").children().val());
					$("#outDayBtn").show();
				}
			}
			//첫번째 클릭했을때 checkIn도 없고 input hidden에 값도 없을경우 checkIn표시
			else {
				$(".day").css({ "background-color": "white", "border-radius": "0px" });
				$(this).addClass("first");
				$(this).css({ "background-color": "#b31312", "border-radius": "100%" });
				$("#inDayBtn").show();
				$("#fnum,#checkIn").val($(".day.first").children().val());
			}
		}

		//checKIn,chekcOut의 날짜를 받는 변수 대소 비교를 위해 특수기호는 제거
		var fnum =
			$(".day.first").length == 0
				? ""
				: $(".day.first").children().val().replace("-", "").replace("-", "");
		var lnum =
			$(".day.last").length == 0
				? ""
				: $(".day.last").children().val().replace("-", "").replace("-", "");

		//달력을 클릭했을때 checkIn이 있고 checkIn이 클릭한 날짜 보다 작을 경우 checkOut 표시
		if ($(".day.first").length && fnum < num) {
			$(".day").not(".first").css({ "background-color": "white", "border-radius": "0px" });
			$(".day").removeClass("last");
			$(this).addClass("last");
			$(this).css({ "background-color": "#b31312", "border-radius": "100%" });
			$("#lnum,#checkOut").val($(".day.last").children().val());
			$("#outDayBtn").show();
		}
		//달력을 클릭했을때 checkIn이 있지만 checkIn이 클릭한 날짜 보다 클 경우 새로운 checkIn으로 표시 
		else if ($(".day.first").length && fnum > num) {
			$(".day").not(".last").css({ "background-color": "white", "border-radius": "0px" });
			$(".day").removeClass("first");
			$(this).addClass("first");
			$(this).css({ "background-color": "#b31312", "border-radius": "100%" });
			$("#fnum,#checkIn").val($(".day.first").children().val());
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
				$(".day").each(function(index2, item2) {
					if ($(this).children().val() == item) {
						$(this)
							.not(".first,.last")
							.css({ "background-color": "#eeeeee", "border-radius": "0" });
					}
				});
			});
		}



		//체크인과 체크아웃 사이에 에약날짜가 있으면 무효처리
		if ($(".outDay").length) {
			getDateRange(fnum, lnum, listDate);
			$(listDate).each(function(index, item) {
				$(".outday").each(function(index3, item3) {
					if ($(this).children().val() == item) {
						$(".day").removeClass("first last")
						$(".day").css({ "background-color": "white", "border-radius": "0px" })
						$(".xDay").css({ "background-color": "white", "border-radius": "0px" })
						$("#fnum").val("")
						$("#lnum").val("")
						$(".selectDay").not(".first").css({ "background-color": "white", "border-radius": "0px" })
						$("#detailHotelCheckIn").val("날짜추가")
						$("#detailHotelCheckOut").val("날짜추가")
						$("#inDayBtn").hide()
						$("#outDayBtn").hide()
						$("#exDay").text("1")
						$("#resultPrice").text($("#exPrice1").text())
						$("#realResultPrice").text($("#exPrice1").text())
						$("#holyStart").val("")
						$("#startBtn").hide()	
					
					}
				});
			})
		}



		//체크인과 체크아웃 사이에 휴무날짜가 있으면 무효처리
		if ($(".holyDay").length) {
			getDateRange(fnum, lnum, listDate);
			$(listDate).each(function(index, item) {
				$(".holyDay").each(function(index3, item3) {
					if ($(this).children().val() == item) {
						$(".day").removeClass("first last")
						$(".day").css({ "background-color": "white", "border-radius": "0px" })
						$(".xDay").css({ "background-color": "white", "border-radius": "0px" })
						$("#fnum").val("")
						$("#lnum").val("")
						$(".selectDay").not(".first").css({ "background-color": "white", "border-radius": "0px" })
						$("#detailHotelCheckIn").val("날짜추가")
						$("#detailHotelCheckOut").val("날짜추가")
						$("#holyStart").val("")
						$("#holyLast").val("")
						$("#inDayBtn").hide()
						$("#outDayBtn").hide()
						$("#startBtn").hide()
						$("#lastBtn").hide()
						$("#exDay").text("1")
						$("#resultPrice").text($("#exPrice1").text())
						$("#realResultPrice").text($("#exPrice1").text())
						$("#holyStart").val("")
						$("#startBtn").hide()	
					}
				});
			})
		}

		//체크인 x표시 클릭시 전부 초기화		
		$(".checkDay > div > ion-icon").on("click", function() {
			console.log(2)
			if ($(this).attr("id") == "inDayBtn") {
				
				$(".day").removeClass("first").removeClass("last");
				$("#inDayBtn,#outDayBtn").hide();
				$("#checkIn,#checkOut").val("");
				$(".day").css({ "background-color": "white", "border-radius": "0px" });
				$("#fnum").val("");
				$("#lnum").val("");
			}
			//체크아웃 x표시 클릭시 checkOut만 초기화		
			else {
				$(".day").removeClass("last");
				$("#outDayBtn").hide();
				$("#lnum").val("");
				$("#checkOut").val("");
				$(".day").not(".first").css({ "background-color": "white", "border-radius": "0px" });
			}
		});
	});

	// 이전달로 이동
	$(".calBtn.prev").on("click", function() {
		//새로 렌더링 될때 기존에 선택한 값을 유지하기 위해 변수로 넘겨준다.
		var fnum = $("#fnum").val();
		var lnum = $("#lnum").val();
		var sFnum = $("#selectFnum").val() == "" ? "" : $("#selectFnum").val();
		var sLnum = $("#selectLnum").val();
		sLLnum == "" ? "" : sLLnum
		thisMonth = new Date(currentYear, currentMonth - 1, currentDate);

		renderCalender(thisMonth, fnum, lnum, sFnum, sLnum, sLLnum);
		prevBtn();
	});

	// 다음달로 이동
	$(".calBtn.next").on("click", function() {
		//새로 렌더링 될때 기존에 선택한 값을 유지하기 위해 변수로 넘겨준다.
		var fnum = $("#fnum").val();
		var lnum = $("#lnum").val();
		var sFnum = $("#selectFnum").val();
		var sLnum = $("#selectLnum").val();
		sLLnum == "" ? "" : sLLnum
		thisMonth = new Date(currentYear, currentMonth + 1, currentDate);
		renderCalender(thisMonth, fnum, lnum, sFnum, sLnum, sLLnum);
		prevBtn();
	});

	//이번달에는 이전달 버튼 비활성화
	function prevBtn() {
		if (
			currentMonth + 1 == today.getMonth() + 1 &&
			currentYear == today.getFullYear()
		) {
			$(".calBtn.prev").hide()
		} else {
			$(".calBtn.prev").show()
		}
	}

	$("#checkIn").val($("#fnum").val());
	$("#checkOut").val($("#lnum").val());
}
// /달력함수/


// 인원수 함수
function countFn(type) {
	var count = $("#peopleCount").val();

	if (type == "plus") {
		if (count < 10) {
			$("#countInfo").text("");
			$("#peopleCount").val(++count);
			$("#checkPeople input").val($("#peopleCount").val() + "명");
		} else {
			$("#countInfo").text("최소 1명부터 10명까지 선택가능 합니다.");
		}
	} else {
		if (count > 1) {
			$("#countInfo").text("");
			$("#peopleCount").val(--count);
			$("#checkPeople input").val($("#peopleCount").val() + "명");
		} else {
			$("#countInfo").text("최소 1명부터 10명까지 선택가능 합니다.");
		}
	}
	if (count > 1) {
		$("#peopleBtn button").first().css("color", "#b31312");
	} else {
		$("#peopleBtn button").first().css("color", "#afafaf");
	}
}
// /인원수 함수/

// 필터
$(document).on("click", "#filterMini button", function(e) {
	$("#filterDetail").addClass("on");
	$("#blurSearchBar").css("display", "block");
	if ($("#filterDetail").hasClass("on")) {
		$("body").css("overflow", "hidden");
	}
});
// 가격슬라이더
const inputLeft = document.getElementById("input-left");
const inputRight = document.getElementById("input-right");

const thumbLeft = document.querySelector(".slider > .thumb.left");
const thumbRight = document.querySelector(".slider > .thumb.right");
const range = document.querySelector(".slider > .range");

const setLeftValue = () => {
	const _this = inputLeft;
	const [min, max] = [parseInt(_this.min), parseInt(_this.max)];

	// 교차되지 않게, 1을 빼준 건 완전히 겹치기보다는 어느 정도 간격을 남겨두기 위해.
	_this.value = Math.min(parseInt(_this.value), parseInt(inputRight.value) - 1);

	// input, thumb 같이 움직이도록
	const percent = ((_this.value - min) / (max - min)) * 100;
	thumbLeft.style.left = percent + "%";
	range.style.left = percent + "%";
};

const setRightValue = () => {
	const _this = inputRight;
	const [min, max] = [parseInt(_this.min), parseInt(_this.max)];

	// 교차되지 않게, 1을 더해준 건 완전히 겹치기보다는 어느 정도 간격을 남겨두기 위해.
	_this.value = Math.max(parseInt(_this.value), parseInt(inputLeft.value) + 1);

	// input, thumb 같이 움직이도록
	const percent = ((_this.value - min) / (max - min)) * 100;
	thumbRight.style.right = 100 - percent + "%";
	range.style.right = 100 - percent + "%";
};

// inputLeft.addEventListener("input", setLeftValue);
// inputRight.addEventListener("input", setRightValue);
// /가격슬라이더/

// 타입 체크
$(document).on("click", ".ht", function(e) {
	$(".ht").removeClass("on");
	$(this).addClass("on");
});
// /타입 체크/

// 방개수
$(document).on("click", ".hotelCountBtn", function(e) {
	var thisLeng = $(this).attr("class").length;
	var textLeng = "hotelCountBtn".length;
	var type = $(this).attr("class").substr(textLeng, thisLeng).trim();
	var count = $("#" + type + "Count").val();

	if ($(this).attr("id") == "plus") {
		if (type == "people") {
			if (count < 10) {
				$(".hotelCountInfo" + "." + type).text("");
				$("#" + type + "Count").val(++count);
			} else {
				$(".hotelCountInfo" + "." + type).text(
					"최소 1명부터 10명까지 선택가능합니다."
				);
			}
		} else {
			if (count < 5) {
				$(".hotelCountInfo" + "." + type).text("");
				$("#" + type + "Count").val(++count);
			} else {
				$(".hotelCountInfo" + "." + type).text(
					"최소 1개부터 5개까지 선택가능합니다."
				);
			}
		}
	} else {
		if (type == "people") {
			if (count > 1) {
				$(".hotelCountInfo" + "." + type).text("");
				$("#" + type + "Count").val(--count);
			} else {
				$(".hotelCountInfo" + "." + type).text(
					"최소 1명부터 10명까지 선택가능합니다."
				);
			}
		} else {
			if (count > 1) {
				$(".hotelCountInfo" + "." + type).text("");
				$("#" + type + "Count").val(--count);
			} else {
				$(".hotelCountInfo" + "." + type).text(
					"최소 1개부터 5개까지 선택가능합니다."
				);
			}
		}
	}
	if (type == "people") {
		if (count > 1) {
			$(".hotelCountBtn" + "." + type)
				.first()
				.css("color", "#b31312");
		} else {
			$(".hotelCountBtn" + "." + type)
				.first()
				.css("color", "#afafaf");
		}

		if (count < 10) {
			$(".hotelCountBtn" + "." + type)
				.last()
				.css("color", "#b31312");
		} else {
			$(".hotelCountBtn" + "." + type)
				.last()
				.css("color", "#afafaf");
		}
	} else {
		if (count > 1) {
			$(".hotelCountBtn" + "." + type)
				.first()
				.css("color", "#b31312");
		} else {
			$(".hotelCountBtn" + "." + type)
				.first()
				.css("color", "#afafaf");
		}

		if (count < 5) {
			$(".hotelCountBtn" + "." + type)
				.last()
				.css("color", "#b31312");
		} else {
			$(".hotelCountBtn" + "." + type)
				.last()
				.css("color", "#afafaf");
		}
	}
});
// /방개수/
// /필터/
