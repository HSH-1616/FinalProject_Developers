<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<link rel="stylesheet" href="${path }/css/accommodation/acPayResult.css" />
<section>
	<div>
		<div id="timerCon">
			<div id="timer">
				<h3>예약이완료 되었습니다.</h3>
				<h3>
					<span>10</span>초 후에 예약내역으로 이동합니다.
				</h3>
				<img src="${path}/images/accommodation/payCheck.png" alt="" />
				<div>
					<button>예약내역 바로가기</button>
				</div>
			</div>
		</div>
	</div>
	<script src="${path }/js/accommodation/acPayResult.js"></script>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />