<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${path }/css/noticeAndCommunity/coStyle.css" />

<jsp:include page="/WEB-INF/views/common/header.jsp"/> 
    <section>
        <div class="rap m-4">
            <div class="title_box m-4">
                <span>${notice.updateDate } update</span>
                
                <h3 class="title">${notice.noticeTitle }</h3>
                
            </div>
            <div class="col-12 border-bottom border-dark boarder-3"></div>
            <div class="content_area text-center p-3" style="min-height: 500px;">
                <div class="content" id="ctn">
           
                    ${notice.noticeContent }
                </div>
            </div>
           
            
            
        </div>
			<div class="text-center mb-3">
				<button type="button" class="s-btn" onclick="location.href='${path}/notice/noticeList.do'">목록</button> 
				<c:if test="${loginAdmin!=null }">
				<button type="button" class="s-btn" onclick="location.href='${path }/notice/updateNoticePage.do?no=${notice.noticeNo}'">수정하기</button>
	            <button type="button" class="s-btn" onclick="deleteNotice(${notice.noticeNo}); $('.removeBtn').trigger('click');">삭제하기</button>
	      		</c:if>
            </div>
    </section>
    <script src="${path }/js/notice/notice.js"></script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/> 







