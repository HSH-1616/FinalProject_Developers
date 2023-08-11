$(document).on("click", "#detailHeart", function (e) {
	if ($("#detailHeartOn").css("display") == "none") {
	  $("#detailHeartOn").show();
	  $("#detailHeartOff").hide();
	  swal({
		title: "찜하기에 등록되었습니다!",
		imageUrl: jspath+"/images/accommodation/heartIcon.png",
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

function insertModal() {
	// 초기화
	$('.review_File').val('');
	$(".image_container").children().remove();
	$(".review_rating").css("width", 0 + "%");
	$(".star_rating h5").text(0 + "/5");
	// 값 변경하기
	//$(".modalForm").attr("action","/food/insertFoodReview.do");
	$(".submitModal").text("리뷰 올리기");
}

function updateModal() {
	// 초기화
	$('.review_File').val('');
	$(".image_container").children().remove();
	$(".review_rating").css("width", 0 + "%");
	$(".star_rating h5").text(0 + "/5");
	//DB에서 가져오기

	// 값 변경하기
	//$(".modalForm").attr("action","/food/updateFoodReview.do");
	$(".submitModal").text("리뷰 수정하기");
}


function setThumbnail(event) {
	const container = $(".image_container");
	container.children().remove();
	if (event.target.files.length <= 5) {
		for (var image of event.target.files) {
			var reader = new FileReader();
			reader.onload = function(event) {
				const img = $("<img>");
				img.css({
					"width": "100px", "height": "100px", "border-radius": "4px"
					, "margin-right": "10px", "margin-bottom": "10px", "border": "3px"
					, "border-style": "solid", "border-color": "rgb(100, 100, 100)"
					, "border": "1px"
				}).attr("src", event.target.result);
				container.css("margin-bottom", "15px");
				container.append(img);
			};
			reader.readAsDataURL(image);
		}
		$(".deleteAllImg").html("전체삭제&times;").attr({"style":"cursor:pointer;","onClick":"fileDeleteAll(event);"});
		// $('.deleteAllImg').on("click", fileDeleteAll(event));
	} else {
		alert("선택한 파일이 " + event.target.files.length + "개 입니다. 사진을 5개까지 줄여주세요.");
		$('.review_File').val('');
	}
}

function fileDeleteAll(event) {
	const result = confirm("전체 삭제 하시겠습니까?");
	if (result == true) {
		console.log($(event.target).prev().children()[0].files.length);
		$('.review_File').val('');
		$(".image_container").children().remove();
		$(".deleteAllImg").html("");
	}
}
//뭐 분기문 추가해야하는데 기억안남
const drawStar = (target) => {
	$(".review_rating").css("width", target.value * 10 + "%");
	$(".star_rating h5").text(target.value / 2 + "/5");
	var count = target.value/2;
	// if ($(target).prev().attr('class').includes("review_insert_rating")) {
	// } else {
	// 	$(".review_update_rating").css("width", target.value * 10 + "%");
	// 	$(".star_update_rating h5").text(target.value / 2 + "/5");
	// }
}

//리뷰 더보기 버튼
$(document).on("click", ".detailFoodInfoBtn button", function(e) {
	// console.log("start");
	if ($(".detailFoodInfoCon pre").height() == 100) {
		$(".detailFoodInfoCon pre").animate({ height: "400px" }, 700
		);
		$(this).next().html('<i class="fa-solid fa-angles-down fa-rotate-180" style="color: #000000;"></i>');
		// console.log("확대");
	} else {
		$(".detailFoodInfoCon pre").animate({ height: "100px" }, 700
		);
		$(this).next().html('<i class="fa-solid fa-angles-down" style="color: #000000;"></i>');
		// console.log("축소");
	}
	// console.log("end");
});

