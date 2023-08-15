$('.favorite').slick({
	lazyLoad: 'ondemand',
	slidesToShow: 3,
	slidesToScroll: 1
});

const mypageCommunity = (cPage, numPerpage) => {
	const memberId = $('.nickname').text();

	$.ajax({
		url: "/community/mypageCommunity.do",
		type: "post",
		data: { memberId: memberId, cPage: cPage, numPerpage: numPerpage },
		success: (data) => {
			console.log(data);
		}
	});
}


$(document).ready(function() {
	$('.gotop').bind('click', function() {
		$('html, body').animate({ scrollTop: '0' }, 100);
	});
});

function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));
};

$(document).ready(function() {
	$.ajax({
		url: "/ac/acMyPage",
		method: "get",
		dataType: "json",
		success: function(data) {
			console.log(data)

			$(data).each(function(i, l) {
				var form = $('<form class="acPayForm" action="/ac/acRefundApply" method="post">')
				var acPay = $('<div class="acPayForm">')
				var hidden = $('<input type="hidden" name="orderId" value="' + l.apOrderId + '"/>')
				var id = $('<input type="hidden" name="acId" value="' + l.acDetail.acId + '"/>')
				var apId = $('<input type="hidden" name="apId" value="' + l.apId + '"/>')
				var listCon = $('<div class="acPayListCon">')
				var listHead = $('<div class="acPayListHead">')
				var img = ""
				$(l.acDetail.acFiles).each(function(i2, l2) {
					if (l2.afMain == "Y") {
						img = $('<img alt="" src="/images/upload/accommodation/' + l2.afName + '">')
					}
				})
				var content = $('<div class="payListContent">')
				var orderId = $('<h5>' + l.apOrderId + '</h5>')

				var card = ""
				if (l.apOrderId.substr(0, 1) == "C") {
					card = "카드"
				} else {
					card = "카카오 페이"
				}

				var contentDiv = $('<div><div><h6 id="acTitle">' + l.acDetail.acTitle + '</h6><h6>숙박기간 : ' + l.arv.checkIn + ' ~ ' + l.arv.checkOut + '</h6></div><div><h6>결제일자 : ' + l.apDate + '</h6><h6>결제수단 : ' + card + '</h6></div></div>')
				var hr = $('<hr>')

				var oldDate = new Date(l.arv.checkIn);
				var newDate = new Date(l.arv.checkOut);
				let diff = Math.abs(newDate.getTime() - oldDate.getTime());
				diff = Math.ceil(diff / (1000 * 60 * 60 * 24));
				var price = ""
				if (l.apPeople > 2) {
					price = (l.acDetail.acPrice + (20000 * (l.apPeople - 2)))
					resultPrice = price * diff
				} else {
					price = l.acDetail.acPrice
					resultPrice = price * diff
				}
				var listDetail = $('<div class="acPayListDetail">')
				/////////////////////////////////

				if (l.apCancel == 'N') {
					console.log(diff)
					var nowDay = new Date();
					var checkIn = new Date(l.arv.checkIn);
					var checkOut = new Date(l.arv.checkOut);

					var listInfo = $('<div class="payInfo">')
					var infoContent = $('<h5>결제 상세</h5><div><div><ion-icon name="people-outline"></ion-icon><span>' + l.apPeople + '명</span></div><div><ion-icon name="time-outline"></ion-icon><span>' + diff + '일</span></div></div>')
					var payPrice = $('<div class="payPrice"><div><h6>₩' + String(price).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + ' x ' + diff + '박</h6><h6>=</h6><h6>₩' + String(resultPrice).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '</h6></div></div>')

					var btn = ""

					content.append(orderId).append(contentDiv)
					listHead.append(img).append(content)
					listInfo.append(infoContent)

					if (nowDay >= checkIn && nowDay < checkOut) {
						btn = $('<button type="button" class="cancelPay off">예약 취소</button>')
						listDetail.append(listInfo).append(payPrice).append(btn)
						listCon.append(listHead).append(hr).append(listDetail)
						form.append(hidden).append(id).append(apId).append(listCon)
						$(".payListArea").append(form)
					} else if (nowDay < checkIn && nowDay < checkOut) {
						btn = $('<button class="cancelPay on">예약 취소</button>')
						listDetail.append(listInfo).append(payPrice).append(btn)
						listCon.append(listHead).append(hr).append(listDetail)
						form.append(hidden).append(id).append(apId).append(listCon)
						$(".payListArea").append(form)
					} else if (nowDay >= checkIn && nowDay >= checkOut) {
						btn = $('<button class="writeReview on">리뷰 쓰기</button>')
						listDetail.append(listInfo).append(payPrice).append(btn)
						listCon.append(listHead).append(hr).append(listDetail)
						acPay.append(hidden).append(id).append(apId).append(listCon)
						$(".beforePayListArea").append(acPay)
					}

				} else if (l.apCancel == 'R') {

					var status = $('<h5 class="refundStatus">검토중</h5>')
					var refundInfo = $('<div class="refundInfo">')

					oldDate = new Date();
					newDate = new Date(l.arv.checkIn);
					diff = Math.abs(newDate.getTime() - oldDate.getTime());
					diff = Math.ceil(diff / (1000 * 60 * 60 * 24));

					if (diff <= 3 && diff > 1) {
						risk = Math.round(l.apPrice * 0.5)
						refundPrice = Math.round(l.apPrice * 0.5)
					} else if (diff <= 1) {
						risk = Math.round(l.apPrice * 0.8)
						refundPrice = Math.round(l.apPrice * 0.2)
					} else {
						risk = 0
						refundPrice = l.apPrice
					}
					console.log(risk)

					var refundPrice = $('<h5>예약 취소 정보</h5><div class="refundPrice"><span>결제금액 : ₩' + String(l.apPrice).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '</span><span>공제액 : ₩' + String(risk).replace(/\B(?=(\d{3})+(?!\d))/g, ",")
						+ '</span></div><h6>환불 예정 금액 : ₩' + String(refundPrice).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '</h6>')
					var refundReason = $('<div class="refundReason"><h5>예약 취소 사유</h5><div><h6>취소 사유 : ' + l.apr.aprReason +'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;취소 일자 : '+l.apReDate+'</h6><pre>내용 : ' + l.apr.aprContent + '</pre></div></div>')
					content.append(orderId).append(status).append(contentDiv)
					listHead.append(img).append(content)
					refundInfo.append(refundPrice)
					listDetail.append(refundInfo).append(refundReason)
					listCon.append(listHead).append(hr).append(listDetail)
					acPay.append(hidden).append(id).append(apId).append(listCon)
					$(".refundCheck").append(acPay)
				}
			})
		},
		error: function(result) {
		}
	})
	setTimeout(function() {
		$.ajax({
			url: "/ac/acCheckReview",
			method: "get",
			dataType: "json",
			success: function(data) {
				$(data).each(function(i, l) {
					console.log(data)
					$.each($(".beforePayListArea").children(".acPayForm"), function(i2, l2) {
						if ($(this).find("input[name=apId]").val() == l.apId) {
							$(this).find(".writeReview").removeClass("on").addClass("off")
						}
					})
				})
			}
		})
	}, 500)
})

$(".value.ac").on("click", function() {
	$(".payListArea").hide()
	$(".beforePayListArea").hide()
	$(".refundListArea").hide()
	$('.' + $(this).attr("id")).show()
})

$(document).ready(function() {
	$("#arfName").on("change", handleImgsFiles)
})
var sel_files = []
function handleImgsFiles(e) {
	var files = e.target.files;
	var filesArr = Array.prototype.slice.call(files);
	//파일 추가
	filesArr.forEach(function(f) {
		if (sel_files.length > 4) {
			Swal.fire({
				icon: 'warning',
				iconColor: '#b31312',
				title: "이미지는 5장까지 등록 가능합니다.",
				confirmButtonText: "확인",
				confirmButtonColor: "#b31312",
			});
		} else {
			sel_files.push(f);

			var reader = new FileReader();

			reader.onload = function(e) {
				var img = "<img class='previewImg' src=\"" + e.target.result + "\" />"
				$(".fileBox").append(img)

				console.log(sel_files)
			}
			reader.readAsDataURL(f)
		}
	})
}

var $item = $(document).on("click", ".previewImg", function() {
	var seq = $item.index(this)
	var num = $(".previewImg").index(this)
	var dataTransfer = new DataTransfer();
	var files = $('input[name=arfName]')[0].files;
	var fileArray = Array.from(files);
	fileArray.splice(seq, 1);
	fileArray.forEach(file => { dataTransfer.items.add(file); });
	$('input[name=arfName]')[0].files = dataTransfer.files;

	sel_files.splice(num, 1)
	$(this).remove()
	console.log(num)
	console.log(sel_files)


})

$(document).on("input", ".reviewContent textarea", function() {
	const val = $(this).val();
	const leng = val.length;
	$("#contentCheckNum span").text(leng);
	if (leng > 199) {
		$(this).val(val.slice(0, 199));
		$("#contentCheckNum").css("color", "#b31312");
		$("#contentCheckNum span").css("color", "#b31312");
	} else {
		$("#contentCheckNum").css("color", "black");
		$("#contentCheckNum span").css("color", "black");
	}
});


const drawStar = (target) => {
	$(".star span").css("width", $(target).val() * 20 + "%")
}
$(document).on("click", ".writeReview.on", function() {
	$("body").css("overflow", "hidden")
	$("#blurReview").show()
	$(".reviewWriteCon").css("display", "flex")
	$(".reviewTitle").children("img").prop("src",
		$(this).parent().prevAll(".acPayListHead").children("img").attr("src"))
	$(".reviewTitle").find(".reviewAcTitle").text(
		$(this).parents(".acPayForm").find("#acTitle").text())
	$("#acId").val($(this).parents(".acPayForm").find("input[name=acId]").val())
	$("#apId").val($(this).parents(".acPayForm").find("input[name=apId]").val())
})

$(".closeReview").on("click", function() {
	$("body").css("overflow", "auto")
	$(".reviewWriteCon").hide()
	$("#blurReview").hide()
	sel_files = []
	$(".previewImg").remove()
	$(".reviewContent textarea").val("")
	$(".star span").css("width", 0)
	$(".star input").val(0)
	$("#contentCheckNum span").text("0")
})

$("#blurReview").on("click", function() {
	$("body").css("overflow", "auto")
	$(".reviewWriteCon").hide()
	$("#blurReview").hide()
	sel_files = []
	$(".previewImg").remove()
	$(".reviewContent textarea").val("")
	$(".star span").css("width", 0)
	$(".star input").val(0)
	$("#contentCheckNum span").text("0")
})



function checkRegist() {
	var content = ""
	if ($(".star input").val() == 0) {
		content = "숙박업소를 평가해주세요!"
		return warningAlert(content);
	} else if ($(".reviewContent textarea").val() == "") {
		content = "리뷰 내용을 작성해주세요."
		return warningAlert(content);
	} else {
		reviewOk()
	}
}

function warningAlert(content) {
	Swal.fire({
		icon: 'warning',
		iconColor: '#b31312',
		title: content,
		confirmButtonText: "확인",
		confirmButtonColor: "#b31312",
	});
}

function reviewOk() {
	data = {
		acId: $("#acId").val(),
		apId: $("#apId").val(),
		arContent: $(".reviewContent textarea").val(),
		arGrade: $(".star input").val()
	}

	const form = new FormData();

	form.append("reviewData", JSON.stringify(data))

	$.each(sel_files, function(i, l) {
		form.append("arfName", l)
	})

	$.ajax({
		url: "/ac/insertReview",
		type: "post",
		data: form,
		processData: false,
		contentType: false,
		success: function(data) {
			Swal.fire({
				icon: 'success',
				iconColor: '#20c997',
				title: "등록완료",
				confirmButtonText: "확인",
				confirmButtonColor: "#20c997",
			}).then((result) => {
				if (result.isConfirmed) {
					document.location.href = document.location.href;
				}
			})
		},
		error: function(data) {
			location.replace("/ac/acError")
		}

	})
}