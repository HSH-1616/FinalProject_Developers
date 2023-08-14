<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<script src="sweetalert2.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<link rel="stylesheet" href="sweetalert2.min.css">
<link rel="stylesheet"
	href="${path }/css/accommodation/acRefundTest.css" />
<section style="margin-top: 50px; margin-bottom: 50px">
	<c:if test="${ not empty apl }">
		<div style="display: flex;">
			<c:forEach var="apl" items="${apl}">
				<div style="margin-right: 80px;">
					<c:forEach var="arf" items="${apl.acDetail.acFiles}">
						<c:if test="${fn:contains(arf.afMain,'Y')}">
							<img style="width: 200px; height: 200px" alt=""
								src="${path }/images/upload/accommodation/${arf.afName}">
						</c:if>
					</c:forEach>
					<input type="hidden" id="acId" value="${apl.acDetail.acId}">
					<h6>${apl.acDetail.acTitle }</h6>
					<p>결제번호 : ${apl.apOrderId }</p>
					<p>결제자아이디 : ${apl.member.memberId }</p>
					<p>결제자 : ${apl.member.memberNickname }</p>
					<input id="apId" type="hidden" value="${apl.apId }"> <input
						id="totalPrice" type="hidden" value="${apl.apPrice }">
					<p>결제금액 : ${apl.apPrice }</p>
					<c:choose>
						<c:when test="${fn:contains(apl.apOrderId,'K')}">
							<button style="display: block" class="cancelBtn kakao">결제취소</button>
						</c:when>
						<c:when test="${fn:contains(apl.apOrderId,'C')}">
							<button style="display: block" class="cancelBtn card">결제취소</button>
						</c:when>
					</c:choose>
					<button style="display: block" class="reviewBtn">리뷰쓰기</button>

				</div>
			</c:forEach>
		</div>
		<div id="blurReview"></div>
		<div class="reviewWriteCon">
			<div class="reviewBox">
				<ion-icon class="closeReview" name="close"></ion-icon>
				<h4>"소중한 리뷰를 남겨주세요!"</h4>
				<div class="reviewTitle">
					<img alt="" src="">
					<div>
						<h6 class="reviewAcTitle"></h6>
						<span class="star"> ★★★★★ <span>★★★★★</span> <input
							type="range" oninput="drawStar(this)" value="0" step="0.1"
							min="0" max="5">
						</span>
					</div>
				</div>
				<div class="reviewFile">
					<span>* 이미지 최대 5장 등록가능</span>
					<div class="fileBox">
						<label>
							<div>
								<ion-icon name='images-sharp'></ion-icon>
								<p>이미지 추가</p>
							</div> <input id="arfName" type="file" name="arfName" hidden="true"
							accept="image/*" multiple="true">
						</label>

					</div>
				</div>
				<div class="reviewContent">
					<textarea rows="" cols=""></textarea>
					<span id="contentCheckNum"><span>0</span>/200</span>
				</div>
				<div class="btnCon">
					<button class="insertReview" onclick="checkRegist()">등록하기</button>
				</div>
			</div>
		</div>
	</c:if>
	<c:if test="${empty apl }">
		<h2>결제목록이 없습니다.</h2>
	</c:if>
</section>
<script>

$(document).ready(function() {
	$("#arfName").on("change", handleImgsFiles)
})
var sel_files=[]
function handleImgsFiles(e) {
	var files = e.target.files;
	var filesArr = Array.prototype.slice.call(files);
	//파일 추가
	filesArr.forEach(function(f) {
		if(sel_files.length>4){
			Swal.fire({
				icon: 'warning',
				iconColor: '#b31312',
				title: "이미지는 5장까지 등록 가능합니다.",
				confirmButtonText: "확인",
				confirmButtonColor: "#b31312",
			});
		}else{
			sel_files.push(f);
			
			var reader = new FileReader();

			reader.onload = function(e) {
				var img= "<img class='previewImg' src=\"" + e.target.result + "\" />"
				$(".fileBox").append(img)
				
				console.log(sel_files)		
			}		
			reader.readAsDataURL(f)
		}	
	})
}

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
	$(".star span").css("width",$(target).val()*20+"%")
}

	$(".reviewBtn").on( "click", function() {
		$("body").css("overflow","hidden")
		$("#blurReview").show()
		$(".reviewWriteCon").css("display","flex")
				$(".reviewTitle").children("img").prop("src",
						$(this).prevAll("img").attr("src"))
				$(".reviewTitle").find(".reviewAcTitle").text(
						$(this).prevAll("h6").text())
			})
			
	$(".closeReview").on("click",function(){
		$(".reviewWriteCon").hide()
		$("#blurReview").hide()
		sel_files=[]
		$(".previewImg").remove()
		$(".reviewContent textarea").val("")
		$(".star span").css("width",0)
		$(".star input").val(0)
		$("#contentCheckNum span").text("0")
	})
	
	$("#blurReview").on("click",function(){
		$(".reviewWriteCon").hide()
		$("#blurReview").hide()
		sel_files=[]
		$(".previewImg").remove()
		$(".reviewContent textarea").val("")
		$(".star span").css("width",0)
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
		registOk()
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
	
	
	$(".cancelBtn").on("click", function() {
		var apId = $(this).parent().children("#apId").val()
		var totalPrice = $(this).parent().children("#totalPrice").val()

		if ($(this).hasClass("kakao")) {
			$.ajax({
				url : "/pay/kakaoRefund",
				method : "POST",
				data : {
					apId : apId,
					acPrice : totalPrice,
				},
				success : function(data) {
					alert("결제금액 환불완료");
					document.location.href = document.location.href;
				},
				error : function(result) {
					alert("결제금액 환불실패.");
					document.location.href = document.location.href;
				}
			})
		} else {
			$.ajax({
				url : "/pay/refundIamport",
				type : "POST",
				data : {
					apId : apId,
					acPrice : totalPrice,
				},
				success : function(result) {
					alert("결제금액 환불완료");
					document.location.href = document.location.href;
				},
				error : function(result) {
					alert("결제금액 환불실패. 이유: " + result.responseText);
					document.location.href = document.location.href;
				}
			});
		}
	})
	
	
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />