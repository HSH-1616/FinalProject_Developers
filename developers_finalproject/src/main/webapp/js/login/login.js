$(document).ready(function(){
    let signup = $(".links").find("li").find("#signup") ; 
    let signin = $(".links").find("li").find("#signin") ;
    let reset  = $(".links").find("li").find("#reset")  ; 
    let first_input = $("form").find(".first-input");
    let hidden_input = $("form").find(".input__block").find("#repeat__password");
    let signin_btn  = $("form").find(".signin__btn");
  
    //----------- sign up ---------------------
    signup.on("click",function(e){
      e.preventDefault();
      $(this).parent().parent().siblings("h1").text("SIGN UP");
      $(this).parent().css("opacity","1");
      $(this).parent().siblings().css("opacity",".6");
      first_input.removeClass("first-input__block").addClass("signup-input__block");
      hidden_input.css({
        "opacity" : "1",
        "display" : "block"
      });
      signin_btn.text("Sign up");
    });
    
  
   //----------- sign in ---------------------
   signin.on("click",function(e){
      e.preventDefault();
      $(this).parent().parent().siblings("h1").text("SIGN IN");
      $(this).parent().css("opacity","1");
      $(this).parent().siblings().css("opacity",".6");
      first_input.addClass("first-input__block")
        .removeClass("signup-input__block");
      hidden_input.css({
        "opacity" : "0",
        "display" : "none"
      });
      signin_btn.text("Sign in");
    });
   
   //----------- reset ---------------------
   reset.on("click",function(e){
     e.preventDefault();
     $(this).parent().parent().siblings("form")
     .find(".input__block").find(".input").val("");
   })
});
	//---------------- modal-------------------
const modal = document.getElementById("modal")
function modalOn() {
    modal.style.display = "flex"
    modal.style.zIndex = "999"
    document.body.style.overflow="hidden";
}

function isModalOn() {
    return modal.style.display === "flex"
}

function modalOff() {
    modal.style.display = "none"
    document.body.style.overflow="unset";
}
const btnModal = document.getElementById("btn-modal")
btnModal.addEventListener("click", e => {
    modalOn()
})
const closeBtn = modal.querySelector(".close-area")
closeBtn.addEventListener("click", e => {
    modalOff()
})
modal.addEventListener("click", e => {
    const evTarget = e.target
    if(evTarget.classList.contains("modal-overlay")) {
        modalOff()
    }
})
window.addEventListener("keyup", e => {
    if(isModalOn() && e.key === "Escape") {
        modalOff()
    }
})

/*카카오 로그인 서비스  */
const naverlogin=()=>{
	location.assign("${path}/login/naverLogin");
}

const kakaologin=()=>{
  Kakao.init("c0e169307572ef60ba8671f2af4eaff4");
  console.log(Kakao.isInitialized()); 
	let email,nickname,image;
		Kakao.Auth.login({
			scope:'profile_nickname,account_email,profile_image',
			success:function(authObj){
				console.log(authObj);
				Kakao.API.request({
					url:'/v2/user/me',
					success:function(res){
						const kakao_account=res.kakao_account;
						console.log(kakao_account);
						email=kakao_account.email;
						nickname=kakao_account.profile.nickname;
						image=kakao_account.profile_image;
						console.log(email,nickname,image);
							$.ajax({
								url:'${path}/member/KakaoLoginCheck.do',
								data:{memberEmail:email,memberNickname:nickname,memberImage:image},
								dataType:"text",
								success: function(data){
									console.log(data, typeof data);
										if(data=='null'){
									        location.assign("${path}/member/Kakaoenroll.do?memberEmail="+email+"&memberNickname="+nickname+"&memberImage="+image);
										}else{
											location.assign("${path}/member/KakaoLogin.do?memberEmail="+email);
										}
										},
								error:(r,m,e)=>{
											console.log(r);
											console.log(m);
										}
							});
					}
				});
			}
		});
  }
  /*로그아웃 로직으로 구현  */
  function kakaologout() {
	Kakao.API.request({
    	url: '/v1/user/unlink',
    	success: function(response) {
    		console.log(response);
    		//callback(); //연결끊기(탈퇴)성공시 서버에서 처리할 함수
    		//window.location.href="<%=request.getContextPath()%>/views/member/memberLogin.jsp"
    	},
    	fail: function(error) {
    		console.log('탈퇴 미완료')
    		console.log(error);
    	},
	});
};
