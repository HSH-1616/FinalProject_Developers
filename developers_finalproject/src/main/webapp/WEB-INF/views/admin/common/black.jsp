<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Black 회원처리하기</title>
<script src="${path }/js/jquery-3.7.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
<style>
	div#m-black{
		text-align:center;
		padding-top:50px;		
	}
</style>
<body>
	<div id="m-black">
		<span>블랙회원 사유를 등록해주세요</span>
		<br><br>	
			<select id="m-selectbox" style="width:400px; height:30px;">
				<option value='블랙컨슈머'>블랙컨슈머</option>
				<option value='욕설도배'>욕설도배</option>
				<option value='기타사유 문의'>기타사유 문의</option>
			</select>
			<br>
			<br>
			<button id="m-blackbtn" class="btn btn-dark">등록</button>
	</div>
</body>
<script>
$("#m-blackbtn").click(e=>{
	console.log("여기로오니?");
	let memberId=${memberId}
	let blackReason=$("#m-selectbox").val();
	console.log(memberId,blackReason);
	$.get("${path}/admin/black?memberId="+memberId+"&blackReason="+blackReason,data=>{
		console.log(data);
		alert("회원을 블랙회원처리 하였습니다.");
		location.assign("${path}/admin/selectMemberAll");
		opener.location.reload();
		close();
	});
})
</script>
</html>