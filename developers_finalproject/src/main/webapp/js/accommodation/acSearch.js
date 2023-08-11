$(window).ready(function() {
	//calender()
	var checkInOutDay = []
	var checkHolyDay = []
	var queryString = window.location.search;
	var urlParams = new URLSearchParams(queryString);
	var loc = urlParams.get('loc');
	var checkIn = urlParams.get('checkIn');
	var checkOut = urlParams.get('checkOut');
	var people = urlParams.get('people');
	$("#searchLocInput").val(loc)
	if (checkIn == "") {
		$("#checkIn").val("날짜추가")
	} else {
		$("#checkIn").val(checkIn)
	}
	if (checkOut == "") {
		$("#checkOut").val("날짜추가")
	} else {
		$("#checkOut").val(checkOut)
	}

	$("#people").val(people)
	if (people == "인원추가") {
		$("#peopleCount").val(1)
	} else {
		$("#peopleCount").val(people.substr(0, people.length - 1))
	}

	$("#fnum").val(checkIn)
	$("#lnum").val(checkOut)

	$(".day").each(function(index, obj) {
		if (checkIn != "") {
			if ($(this).children().val() == checkIn) {
				$(this).addClass("first")
			}
			$("#inDayBtn").show()
		}
		if (checkOut != "") {
			if ($(this).children().val() == checkOut) {
				$(this).addClass("last")
			}
			$("#outDayBtn").show()
		}
	});


	$(".checkDay > div > ion-icon").on("click", function() {
		if ($(this).attr("id") == "inDayBtn") {
			$(".day").removeClass("first").removeClass("last");
			$("#inDayBtn,#outDayBtn").hide();
			$("#checkInDay,#checkOutDay").val("날짜 추가");
			$(".day").css("background-color", "white");
			$("#fnum").val("");
			$("#lnum").val("");
		} else {
			$(".day").removeClass("last");
			$("#outDayBtn").hide();
			$("#lnum").val("");
			$("#checkOutDay").val("날짜 추가");
			$(".day").not(".first").css("background-color", "white");
		}
	});

	var listDate = [];
	var fnum = checkIn
	var lnum = checkOut

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

	if (fnum != '' && lnum != '') {
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

})

$(".like").on("change", function() {

	var acId = $(this).val();

	if ($(this).is(":checked")) {
		$.ajax({
			url: "/ac/insertHeart",
			data: {
				memberId: memberId,
				acId: acId
			},
			success: function(result) {
				if (result > 0) {

				}
			}
		})
	} else {
		$.ajax({
			url: "/ac/deleteHeart",
			data: {
				memberId: memberId,
				acId: acId
			},
			success: function(result) {
				if (result > 0) {

				}
			}
		})
	}

})
