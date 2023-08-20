AOS.init();

$(window).scroll(function () {
  var scroll = $(window).scrollTop();
  var height = $(".navbar").height();
  if (scroll > height) {
    $(".navbar").addClass("drop");
  } else {
    $(".navbar").removeClass("drop");
  }
});

$(".main-menu").mouseover(function () {
  $(".navbar").addClass("drop");
  $("#blurSection").css("display", "block");
});

$(".main-menu").mouseout(function () {
  var scroll = $(window).scrollTop();
  var height = $(".navbar").height();
  $("#blurSection").css("display", "none");
  if (scroll < height) {
    $(".navbar").removeClass("drop");
  }
});

$(document).on("mouseover", ".travelBtn", function () {
  $(this).parent().children(".blurImage").fadeOut(300, "linear");
});

$(document).on("mouseout", ".travelBtn", function () {
  console.log(1);
  $(this).parent().children(".blurImage").fadeIn(300, "linear");
});

const communityCardView=(no)=>{
	console.log(no);
	location.href=path+"community/communityView.do?no="+no;
}


$(document).ready(

	
	$.ajax({
		url:path+"/community/communityMain.do",
		type: "get",
		success:(data)=>{
			console.log(data);
			for(let c of data){
			let str="<div class='card' onclick='communityCardView("+c.communityNo+")'>";
			str+="<div class='card-image'>";
            str+="<a><img src='"+path+"/upload/community/"+c.thumbnail+"' /></a>";
            str+="</div>";
            str+="<div class='heading'>"+c.communityTitle;
            str+="<div class='author'>";
            str+="By <span class='name'>"+c.memberId.memberNickname+" </span>"+c.modifyDate;
            str+="</div>";
            str+="</div>";
            str+="</div>";  
            $("#communityImage").append(str);
          }
		}
	})

)


















