<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<link rel="stylesheet" href="${path }/css/noticeAndCommunity/coStyle.css" />
<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<section>
   
	<c:if test="${loginMember!=null }">   	
   		 <div class="text-end"><button class="w-btn w-btn-red" onclick="location.href='${path }/community/communityWritePage.do'">글쓰기</button></div>
    </c:if>
    <div class="row row-cols-1 row-cols-md-3 mx-5 g-4 p-2" id="community-cards">
    
    
    

        
        
        
    
      
      </div>

</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
<script src="${path }/js/community/community.js"></script>

