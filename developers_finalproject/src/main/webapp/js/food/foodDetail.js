
function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));
 };

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

	function insertModal(loginMember) {
		if(loginMember.length==0){
			alert("로그인 후 사용 가능한 기능 입니다.");
		}
		if(loginMember.length!=0){
			// 초기화
			$('.review_File').val('');
			$(".image_container").children().remove();
			$(".review_rating").css("width", 0 + "%");
			$(".star_rating h5").text(0 + "/5");
			// 값 변경하기
			const insertContent = "작성내용은 매인페이지와 장소상세에 노출되며 매장주를 포함한 다른 사용자들이 볼 수 있으니, 서로를 배려하는 마음을 담아 작성해 주세요.";
			$("#FR_CONTENT").val(insertContent);
			if($(".submitModal").hasClass("updateFoodReview") === true){
				$(".submitModal").removeClass("updateFoodReview");
			}
			$(".submitModal").addClass("insertFoodReview");
			$(".submitModal").text("리뷰 올리기");
			$("#reviewModal").modal('show');
		}
	}


function updateModal(event) {
	// 초기화
	$('.review_File').val('');
	$(".image_container").children().remove();
	$(".review_rating").css("width", 0 + "%");
	$(".star_rating h5").text(0 + "/5");
	//DB에서 가져오기

	// 값 변경하기
	// 반복되는 부분을 찾고, 반복 전까지는 메소드로 접근, 그 이후는 클래스명으로 접근하기
	const updateContent = $(event.target).parents(".detailFoodInfoCon").find(".detailReviewText").children(".default_food_review_height").text().trim();
	console.log(updateContent);
	$("#FR_CONTENT").text("");
	$("#FR_CONTENT").text(updateContent);

	const updateGrade = $(event.target).parents(".detailFoodInfoCon").find(".starContainer").find(".star_number").next().val();
	console.log(updateGrade);
	$(".review_rating").css("width", updateGrade);
	$(".star_rating h5").text(updateGrade.replace('%','') / 20 + "/5");
	if($(".submitModal").hasClass("insertFoodReview") === true){
		$(".submitModal").removeClass("insertFoodReview");
	}
	$(".submitModal").addClass("updateFoodReview");
	$(".submitModal").text("리뷰 수정하기");
}

function insertFrNo(frNo){
	console.log(frNo);
	$("#selected_food_no").val(frNo);
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

$(document).on("click", "#detailHeart", function(e) {
	if(memberId!=null){
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
			$.get("/tourisDetail/insertHeart?tourisId="+tourisId+"&memberId="+memberId,data=>{
					//location.reload();
			});
		} else {
			$.get("/tourisDetail/deleteHeart?tourisId="+tourisId+"&memberId="+memberId,data=>{
					location.reload();
			});
			$("#detailHeartOn").hide();
			$("#detailHeartOff").show();
		}
	}else{
		alert("로그인 후 이용해주세요!");
	}
});


























//별점 확인
$(".starcountnum").click(e=>{
console.log($(".starcountnum").val()/2);
})

// $(".submitModal").click(e=>{
// 	console.log("flag");
// 	//ajax 통신(등록)
// 	if($(".submitModal").hasClass("insertFoodReview") === true){
// 	   //js가 제공하는 FormData클래스를 이용함
// 	   const form=new FormData();
// 	   //append로 서버에 전송할 데이터를 넣을 수 있음
// 	   //key-value형식으로 저장
// 	   const fileInput=$("#upFile");
// 	   //console.log(fileInput[0].files);
// 	   $.each(fileInput[0].files,(i,f)=>{
// 		  form.append("upFile",f);
// 		  console.log(form);
// 	   });
// 	   form.append("foodNo","${f.foodNo}");
// 	   //form.append("memberId","${loginMember.memberId}");
// 	   form.append("frGrade",$(".starcountnum").val()/2);
// 	   form.append("frContent",$("#FR_CONTENT").val());
// 	   console.log($(".starcountnum").val()/2);
// 	   if($(".starcountnum").val()/2 == 0){
// 		  alert("별점, 후기를 작성해주세요");
// 	   }else{
// 		  $.ajax({
// 			 url:getContextPath()+"/insertFoodReview.do",
// 			 data:form,
// 			 type:"post",
// 			 enctype: "multipart/form-data",
// 			 processData:false,
// 			 contentType:false,
// 			 cache: false,
// 			 success:data=>{
// 				alert("업로드가 완료되었습니다.");
// 				location.reload();
// 				$('window').scrollTop(0,300);
// 			 },
// 			 error:(r,s,e)=>{
// 				console.log("업로드실패 "+r.s+"\n"+"msg "+r.responseText+"\n"+"error "+e);
// 				alert("업로드 실패");
// 			 },
// 			 complete:()=>{
// 				$(".upFile").val('');
// 			 }
// 		  });
// 	   }
// 	}
// 	//ajax 통신(수정)
// 	if($(".submitModal").hasClass("updateFoodReview") === true){
// 	   const form=new FormData();
// 	   const fileInput=$("#upFile");

// 	   $.each(fileInput[0].files,(i,f)=>{
// 		  form.append("upFile",f);
// 		  console.log(form);
// 	   });
// 	   console.log($(".starcountnum").val()/2);
// 	   form.append("foodNo","${f.foodNo}");
// 	   form.append("frGrade",$(".starcountnum").val()/2);
// 	   form.append("frContent",$("#FR_CONTENT").val());
// 	   form.append("frNo",$("#selected_food_no").val());

// 	   $.ajax({
// 		  url:getContextPath()+"/updateFoodReview.do",
// 		  data:form,
// 		  type:"post",
// 		  enctype: "multipart/form-data",
// 		  processData:false,
// 		  contentType:false,
// 		  cache: false,
// 		  success:data=>{
// 			 alert("수정이 완료되었습니다.");
// 			 location.reload();
// 			 $('window').scrollTop(0,300);
// 		  },
// 		  error:(r,s,e)=>{
// 			 console.log("수정 실패 "+r.s+"\n"+"msg "+r.responseText+"\n"+"error "+e);
// 			 alert("수정 실패");
// 		  },
// 		  complete:()=>{
// 			 $(".upFile").val('');
// 		  }
// 	   });
// 	}
//  });

//ajax 통신(삭제)
// $("#remove_food_btn").click(e=>{
// 	const data = {frNo:$("#selected_food_no").val()};

// 	$.ajax({
// 	   url:getContextPath()+"/deleteFoodReview.do",
// 	   type:"post",
// 	   data:data,
// 	   success:data=>{
// 		  alert("삭제가 완료되었습니다.");
// 		  // location.reload();
// 		  $('window').scrollTop(0,300);
// 	   },
// 	   error:(r,s,e)=>{
// 		  console.log("삭제실패 "+r.s+"\n"+"msg "+r.responseText+"\n"+"error "+e);
// 		  alert("삭제 실패");
// 	   },
// 	   complete:()=>{
// 		  // $(".upFile").val('');
// 		  location.reload();
// 	   }
// 	});
//  });



// ==========================================touris===============================================
var tourisId="${touris.tourisId}";
if('${loginMember}'==''){
	var memberId=null;
}else{
	var memberId="${loginMember.memberId}";
}
console.log('${loginMember.tourisHeartlist}');