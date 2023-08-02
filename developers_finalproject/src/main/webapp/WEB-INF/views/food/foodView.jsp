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
	<table>
		<tbody>
			<c:forEach var="f" items="${foods}" varStatus="vs">
				<form action="/foodImgApi" >
					<tr>
						<td>
							<c:if test="${f.foodPhoto.FPMain == 1}">
								<img alt="대표이미지" src="${f.foodPhoto.FPName}" style="width: 300px;height: 300px;"><br>							
							</c:if>
							<c:if test="${f.foodPhoto.FPMain != 1}"></c:if>
							<input type="text" name="foodNo" value="${f.foodNo}"><br>
							<input type="text" name="foodName" value="${f.foodName}"><br>
							<input type="text" name="foodOpenTime" value="${f.foodOpenTime}"><br>
							<input type="text" name="foodMenu" value="${f.foodMenu}"><br>
							<input type="text" name="foodPhone" value="${f.foodPhone}"><br>
							<input type="text" name="foodAddress" value="${f.foodAddress}">
							<button>상세정보(img)</button>
							<br><br>
						</td>
					</tr>
				</form>
			</c:forEach>
		</tbody>
		</table>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>