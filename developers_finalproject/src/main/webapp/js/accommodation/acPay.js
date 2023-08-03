var queryString = window.location.search;
var urlParams = new URLSearchParams(queryString);
var checkIn = urlParams.get('checkIn');
var checkOut = urlParams.get('checkOut');
var people = urlParams.get('people');

$(document).ready(function() {
	$("input[name=checkIn]").val(checkIn)
	$("input[name=checkOut]").val(checkOut)
	$("input[name=people]").val(people)

	var maxPeople = people.replace("명", "")

	for (var i = 1; i <= maxPeople; i++) {
		if (i > 2) {
			$("#exPrice").text(String(Number($("#exPrice").text().replace(",", "")) + 20000).replace(/\B(?=(\d{3})+(?!\d))/g, ","))

		}
	}

	const oldDate = new Date(checkIn);
	const newDate = new Date(checkOut);
	let diff = Math.abs(newDate.getTime() - oldDate.getTime());
	diff = Math.ceil(diff / (1000 * 60 * 60 * 24));
	$("#exDay").text(diff)
	$("#resultPrice,#realResultPrice").text(String($("#exPrice").text().replace(",", "") * diff).replace(/\B(?=(\d{3})+(?!\d))/g, ","))

	$("input[name=acPrice]").val(Number($("#resultPrice").text().replace(",", "")))
})

$("#kakaoPay").on("click", function() {

	$.ajax({
		url: "/pay/kakao",
		method: "GET",
		data: {
			acId: $("input[name=acId").val(),
			memberId: $("input[name=userId").val(),
			acTitle: $("input[name=acTitle]").val(),
			acPrice: $("input[name=acPrice]").val(),
			people: people,
			checkIn: checkIn,
			checkOut: checkOut,
		},
		success: function(data) {
			window.open(data.next_redirect_pc_url,"_blank", "width=500, height=700");
		}
})
})