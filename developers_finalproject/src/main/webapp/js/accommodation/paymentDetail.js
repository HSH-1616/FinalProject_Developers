function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(
		hostIndex,
		location.href.indexOf("/", hostIndex + 1)
	);
}

var apId = $("input[name=apId]").val()
var refundPrice = $("input[name=refundPrice]").val()

function refundKaKao() {
	$.ajax({
		url: getContextPath()+"/pay/kakaoRefund",
		method: "POST",
		data: {
			apId: apId,
			refundPrice: refundPrice,
		},
		success: function(data) {
			Swal.fire({
				icon: 'success',
				iconColor: '#20c997',
				title: "승인 완료",
				confirmButtonText: "확인",
				confirmButtonColor: "#20c997",
			}).then((result) => {
				if (result.isConfirmed) {
					location.replace(getContextPath() + "/paymentList");
				}
			})
		},
		error: function(result) {
			alert("결제금액 환불실패.");
			location.replace(getContextPath() + "/paymentList");
		}
	})
}

function refundCard() {
	$.ajax({
		url: getContextPath()+"/pay/refundIamport",
		type: "POST",
		data: {
			apId: apId,
			refundPrice: refundPrice,
		},
		success: function(result) {
			Swal.fire({
				icon: 'success',
				iconColor: '#20c997',
				title: "승인 완료",
				confirmButtonText: "확인",
				confirmButtonColor: "#20c997",
			}).then((result) => {
				if (result.isConfirmed) {
					location.replace(getContextPath() + "/paymentList");
				}
			})
		},
		error: function(result) {
			alert("결제금액 환불실패. 이유: " + result.responseText);
			location.replace(getContextPath() + "/paymentList");
		}
	});
}

function rejectRefund() {
	(async () =>{
		const { value: comment } = await Swal.fire({
			title: '거절 사유를 입력해주세요.',
			input: 'text',
			inputPlaceholder: '사유 입력',
			confirmButtonText: "확인",
			confirmButtonColor: "#20c997",
		})

		// 이후 처리되는 내용.
		if (comment) {
			$.ajax({
				url: getContextPath()+"/ac/rejectRefund",
				type: "POST",
				data: {
					apId: apId,
					comment : comment,
				},
				success: function(result) {
					Swal.fire({
						icon: 'success',
						iconColor: '#20c997',
						title: "제출 완료",
						confirmButtonText: "확인",
						confirmButtonColor: "#20c997",
					}).then((result) => {
						if (result.isConfirmed) {
							location.replace(getContextPath() + "/paymentList");
						}
					})
				},
				error: function(result) {
					alert("결제금액 환불실패. 이유: " + result.responseText);
					location.replace(getContextPath() + "/paymentList");
				}
			});
		}
		
	})()
}