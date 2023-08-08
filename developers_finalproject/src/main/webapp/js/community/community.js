
const communityList = (cPage, numPerpage) => {
	let result;
	console.log(cPage);
	$.ajax({
		url: "/community/communityListEnd.do",
		type: "post",
		async: false,
		data: { cPage: cPage, numPerpage: numPerpage },
		success: (data) => {
			console.log(data);
			for (const c of data) {
				let str = "<div class='col'>";
					str += "<div class='card' onclick='communityView("+c.communityNo+")'>";
					str += "<div class='d-flex'>";
					str += "<a><img src='"+c.memberId.memberImage+"' style='height: 30px;'></a>&nbsp";
					str += "<p>닉네임</p>";
					str += "</div>";
					str += "<img src='/upload/community/" + c.thumbnail + "' class='card-img-top' >";
					str += "<div class='card-body'>";
					str += "<h5 class='card-title'>" + c.communityTitle + "</h5>";
					str += "<div class='text-end '>";
					str += "<div><img src='/images/community/heart.svg' style='height: 20px; width:auto'>" + c.likeCount + "</div>";
					str += "</div>";
					str += "<p class='card-text'>" + c.communityContent + "</p>";
					str += "</div>";
					str += "</div>";
					str += "</div>";
					$('#community-cards').append(str);
					result=str;
			}

		}
	});
	return result;
};
//$(document).ready(communityList());


const communityView=(no)=>{
	location.assign("/community/communityView.do?no="+no);
};


const target=document.querySelector('#footer');
let cPage=0;

let callback=(entries,observer)=>{
	entries.forEach(entry=>{
		if(entry.isIntersecting){
			cPage++;
			const list=communityList(cPage);
			if(list==undefined){
				observer.unobserve(target);
				return;
			}
		}
	})
}

const observer=new IntersectionObserver(callback, {threshold: 0.7});
observer.observe(target);


	$(".like-review")
			.click(
					function(e) {
						if ($(".like-review").val() == "false") {
							$(e.target)
									.html(
											'<i class="fa fa-heart" aria-hidden="true"></i> You liked this');
							$(e.target).children('.fa-heart').addClass(
									'animate-like');
							$(".like-review").val("true");
							fn_like();
							$(".like-content>img").attr("src","/images/community/fillheart.svg");
						} else {
							$(e.target)
									.html(
											'<i class="fa fa-heart" aria-hidden="true"></i>Like');
							$(e.target).children('.fa-heart').addClass(
									'animate-like');
							$(".like-review").val("false");
							fn_like();
							$(".like-content>img").attr("src", "/images/community/heart.svg");
						}
					});


const fn_like=()=>{
	const no="<c:out value='${comuView.communityNo}'/>";
	console.log(no);
}





