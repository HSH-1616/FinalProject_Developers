//const path="/member";
const CLIENT_ID="TR_SQ2GAJzrrTPobWiSh";
const redirectURI="http://localhost:8888/member/naver/callback";
const state="1";

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

const btnModal=$("#btn-modal");
btnModal.click(e=>{
	modalOn();
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
const naverlogin=()=>{
	location.assign("https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id="+CLIENT_ID+"&redirect_uri="+redirectURI+"&state="+state);
	
}
const googlelogin=()=>{
	location.assign("https://accounts.google.com/o/oauth2/v2/auth?client_id=839800773396-kvhvsj12jbcfs977u23dfa0ipci4s196.apps.googleusercontent.com&redirect_uri=http://localhost:8888/member/google/callback&response_type=code&scope=email%20profile%20openid&access_type=offline")
}
/*카카오 로그인 서비스  */
  Kakao.init("c0e169307572ef60ba8671f2af4eaff4");
	const kakaologin=()=>{
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
								image=kakao_account.profile.profile_image_url;
								console.log(email,nickname,image);
									$.ajax({
										type:"get",
										url:path+"/member/KakaoLoginCheck",
										data:{"memberEmail":email,"memberNickname":nickname,"memberImage":image},
										dataType:"text",
										success: data=>{
											console.log(data, typeof data);
												if(data==''){
											        location.assign(path+"/member/Kakaoenroll?memberEmail="+email+"&memberNickname="+nickname+"&memberImage="+image);
												}else{
													location.assign(path+"/member/KakaoLogin?memberEmail="+email);
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
/*if($("#kakaologout").length==1){
const kakaologout=$("#kakaologout");  
kakaologout.click(e=>{
	Kakao.API.request({
    	url: '/v1/user/unlink',
    	success: function(response) {
    		console.log(response);
    		//callback(); //연결끊기(탈퇴)성공시 서버에서 처리할 함수
    		location.assign(path+"/member/logout");
    	},
    	fail: function(error) {
    		console.log('탈퇴 미완료')
    		console.log(error);
    	},
	});
})
}*/

