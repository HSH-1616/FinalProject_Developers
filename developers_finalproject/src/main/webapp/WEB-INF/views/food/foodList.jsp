<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
  <c:set var="path" value="${pageContext.request.contextPath}"/> 
<jsp:include page="/WEB-INF/views/common/header.jsp">
   <jsp:param name="title" value=""/>
</jsp:include>
<section>

		${fn:length(contentId)}
		<table>
		<tbody>
			<c:forEach var="id" items="${contentId}" varStatus="vs">
				<form action="/foodInfoApi" >
					<tr>
						<td>
							<c:set var="foodId" value="${id}"/>				
							<input type="text" name="contentId" value="${foodId}">
							<button>상세정보</button>
						</td>
					</tr>
				</form>
			</c:forEach>
		</tbody>
		</table>
		
   
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>