$(document).ready(function() {
	console.log(checkInOutDay)
});

// input 정규식

$(document).on("input", "#acTitle", function() {
	const regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/g;
	const val = $(this).val();
	const reg = $(this).val().replace(regExp, "");
	const test = regExp.test(val);
	const leng = $(this).val().length;
	$(".textNumCheck span").text(leng);
	if (test || leng > 29) {
		if (test) {
			$(this).val(reg);
			$(".warnReg").text("특수 기호는 허용되지 않습니다.");
			$(".warnReg").show();
		} else {
			$(this).val(val.slice(0, 29));
			$(".warnReg").text("최대 30자까지 입력 가능합니다.");
			$(".textNumCheck").css("color", "#b31312");
			$(".textNumCheck span").css("color", "#b31312");
			$(".warnReg").show();
		}
	} else {
		$(".warnReg").hide();
		$(".textNumCheck").css("color", "black");
		$(".textNumCheck span").css("color", "black");
	}
});

/*$(document).on("input", "#acPrice", function() {
	const regExp = /[^0-9]/g;
	const val = $(this).val();
	const reg = $(this)
		.val()
		.replace(regExp, "")
		.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	const test = regExp.test(val);
	var val2 = $(this).val(reg);
		
});*/

$(document).on("input", "#acContent", function() {
	const val = $(this).val();
	const leng = val.length;
	$("#contentCheckNum span").text(leng);
	if (leng > 1999) {
		$(this).val(val.slice(0, 1999));
		$("#contentCheckNum").css("color", "#b31312");
		$("#contentCheckNum span").css("color", "#b31312");
	} else {
		$("#contentCheckNum").css("color", "black");
		$("#contentCheckNum span").css("color", "black");
	}
});

// /input 정규식/

// 숙박업소 유형 체크

$(document).on("click", ".rt", function() {
	$("input[name=acType]").prop("checked", false)
	$(".blurRt").removeClass("on");
	$(".rtTitle").removeClass("on");
	$(this).children(".blurRt").addClass("on");
	$(this).children(".rtTitle").addClass("on");
	$(this).children("input").prop("checked", true)
	console.log($("input[name=acType]:checked").val())
});

// /숙박업소 유형 체크/

// 휴무일 체크
setTimeout(function() {
	$(document).on("click", ".day", function() {
		if ($(this).hasClass("first")) {
			$("#holyStart").val($(this).children().val())
			$("#startBtn").show()

		} else if ($(this).hasClass("last")) {
			$("#holyLast").val($(this).children().val())
			$("#lastBtn").show()
		}
	})
}, 100)

function resetStart() {
	$("#holyInput>div>input").val("")
	$("#holyInput>div ion-icon").hide()
	$(".day").removeClass("first")
	$(".day").removeClass("last")
	$("#fnum").val("")
	$("#lnum").val("")
	$(".day").css({ "background-color": "white", "border-radious": 0 })
}

function resetLast() {
	$("#lastBtn").prev().val("")
	$("#lastBtn").hide()
	$(".day").removeClass("last")
	$("#lnum").val("")
	$(".day").not(".first").css({ "background-color": "white", "border-radious": 0 })
}

$("#holyInput>div ion-icon").on("click", function() {
	if ($(this).attr("id") == "startBtn") {
		resetStart()
	} else {
		resetLast()
	}

})


$(document).on("click", "#holyBtn", function() {
	const con = $("<div>")
	const span = $("<span>휴무일</span>")
	const start = $("<input type='text' name='checkIn' readonly>")
	const and = "~"
	const last = $("<input type='text' name='checkOut' readonly>")
	const btn = $("<button type='button'>삭제</button>")

	con
		.append(span)
		.append(start)
		.append(and)
		.append(last)
		.append(btn)

	if ($("#holyStart").val() != "" && $("#holyLast").val() != "") {
		$("#holyResult").append(con)
		$("#holyResult>div").last().children("input[name=checkIn]").val($("#holyStart").val())
		$("#holyResult>div").last().children("input[name=checkOut]").val($("#holyLast").val())
		checkHolyDay.push({
			checkIn: $("#holyStart").val(),
			checkOut: $("#holyLast").val()
		})
		resetStart()
		resetLast()
	}

	calender()
})

$(document).on("click", "#holyResult button", function() {
	$(this).parent("div").remove()
	for (var i = 0; i < checkHolyDay.length; i++) {
		if (checkHolyDay[i].checkOut === $(this).prev().val()) {
			checkHolyDay.splice(i, 1);
			calender()			
		}
	}
	
})

// /휴무일 체크/

// 편의시설 체크

$(document).on("click", ".blurFc", function() {
	if ($(this).hasClass("on")) {
		$(this).next().val(0);
		$(this).removeClass("on");
		$(this).parent().children("p").removeClass("on");

	} else {
		$(this).addClass("on");
		$(this).next().val(1);
		$(this).parent().children("p").addClass("on");
	}
});


// /편의시설 체크/

// 편의시설 추가

$(document).on("click", ".insertFc", function() {

	var count = $(".insertFcImageCon").length;
	console.log(count)
	const copy = $(this).clone();
	const insertFcImageCon = $("<div class='insertFcImageCon'>");
	const xIcon = $("<ion-icon class='deleteFc'name='close-circle-outline'></ion-icon>")
	const insertFcImage = $("<div class='insertFcImage'>");
	const label = $("<label for='inputFileindex" + count + "'>");
	const icon = $(" <ion-icon name='images-sharp'>");
	const p = $("<p>이미지 추가</p>");
	const inputFile = $(" <input type='file' name='afalImage' id='inputFileindex" + count + "' required/>");
	// onchange='previewImg(this)'
	const insertFcImg = $("<img class='insertFcImg' src='' alt=''/>");
	const blurInsertFc = $("<div class='blurInsertFc'>");
	const insertFcDelete = $("<button type='button' class='insertFcDelete'>삭제하기</button>");
	const insertFcName = $(
		"<input type='text' id='insertFcName' name='afalName' placeholder='편의시설 이름' required/>"
	);

	label.append(icon).append(p);
	blurInsertFc.append(insertFcDelete);
	insertFcImage
		.append(xIcon)
		.append(label)
		.append(inputFile)
		.append(insertFcImg)
		.append(blurInsertFc);
	insertFcImageCon.append(insertFcImage).append(insertFcName);

	console.log(($(this).prev()).children("input").val() == "")

	if ($(".insertFcImageCon").length == 5) {
		if (($(this).prev()).children("div").children("img").attr("src") == "" || ($(this).prev()).children("input").val() == "") {

			$("#insertFcWarn").show()
		} else {
			$("#insertFcCon").append(insertFcImageCon);
			$("#insertFcWarn").hide()
			$(this).remove();
		}
	} else {
		if (($(this).prev()).children("div").children("img").attr("src") == "" || ($(this).prev()).children("input").val() == "") {
			console.log(2)
			$("#insertFcWarn").show()
		} else {
			$("#insertFcCon").append(insertFcImageCon);
			$("#insertFcCon").append(copy);
			$("#insertFcWarn").hide()
			$(this).remove();
		}
	}

	
});

$(document).on("click", ".insertFcImage label", function() {
	const index = "#" + $(this).next().attr("id");
	$(index).change(function() {
		const file = $(this);
		if (this.files && this.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				file.next().css("display", "block");
				file.next().prop("src", e.target.result);
				file.nextAll(".blurInsertFc").css("display", "block");
			};
			reader.readAsDataURL(this.files[0]);
		} else {
			file.next().prop("src", "");
		}
	});
});

$(document).on("click",".deleteFc",function(e) {
	console.log(2)
		const copy = $(".insertFc").clone();
		if ($(".insertFcImageCon").length == 6) {
			$(this).parents(".insertFcImageCon").remove();
			$("#insertFcCon").append(copy);
		} else {
			$(this).parents(".insertFcImageCon").remove();
		}
	});

$(document).on("click",".insertFcDelete",function(e) {
		const copy = $(".insertFc").clone();
	if ($(".insertFcImageCon").length == 6) {
		$(this).parents(".insertFcImageCon").remove();
			$("#insertFcCon").append(copy);
		} else {
			$(this).parents(".insertFcImageCon").remove();
		}
	})

// /편의시설 추가/

var themeObj = {
	bgColor: "#B31312", //바탕 배경색
	//searchBgColor: "", //검색창 배경색
	//contentBgColor: "", //본문 배경색(검색결과,결과없음,첫화면,검색서제스트)
	//pageBgColor: "", //페이지 배경색
	//textColor: "", //기본 글자색
	//queryTextColor: "", //검색창 글자색
	//postcodeTextColor: "", //우편번호 글자색
	emphTextColor: "#000000", //강조 글자색
	//outlineColor: "", //테두리
};

var mapContainer = document.getElementById("registMap"), // 지도를 표시할 div
	mapOption = {
		center: new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
		level: 5, // 지도의 확대 레벨
	};

//지도를 미리 생성
var map = new daum.maps.Map(mapContainer, mapOption);

//주소-좌표 변환 객체를 생성
var geocoder = new daum.maps.services.Geocoder();

//마커를 미리 생성
var marker = new daum.maps.Marker({
	position: new daum.maps.LatLng(37.537187, 127.005476),
	map: map,
});

function kakaoAddress() {
	new daum.Postcode({
		oncomplete: function(data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

			// 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
			// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			var roadAddr = data.roadAddress; // 도로명 주소 변수
			var extraRoadAddr = ""; // 참고 항목 변수

			// 법정동명이 있을 경우 추가한다. (법정리는 제외)
			// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
			if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
				extraRoadAddr += data.bname;
			}
			// 건물명이 있고, 공동주택일 경우 추가한다.
			if (data.buildingName !== "" && data.apartment === "Y") {
				extraRoadAddr +=
					extraRoadAddr !== "" ? ", " + data.buildingName : data.buildingName;
			}
			// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
			if (extraRoadAddr !== "") {
				extraRoadAddr = " (" + extraRoadAddr + ")";
			}

			// 우편번호와 주소 정보를 해당 필드에 넣는다.
			document.getElementById("aaZip").value = data.zonecode;
			document.getElementById("aaAddress").value = roadAddr;
			document.getElementById("aaJibun").value = data.jibunAddress;
			document.getElementById("aaDetail").focus();

			geocoder.addressSearch(data.address, function(results, status) {
				// 정상적으로 검색이 완료됐으면
				if (status === daum.maps.services.Status.OK) {
					var result = results[0]; //첫번째 결과의 값을 활용

					// 해당 주소에 대한 좌표를 받아서
					var coords = new daum.maps.LatLng(result.y, result.x);
					// 지도를 보여준다.
					mapContainer.style.display = "block";
					map.relayout();
					// 지도 중심을 변경한다.
					map.setCenter(coords);
					// 마커를 결과값으로 받은 위치로 옮긴다.
					marker.setPosition(coords);
				}
			});
		},
		theme: themeObj,
	}).open();
}



var sel_files = [];

$(document).ready(function() {
	$("#afImage").on("change", handleImgsFiles)
})
function handleImgsFiles(e) {
	var files = e.target.files;
	var filesArr = Array.prototype.slice.call(files);

	//파일 추가
	filesArr.forEach(function(f) {
		sel_files.push(f);
		var reader = new FileReader();

		reader.onload = function(e) {
			var img_html = "<img class='previewImg' src=\"" + e.target.result + "\" />"

			const div = $('<div class="previewImgWrap">')
			const blur = $('<div class="blurPreview"><img alt="" src="/images/accommodation/checkImage.png"></div > ')
			const main = $('<div class="mainCheck"><div>메인</div></div>')
			const icon = $('<ion-icon class="deletePreview" name="close-circle-outline" role="img"></ion-icon>')
			const input = $('<input type="hidden" name="afMain" value="N">')
			const afImage = $("<img class='previewImg' src=\"" + e.target.result + "\" />")

			div.append(blur).append(main).append(icon).append(input).append(afImage)

			$(".preview").append(div)
			if ($(".previewImgWrap").length == 1) {
				$(".previewImgWrap").first().children(".mainCheck").css("display", "flex")
				$(".previewImgWrap").first().children("input[name=afMain]").val("Y")
			}

		}
		reader.readAsDataURL(f)
	})



}

$(document).on("click",".blurPreview", function() {
	$(".mainCheck").hide()
	$("input[name=afMain]").val("N")
	$(this).next().css("display", "flex");
	$(this).nextAll("input[name=afMain]").val("Y")
})

var $item = $(document).on("click", '.deletePreview', function(e) {
	var seq = $item.index(this)
	var num = $(".deletePreview").index(this)
	var dataTransfer = new DataTransfer();
	var files = $('input[name=afImage]')[0].files;
	var fileArray = Array.from(files);
	fileArray.splice(seq, 1);
	fileArray.forEach(file => { dataTransfer.items.add(file); });
	$('input[name=afImage]')[0].files = dataTransfer.files;

	sel_files.splice(num, 1)

	if ($(this).parents(".previewImgWrap").children(".mainCheck").css("display") == "flex") {
		$(this).parents(".previewImgWrap").remove()
		$(".previewImgWrap").first().children(".mainCheck").css("display", "flex")
		$(".previewImgWrap").first().children("input[name=afMain]").val("Y")

	} else {
		$(this).parents(".previewImgWrap").remove()
	}
});

$("#registOkBtn").on("click", function() {

	acData = {
		acTitle: $("input[name=acTitle]").val(),
		acPrice: $("input[name=acPrice]").val(),
		acAddress: $("input[name=acAddress]").val(),
		acType: $("input[name=acType]:checked").val(),
		acPeople: $("input[name=acPeople]").val(),
		acRoom: $("input[name=acRoom]").val(),
		acBed: $("input[name=acBed]").val(),
		acBathRoom: $("input[name=acBathRoom]").val(),
		acContent: $("textarea[name=acContent]").val(),
		arv: checkHolyDay,
		afa: {
			afaCamera: $("input[name=afaCamera]").val(),
			afaAircon: $("input[name=afaAircon]").val(),
			afaKitchen: $("input[name=afaKitchen]").val(),
			afaWifi: $("input[name=afaWifi]").val(),
			afaWasher: $("input[name=afaWasher]").val(),
			afaParking: $("input[name=afaParking]").val(),
		}


	}

	const afalImg = [];

	var files = document.getElementsByName("afalImage")
	$.each(files, function(i, l) {
		var filesArr = Array.prototype.slice.call(l.files);
		filesArr.forEach(function(f) {
			afalImg.push(f);
		})
	})

	const form = new FormData();

	form.append("acData", JSON.stringify(acData))

	$.each(sel_files, function(i, l) {
		form.append("afImage", l)
	})

	$.each($("input[name=afMain]"), function(i, l) {
		form.append("afMain", $(this).val())
	})

	$.each(afalImg, function(i, l) {
		form.append("afalImg", l)
	})

	$.each($("input[name=afalName]"), function(i, l) {
		form.append("afalName", $(this).val())
	})

	for (let [key, value] of form) {
		console.log(key, value);
	}



	$.ajax({
		url: "/ac/insertRegist",
		type: "post",
		data: form,
		processData: false,
		contentType: false,
		success: function(result) {
			alert("등록완료")
			location.href = "/"
		},
		error: function(result) {
			location.href = "/ac/acError"
		}
	});
	
	
})

var acFiles=[]
$("#updateOkBtn").on("click", function() {

$(".previewImg").each(function(i,l){

	acFiles.push({
		afName : $(this).attr("src").replace("/images/upload/accommodation/","")
	})
})

	acData = {
		acId:$("input[name=acId]").val(),
		acTitle: $("input[name=acTitle]").val(),//update
		acPrice: $("input[name=acPrice]").val(),//update
		acAddress: $("input[name=acAddress]").val(),//update
		acType: $("input[name=acType]:checked").val(),//update
		acPeople: $("input[name=acPeople]").val(),//update
		acRoom: $("input[name=acRoom]").val(),//update
		acBed: $("input[name=acBed]").val(),//update
		acBathRoom: $("input[name=acBathRoom]").val(),//update
		acContent: $("textarea[name=acContent]").val(),//update
		arv: checkHolyDay,
		acFiles:acFiles,
		afa: {
			afaId : $("input[name=afaId]").val(),//update
			afaCamera: $("input[name=afaCamera]").val(),//update
			afaAircon: $("input[name=afaAircon]").val(),//update
			afaKitchen: $("input[name=afaKitchen]").val(),//update
			afaWifi: $("input[name=afaWifi]").val(),//update
			afaWasher: $("input[name=afaWasher]").val(),//update
			afaParking: $("input[name=afaParking]").val(),//update
		}


	}

	const afalImg = [];

	var files = document.getElementsByName("afalImage")
	$.each(files, function(i, l) {
		var filesArr = Array.prototype.slice.call(l.files);
		filesArr.forEach(function(f) {
			afalImg.push(f);
		})
	})

	const form = new FormData();

	form.append("acData", JSON.stringify(acData))

	$.each(sel_files, function(i, l) {
		form.append("afImage", l)
	})

	$.each($("input[name=afMain]"), function(i, l) {
		form.append("afMain", $(this).val())
	})

	$.each(afalImg, function(i, l) {
		form.append("afalImg", l)
	})
	
	$.each($(".insertFcImg"), function(i, l) {
		form.append("afalImgSrc", $(this).attr("src").replace("/images/upload/accommodation/afal/",""))
	})
	
	$.each($("input[name=afalName]"), function(i, l) {
		form.append("afalName", $(this).val())
	})

	for (let [key, value] of form) {
		console.log(key, value);
	}



	$.ajax({
		url: "/ac/updateAc",
		type: "post",
		data: form,
		processData: false,
		contentType: false,
		success: function(result) {
			/*alert("수정완료")
			location.href = "/"*/
		},
		error: function(result) {
			location.href = "/ac/acError"
		}
	});
})

$("#deleteRegist").on("click", function() {
	$.ajax({
		url: "/ac/deleteRegist",
		type: "get",
		data: { acId: 40 },
		success: function(result) {
			if (result > 0) {
				alert("삭제완료")
				location.href = "/"
			}
		},
		error: function() {
			location.href = "/ac/acError"
		}
	});
})
