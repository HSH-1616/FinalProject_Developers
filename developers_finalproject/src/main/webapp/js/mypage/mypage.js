$('.favorite').slick({
  lazyLoad: 'ondemand',
  slidesToShow: 3,
  slidesToScroll: 1
});

const mypageCommunity=(cPage,numPerpage)=>{
	const memberId = $('.nickname').text();
	
	$.ajax({
		url:"/community/mypageCommunity.do",
		type:"post",
		data:{memberId:memberId,cPage:cPage,numPerpage:numPerpage},
		success:(data)=>{
			console.log(data);
		}
	});
}


$(document).ready(function() {
  $('.gotop').bind('click', function() {
    $('html, body').animate({scrollTop: '0'}, 100);
  });
});