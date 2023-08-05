$(document).ready(function() {

	var queryString = window.location.search;
	var urlParams = new URLSearchParams(queryString);
	var checkIn = urlParams.get('checkIn');
	var checkOut = urlParams.get('checkOut');
	var people = urlParams.get('people');
	
	console.log(people.replace("명",""))
	console.log($("#exPrice").text().replace(",",""))
	var maxPeople=people.replace("명","")
	
	for(var i=1;i<=maxPeople;i++){
		if(i>2){
			console.log(1)
			$("#exPrice").text(String(Number($("#exPrice").text().replace(",",""))+20000).replace(/\B(?=(\d{3})+(?!\d))/g, ","))
		}
	}
	
	const oldDate = new Date(checkIn);
	const newDate = new Date(checkOut);
	let diff = Math.abs(newDate.getTime() - oldDate.getTime());
	diff = Math.ceil(diff / (1000 * 60 * 60 * 24));
	$("#exDay").text(diff)
	$("#resultPrice,#realResultPrice").text(String($("#exPrice").text().replace(",", "") * diff).replace(/\B(?=(\d{3})+(?!\d))/g, ","))

})

