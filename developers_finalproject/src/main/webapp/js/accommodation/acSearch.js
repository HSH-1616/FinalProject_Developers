$(document).ready(function() {
	calender()
	const queryString = window.location.search;
	const urlParams = new URLSearchParams(queryString);
	const loc = urlParams.get('loc');
	const checkIn = urlParams.get('checkIn');
	const checkOut = urlParams.get('checkOut');
	const people = urlParams.get('people');


	checkIn.substr(4, 2) + "월 " + checkIn.substr(6, 2) + "일"
	$("#searchLocInput").val(loc)
	$("#checkInDay").val(checkIn.substr(5, 2) + "월 " + checkIn.substr(8, 2) + "일")
	$("#checkOutDay").val(checkOut.substr(5, 2) + "월 " + checkOut.substr(8, 2) + "일")
	$("#people").val(people)
	$("#peopleCount").val(people.substr(0,people.length-1))
	$("#fnum").val(checkIn)
	$("#lnum").val(checkOut)
	
	
	$(".day").each(function(index, obj) {
		if (checkIn != "" && $(this).children().val() == checkIn) {

			$(this).addClass("first")
			$("#inDayBtn").show()
		} else if (checkOut != "" && $(this).children().val() == checkOut) {
			$(this).addClass("last")
			$("#outDayBtn").show()
		}
	});

	$(".checkDay > div > ion-icon").click(function() {
		if ($(this).attr("id") == "inDayBtn") {
			$(".day").removeClass("first").removeClass("last");
			$("#inDayBtn,#outDayBtn").hide();
			$("#checkInDay,#checkOutDay").val("날짜 추가");
			$(".day").css("background-color", "white");
		} else {
			$(".day").removeClass("last");
			$("#outDayBtn").hide();
			$("#checkOutDay").val("날짜 추가");
			$(".day").not(".first").css("background-color", "white");
		}
	});

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

		/*$(".calBtn").click(function() {
		$(".day").each(function(index, obj) {
			if (checkIn != "" && $(this).children().val() == checkIn) {

				$(this).addClass("first")
				$("#inDayBtn").show()
			} else if (checkOut != "" && $(this).children().val() == checkOut) {
				$(this).addClass("last")
				$("#outDayBtn").show()
			}
		});
		
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
	})*/

})

