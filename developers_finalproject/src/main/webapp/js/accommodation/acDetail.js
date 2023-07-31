$(document).ready(function() {
	if ($(".detailHotelInfoCon pre").height() > 300) {
		$(".detailHotelInfoBtn").css("display", "flex");
		$(".detailHotelInfoCon pre").height(300);
	}
});












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
			imageUrl: "./image/heartIcon.png",
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
