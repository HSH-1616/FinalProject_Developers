<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<link rel="stylesheet" href="${path }/css/accommodation/acError.css" />
<section>
	<div>
		<div id="errorCon">
			<div id="error">
				<h3>잘못된 요청입니다.</h3>				
				<img src="${path}/images/accommodation/acError.png" alt="" />
				<div>
					<button onclick="history.back()">뒤로가기</button>
				</div>
			</div>
		</div>
	</div>
	<script src="${path }/js/accommodation/acPayResult.js"></script>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />