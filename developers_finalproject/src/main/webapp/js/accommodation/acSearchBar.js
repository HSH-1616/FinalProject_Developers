$(document).ready(function() {
	calender();
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

/*$(document).on("input", "#input-left", function (e) {
  var lval = $("#input-left").val();
  var rval = $("#input-right").val();
  $("#minVal").val(lval);
  $("#maxVal").val(rval);
});*/

$(window).scroll(function() {
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
});

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
	//renderNextCalender(nextMonth);

	function renderCalender(thisMonth, fnum, lnum) {
		//새로 들어오는 변수를 위한 데이터 정의
		currentYear = thisMonth.getFullYear();
		currentMonth = thisMonth.getMonth();
		currentDate = thisMonth.getDate();

		nextMonth = thisMonth.getMonth() + 1;
		console.log(fnum);
		console.log(lnum);
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
		var nextval =
			currentYear +
			"-" +
			(currentMonth < 8 ? "0" + (currentMonth + 2) : currentMonth + 2) +
			"-";

		// 지난달
		for (var i = prevDate - prevDay + 1; i <= prevDate; i++) {
			nowdate = nowdate + "<div class='notday'></div>";
			$(".hotelDates.now").html(nowdate);
		}

		// 이번달
		for (var i = 1; i <= nextDate; i++) {
			if (i < currentDate && currentMonth + 1 == today.getMonth() + 1) {
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
		for (var i = 1; i <= (7 - nextDay == 7 ? 0 : 7 - nextDay); i++) {
			nowdate = nowdate + "<div class='notday'></div>";
			$(".hotelDates.now").html(nowdate);
		}

		//오른쪽 달력
		for (var i = prevNextDate - prevNextDay + 1; i <= prevNextDate; i++) {
			nextdate = nextdate + "<div class='notday'></div>";
			$(".hotelDates.next").html(nextdate);
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
		for (var i = 1; i <= (7 - nextNextDay == 7 ? 0 : 7 - nextNextDay); i++) {
			nextdate = nextdate + "<div class='notday'></div>";
			$(".hotelDates.next").html(nextdate);
		}
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
		if ($(".first,.last").length) {
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
	}

	//달력 기간 구하기
	$(document).on("click", ".day", function() {
		var num = $(this).children().val().replace("-", "").replace("-", "");
		if (!$(".day.first").length) {
			// if ($("#fnum").val() == "") {
			$(this).addClass("first");
			$(this).css({ "background-color": "#b31312", "border-radius": "100%" });
			$("#inDayBtn").show();
			// }
			// else if ($("#fnum").val() != "") {
			//   console.log(2);
			//   $(this).addClass("last");
			// }
		}

		var fnum =
			$(".day.first").length == 0
				? ""
				: $(".day.first").children().val().replace("-", "").replace("-", "");
		var lnum =
			$(".day.last").length == 0
				? ""
				: $(".day.last").children().val().replace("-", "").replace("-", "");

		if ($(".day.first").length && fnum < num) {
			$(".day").not(".first").css("background-color", "white");
			$(".day").removeClass("last");
			$(this).addClass("last");
			$(this).css({ "background-color": "#b31312", "border-radius": "100%" });
			$("#outDayBtn").show();
		} else if ($(".day.first").length && fnum > num) {
			$(".day").not(".last").css("background-color", "white");
			$(".day").removeClass("first");
			$(this).addClass("first");
			$(this).css({ "background-color": "#b31312", "border-radius": "100%" });
		}

		var fnum =
			$(".day.first").length == 0
				? ""
				: $(".day.first").children().val().replace("-", "").replace("-", "");
		var lnum =
			$(".day.last").length == 0
				? ""
				: $(".day.last").children().val().replace("-", "").replace("-", "");
		var inday =
			$(".day.first").length == 0
				? "날짜 추가"
				: fnum.substr(4, 2) + "월 " + fnum.substr(6, 2) + "일";
		var outday =
			$(".day.last").length == 0
				? "날짜 추가"
				: lnum.substr(4, 2) + "월 " + lnum.substr(6, 2) + "일";
		$("#checkInDay").text(inday);
		$("#checkOutDay").text(outday);

		var listDate = [];
		var fnum =
			$(".day.first").length == 0 ? "" : $(".day.first").children().val();
		var lnum =
			$(".day.last").length == 0 ? "" : $(".day.last").children().val();

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
		if ($(".first,.last").length) {
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

		$(".checkDay > div > ion-icon").click(function() {
			if ($(this).attr("id") == "inDayBtn") {
				$(".day").removeClass("first").removeClass("last");
				$("#inDayBtn,#outDayBtn").hide();
				$("#checkInDay,#checkOutDay").text("날짜 추가");
				$(".day").css("background-color", "white");
			} else {
				$(".day").removeClass("last");
				$("#outDayBtn").hide();
				$("#checkOutDay").text("날짜 추가");
				$(".day").not(".first").css("background-color", "white");
			}
		});

		$(".day.first").length == 0
			? ""
			: $("#fnum,#checkIn").val($(".day.first").children().val());
		$(".day.last").length == 0
			? ""
			: $("#lnum,#checkOut").val($(".day.last").children().val());

	});


	// 이전달로 이동
	$(".calBtn.prev").on("click", function() {
		var fnum = $("#fnum").val();
		var lnum = $("#lnum").val();
		thisMonth = new Date(currentYear, currentMonth - 1, currentDate);

		renderCalender(thisMonth, fnum, lnum);
		prevBtn();
	});

	// 다음달로 이동
	$(".calBtn.next").on("click", function() {
		var fnum = $("#fnum").val();
		var lnum = $("#lnum").val();
		thisMonth = new Date(currentYear, currentMonth + 1, currentDate);
		renderCalender(thisMonth, fnum, lnum);
		prevBtn();
	});

	//이번달에는 이전달 버튼 비활성화
	function prevBtn() {
		if (
			currentMonth + 1 == today.getMonth() + 1 &&
			currentYear == today.getFullYear()
		) {
			$(".calBtn.prev ion-icon").css("display", "none");
		} else {
			$(".calBtn.prev ion-icon").css("display", "flex");
		}
	}
}

// /달력함수/


// 인원수 함수
function countFn(type) {
	var count = $("#peopleCount").val();

	if (type == "plus") {
		if (count < 10) {
			$("#countInfo").text("");
			$("#peopleCount").val(++count);
			$("#checkPeople input").val($("#peopleCount").val());
		} else {
			$("#countInfo").text("최소 1명부터 10명까지 선택가능 합니다.");
		}
	} else {
		if (count > 1) {
			$("#countInfo").text("");
			$("#peopleCount").val(--count);
			$("#checkPeople input").val($("#peopleCount").val());
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
