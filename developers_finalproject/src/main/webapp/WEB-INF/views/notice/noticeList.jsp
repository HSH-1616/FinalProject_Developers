<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${path }/css/noticeAndCommunity/coStyle.css" />

<jsp:include page="/WEB-INF/views/common/header.jsp"/> 
<section>


<div class="notice-container p-3">

<div class="title text-align"><h3 class="text-center mt-2 p-2">공지사항</h3></div>
        
<div class="search-notice text-end mt-3">
	<form  class="search-form" ><!-- action="/notice/searchNotice.do" -->
	<select name="type">
		<option value="titile">제목</option>
		<option value="content">내용</option>
		<option value="title_content">제목+내용</option>
	</select>
	<input type="text" name="keyword" placeholder="검색어를 입력해주세요">
	<button type="button" class="s-btn" onclick="searchNotice();">검색</button>
	</form>
</div>  
<div class="list-area mt-3">
		<table class="table nolist-table table-hover text-center">
			<colgroup>
			    <col width="10%"  />
			    <col width="30%" />
			    <col width="20%" />
			    <col width="20%" />
                <col width="20%" />
            </colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
	
			</tbody>
		</table>
		<c:if test="${loginAdmin!=null }">
		<div class="text-end">
			<button class="w-btn w-btn-red" onclick="location.href='${path }/notice/noticeWrite.do'">작성하기</button>
		</div>
		</c:if>
	</div>
	<%-- 	<div class="write-area text-end">
				<a href="location.href='${path }/notice/noticeWrite.do'"
					class="btn btn-dark btn-sm ms-1">작성하기</a>
		</div> --%>


	<div class="board-pasing">
		<nav aria-label="notice-pageNav">
	
		  </nav>
	</div>
</div>
<script src="${path }/js/notice/notice.js"></script>
</section>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/> 
