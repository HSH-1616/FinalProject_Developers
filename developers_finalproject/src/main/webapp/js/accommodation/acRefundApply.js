function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));
};

$(".value").on("click", function() {
	$(".value").removeClass("active")
	$(this).addClass("active")
	$(this).children("input").prop("checked", true)
})

function checkRefund() {
	var content = ""
	if ($("input[name=refundReason]").is(":checked") == false) {
		content = "예약취소 사유를 선택해 주세요."
	} else if ($("textarea[name=refundContent]").val() == "") {
		content = "예약취소 이유을 입력해 주세요."
	} else {
		return refundOk()
	}
	return warningAlert(content);
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

function refundOk() {

	$.ajax({
		url: getContextPath()+"/ac/insertRefund",
		type: "post",
		data: {
			apId: $("input[name=apId]").val(),
			orderId: $("input[name=apOrderId]").val(),
			refundPrice: $("input[name=refundPrice]").val(),
			refundReason: $("input[name=refundReason]:checked").val(),
			refundContent :$("textarea[name=refundContent]").val()
		},
		success:function(data){
			alert("성공")
		}
	})

}