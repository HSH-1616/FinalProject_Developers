<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.min.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.all.min.js"></script>

<link rel="stylesheet" href="${path }/css/accommodation/acSearchBar.css" />
<link rel="stylesheet" href="${path }/css/accommodation/acDetail.css" />
<script
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=737cdc9322ba7fec26566e0863b53498&libraries=services,clusterer"></script>
<section>
	<div class="hotelDetailCon">
		<div id="hotelDetail">
			<div class="hotelDetailTitle">
				<h2>${touris.tourisName }</h2>
				<div class="hotelDetailSubTitle">
					<div class="detailAdress">
						<input type="hidden" id="kakaoAddr" value="${touris.tourisAddress}">
						<span>${touris.tourisAddress} </span>
					</div>
					<div class="detailShare">
						<button id="detailHeart">
							<ion-icon id="detailHeartOff" name="heart-outline"></ion-icon>
							<ion-icon id="detailHeartOn" name="heart"></ion-icon>
							찜하기
						</button>
					</div>
				</div>
			</div>
	<%-- 		<div class="hotelCard">
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
			</div> --%>
			
<%-- 			<div class="hotelDetailBody">
				<div class="detailHotelCon">
					<div class="detailhotelTypeCon">
						<div class="detailHotelTitle">
							<h2>숙박장소</h2>
							<hr />
						</div>
						<div class="detailHotelType">
							<input type="hidden" id="acType" value="${ad.acType}">
							<c:if test="${ad.acType=='펜션' }">
								<div class="dt pension">
									<img src="${path}/images/accommodation/pensiondetail.png"
										alt="" />
									<p>펜션</p>
								</div>
							</c:if>
							<c:if test="${ad.acType=='호텔' }">
								<div class="dt pension">
									<img src="${path}/images/accommodation/hoteldetail.png" alt="" />
									<p>호텔</p>
								</div>
							</c:if>
							<c:if test="${ad.acType=='모텔' }">
								<div class="dt pension">
									<img src="${path}/images/accommodation/moteldetail.png" alt="" />
									<p>모텔</p>
								</div>
							</c:if>
							<div class="dt dr">
								<img src="${path}/images/accommodation/roomdetail.png" alt="" />
								<p>방 ${ad.acRoom}개</p>
							</div>
							<div class="dt dr">
								<img src="${path}/images/accommodation/beddetail.png" alt="" />
								<p>침대 ${ad.acBed}개</p>
							</div>
							<div class="dt dr">
								<img src="${path}/images/accommodation/bathroomdetail.png"
									alt="" />
								<p>욕실 ${ad.acBathRoom}개</p>
							</div>
							<div class="dt dr">
								<img src="${path}/images/accommodation/peopleDetail.png"
									alt="" />
								<p>최대 ${ad.acPeople}명</p>
							</div>
						</div>
					</div> --%>
					
					<div class="detailHotelInfoCon">
						<div class="detailHotelTitle">
							<h2>상세설명</h2>
							<hr />
						</div>
						<div class="detailHotelInfo">
							<pre>
                   				<c:out value="${touris.tourisContent}" escapeXml="false"/>
                 			</pre>
							<div class="detailHotelInfoBtn">
								<button>더보기</button>
							</div>
						</div>
					</div>
<%-- 					<div class="detailHotelReviewCon">
						<div class="detailHotelTitle">
							<h2>후기</h2>
							<hr />
						</div>
						<c:if test="${empty ad.acReviews }">
							후기가 없습니다.
						</c:if>
						<c:if test="${not empty ad.acReviews }">
							<c:forEach var="r" items="${ad.acReviews}">
								<div class="detailHotelReview">
									<div class="detailUserInfo">
										<img src="${path }/images/member/user.jpg" alt="" />
										<div>
											<span>${r.member.memberNickname}</span><span>${r.arDate}</span>
										</div>
									</div>
									<div class="starContainer">
										<c:set var="star" value="20" />
										<span class="star"> ★★★★★ <span
											style="width:${r.arGrade*20}%">★★★★★</span>
										</span>
									</div>
									<div class="detailReviewImg">
										<c:forEach var="rf" items="${r.arFiles }">
											<img
												src="${path }/images/upload/accommodation/review/${rf.arfName}"
												alt="" />
										</c:forEach>
									</div>
									<div class="detailReviewText">
										<pre>
									${r.arContent }
                   			 	</pre>
									</div>
								</div>
							</c:forEach>
						</c:if>
					</div> --%>
				</div>
			</div>
		</div>
	</div>
	<div class="detailHotelLocCon">
		<div class="detailHotelLoc">
			<div class="detailHotelTitle">
				<h2>상세위치</h2>
				<hr />
			</div>
			<div class="detailHotelMap">
				<p>${touris.tourisAddress }</p>
				<div id="mapContainer"></div>
			</div>
		</div>
	</div>
	<script>
	function getContextPath() {
		var hostIndex = location.href.indexOf(location.host) + location.host.length;
		return location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));
	};
	var img1=$(".mainImage img").attr("src")
	var img2=$(".subImage img").first().attr("src")
	var img3=$(".subImage img").last().attr("src")

		// init 체크
		if (!Kakao.isInitialized()) {
			Kakao.init('737cdc9322ba7fec26566e0863b53498');
		}
		var addr = $("#kakaoAddr").val()
		var geocoder = new kakao.maps.services.Geocoder();
		geocoder.addressSearch(addr,function(result, status) {
							if (status === kakao.maps.services.Status.OK) {
								var coords = new kakao.maps.LatLng(result[0].y,
										result[0].x);
								var imageSize = new kakao.maps.Size(60, 60)
								var imageOption = {
									offset : new kakao.maps.Point(27, 69)
								};
								var marker = new kakao.maps.Marker({
									position : coords, // 마커의 위치 */
									
								});
								var staticMapContainer = document
										.getElementById('mapContainer'), // 이미지 지도를 표시할 div  
								staticMapOption = {
									center : coords, // 이미지 지도의 중심좌표
									level : 3, // 이미지 지도의 확대 레벨
									marker : marker
								// 이미지 지도에 표시할 마커 
								};

								// 이미지 지도를 생성합니다
								var staticMap = new kakao.maps.StaticMap(
										staticMapContainer, staticMapOption);
							}
						});
	</script>
	<script src="${path }/js/touris/tourisDetail.js"></script>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
