<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<c:forEach var="t" items="${tourlist }">
	<li class="selectlist2">
		<div class="listbox">
			<div class="listboximg">
			<c:if test="${not empty t.tourismainImge}">
				<img
					src="${t.tourismainImge }"
					alt="">
			</c:if>
			<c:if test="${empty t.tourismainImge || t.tourismainImge == null }">
				<img
					src="http://skhdt.hagiang.gov.vn/assets/img/default.jpg"
					alt="">
			</c:if>
			</div>
			<div class="listicon">
				<svg xmlns="http://www.w3.org/2000/svg" fill="none"
					viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor"
					class="w-6 h-6" width="10" color="white">
                <path stroke-linecap="round" stroke-linejoin="round"
						d="M12 21v-8.25M15.75 21v-8.25M8.25 21v-8.25M3 9l9-6 9 6m-1.5 12V10.332A48.36 48.36 0 0012 9.75c-2.551 0-5.056.2-7.5.582V21M3 21h18M12 6.75h.008v.008H12V6.75z"></path>
              </svg>
			</div>
		</div>
		<div class="listtext">
			<h7 class="listtitle-text">${t.tourisName }</h7>
			<h7 class="listtitleaddr">주소 : <h7 class="listtitle-textaddr">${t.tourisAddress }</h7></h7>
		</div>
		<div class="listbtn">
			<div class="detail-travellist">
				<a><div class="tourisidhidden" style="display: none">${t.tourisId }</div>
					<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
						fill="currentColor" class="w-6 h-6" width="20" height="20"
						color="#e0e0e0">
                <path fill-rule="evenodd"
							d="M2.25 12c0-5.385 4.365-9.75 9.75-9.75s9.75 4.365 9.75 9.75-4.365 9.75-9.75 9.75S2.25 17.385 2.25 12zm11.378-3.917c-.89-.777-2.366-.777-3.255 0a.75.75 0 01-.988-1.129c1.454-1.272 3.776-1.272 5.23 0 1.513 1.324 1.513 3.518 0 4.842a3.75 3.75 0 01-.837.552c-.676.328-1.028.774-1.028 1.152v.75a.75.75 0 01-1.5 0v-.75c0-1.279 1.06-2.107 1.875-2.502.182-.088.351-.199.503-.331.83-.727.83-1.857 0-2.584zM12 18a.75.75 0 100-1.5.75.75 0 000 1.5z"
							clip-rule="evenodd"></path>
              </svg> </a>
			</div>
			<div title="선택목록 장소에 추가" class="plus-travel">
				<a><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
						fill="currentColor" class="w-6 h-6" width="20" height="20"
						color="black">
                <path fill-rule="evenodd"
							d="M12 3.75a.75.75 0 01.75.75v6.75h6.75a.75.75 0 010 1.5h-6.75v6.75a.75.75 0 01-1.5 0v-6.75H4.5a.75.75 0 010-1.5h6.75V4.5a.75.75 0 01.75-.75z"
							clip-rule="evenodd"></path>
              </svg> </a>
			</div>
		</div>
	</li>
</c:forEach>