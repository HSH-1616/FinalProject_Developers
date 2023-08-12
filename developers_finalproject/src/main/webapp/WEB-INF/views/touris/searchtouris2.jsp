<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<c:forEach var="t" items="${tourlist }">    
<ul class="makeroutesearchlistul">
		<li class="makeroutesearchlistli">
			<c:if
				test="${empty t.tourismainImge || t.tourismainImge == null }">
				<div class="makeroutesearchlistliimges">
					<img class="makesearchimg"
						src="http://skhdt.hagiang.gov.vn/assets/img/default.jpg" alt="">
				</div>
			</c:if> 
			<c:if test="${not empty t.tourismainImge}">
				<div class="makeroutesearchlistliimges">
					<img class="makesearchimg" src="${t.tourismainImge}" alt="">
				</div>
			</c:if>
			<div class="makeroutesearchlistliimgesicon">
				<svg xmlns="http://www.w3.org/2000/svg" fill="none"
					viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor"
					class="w-6 h-6" width="10" color="white">
                  <path stroke-linecap="round" stroke-linejoin="round"
						d="M12 21v-8.25M15.75 21v-8.25M8.25 21v-8.25M3 9l9-6 9 6m-1.5 12V10.332A48.36 48.36 0 0012 9.75c-2.551 0-5.056.2-7.5.582V21M3 21h18M12 6.75h.008v.008H12V6.75z"></path>
                </svg>
			</div>
			<div class="makeroutesearchlistdetaile">
				<span class="makeroutelisttextarea"> <h7>${t.tourisName }<br>
					<div class="smailspottext">
						${t.tourisAddress }
						<div class="makerouteplusbtnarea">
							<div title="포함되지 않은 장소에 추가" class="makerouteplusicon">
								<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20"
									fill="currentColor" class="w-5 h-5">
                            <path
										d="M10.75 6.75a.75.75 0 00-1.5 0v2.5h-2.5a.75.75 0 000 1.5h2.5v2.5a.75.75 0 001.5 0v-2.5h2.5a.75.75 0 000-1.5h-2.5v-2.5z" />
                          </svg>
							</div>
						</div>
					</div>
					</h7>
				</span>
			</div></li>
	</ul>
</c:forEach>