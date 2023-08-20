<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<footer>
	<div id="footer">
		<div id="teamIntro">
			<img src="${path }/images/common/logo.png" alt="" />
		</div>
		<div id="memberIntro">
			<div class="member">
				<p>강민기</p>
				<a href=""><p>소셜로그인</p></a> <a href=""><p>여행상세페이지</p></a> <a href=""><p>관리자페이지</p></a>
			</div>
			<div class="member">
				<p>김찬은</p>
				<a href=""><p>여행경로페이지</p></a> <a href=""><p>마이페이지</p></a>
			</div>
			<div class="member">
				<p>윤지환</p>
				<a href=""><p>맛집상세페이지</p></a> <a href=""><p>관리자페이지</p></a>
			</div>
			<div class="member">
				<p>조장흠</p>
				<a href=""><p>맛집목록페이지</p></a> <a href=""><p>마이페이지</p></a> <a href=""><p>관리자페이지</p></a>
			</div>
			<div class="member">
				<p>허성현</p>
				<a href=""><p>숙박업소페이지</p></a> <a href=""><p>마이페이지</p></a> <a href=""><p>관리자페이지</p></a>
			</div>
			<div class="member">
				<p>홍승우</p>
				<a href=""><p>공지사항페이지</p></a> <a href=""><p>커뮤니티페이지</p></a> <a href=""><p>상담봇</p></a>
			</div>
		</div>
	</div>
</footer>
<script src="${path }/js/default.js"></script>
</body>
</html>
