<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<jsp:include page="/WEB-INF/views/accommodation/acSearchBar.jsp" />
<link rel="stylesheet" href="${path }/css/accommodation/acList.css" />

<div id="hotelListContainer">
	<div id="hotelList">
		<c:choose>
			<c:when test="${not empty ac}">
				<c:forEach var="al" items="${ac}" varStatus="status">
					<div class="hotelCard">
						<a class="hotelItem" href="${path}/ac/acDetail?no=${al.acId}">
							<div class="heartContainer">
								<div class="con-like">
									<input title="like" type="checkbox" class="like" />
									<div class="checkmark">
										<svg viewBox="0 0 24 24" class="outline"
											xmlns="http://www.w3.org/2000/svg">
                      							<path
												d="M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Zm-3.585,18.4a2.973,2.973,0,0,1-3.83,0C4.947,16.006,2,11.87,2,8.967a4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,11,8.967a1,1,0,0,0,2,0,4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,22,8.967C22,11.87,19.053,16.006,13.915,20.313Z"></path>
                   									 </svg>
										<svg viewBox="0 0 24 24" class="normal"
											xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="none"
											width="30" height="30">
                      							<path
												d="M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Z"></path>
                    							</svg>
										<svg viewBox="0 0 24 24" class="filled"
											xmlns="http://www.w3.org/2000/svg">
                     								 <path
												d="M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Z"></path>
                   								 </svg>
										<svg class="celebrate" width="100" height="100"
											xmlns="http://www.w3.org/2000/svg">
                     						 <polygon points="10,10 20,20" class="poly"></polygon>
                      							<polygon points="10,50 20,50" class="poly"></polygon>
                      							<polygon points="20,80 30,70" class="poly"></polygon>
                      						<polygon points="90,10 80,20" class="poly"></polygon>
                      						<polygon points="90,50 80,50" class="poly"></polygon>
                      						<polygon points="80,80 70,70" class="poly"></polygon>
                   						 </svg>
									</div>
								</div>
							</div>
							<div id="carouselExampleIndicators${status.index}"
								class="carousel slide">
								<div class="carousel-indicators">
									<c:forEach var="i" begin="0" end="${fn:length(al.acFiles)-1}"
										step="1">
										<button type="button"
											data-bs-target="#carouselExampleIndicators${status.index}"
											data-bs-slide-to="${i}" class="active" aria-current="true"
											aria-label="Slide ${i+1}"></button>
									</c:forEach>
								</div>
								<div class="carousel-inner">
									<c:forEach var="af" items="${al.acFiles}">
										<c:if test="${fn:contains(af.afMain, 'Y')}">
											<div class="carousel-item active">
												<img src="${path}/images/upload/accommodation/${af.afName}"
													class="d-block w-100" alt="..." />
											</div>
										</c:if>
										<c:if test="${fn:contains(af.afMain, 'N')}">
											<div class="carousel-item">
												<img src="${path}/images/upload/accommodation/${af.afName}"
													class="d-block w-100" alt="..." />
											</div>
										</c:if>
									</c:forEach>
								</div>
								<button class="carousel-control-prev" type="button"
									data-bs-target="#carouselExampleIndicators${status.index}"
									data-bs-slide="prev">
									<span class="carousel-control-prev-icon" aria-hidden="true"></span>
									<span class="visually-hidden">Previous</span>
								</button>
								<button class="carousel-control-next" type="button"
									data-bs-target="#carouselExampleIndicators${status.index}"
									data-bs-slide="next">
									<span class="carousel-control-next-icon" aria-hidden="true"></span>
									<span class="visually-hidden">Next</span>
								</button>
							</div>
							<div class="itemContent">
								<div class="contentTitle">${fn:substring(al.acTitle, 0, 16)}...</div>
								<div class="contentAddress">${fn:substringBefore(al.acAddress, "시")}시</div>
								<div class="priceStar">
									<div class="contentPrice">
										₩
										<fmt:formatNumber value="${al.acPrice }" pattern="#,###" />
										/박
									</div>
									<div class="starContainer">
										<c:if test="${al.reviewGrade!='0.0'}">
											<span class="star"> ★★★★★ <span
												<c:set var="star" value="20" />
												style="width: ${al.reviewGrade * star}">★★★★★</span>
											</span>
										</c:if>
										<c:if test="${al.reviewGrade=='0.0'}">
										<span>리뷰 없음</span>
										</c:if>
									</div>
								</div>
							</div>
						</a>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<h2>숙박지 없음</h2>
			</c:otherwise>
		</c:choose>
	</div>
</div>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />