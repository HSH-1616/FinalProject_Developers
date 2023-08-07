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

<link rel="stylesheet" href="${path }/css/touris/tourisDetail.css" />
<script
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=737cdc9322ba7fec26566e0863b53498&libraries=services,clusterer"></script>
<script>
	var tourisId=${touris.tourisId}
	var memberId=${loginMember.memberId}
	console.log('${loginMember.tourisHeart[0]}');
</script>
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
						<c:if test="${loginMember==null}">
							<ion-icon id="detailHeartOff" name="heart-outline"></ion-icon>
							<ion-icon id="detailHeartOn" name="heart"></ion-icon>
						</c:if>
						<c:if test="${loginMember.tourisHeart[0].tourisId !=null && loginMember.tourisHeart[0].tourisId ne touris.tourisId}">
							<ion-icon id="detailHeartOff" name="heart-outline"></ion-icon>
							<ion-icon id="detailHeartOn" name="heart"></ion-icon>
						</c:if>
						<c:if test="${loginMember.tourisHeart[0].tourisId !=null && loginMember.tourisHeart[0].tourisId eq touris.tourisId}">
							<ion-icon id="detailHeartOff" name="heart-outline" style="display:none;"></ion-icon>
							<ion-icon id="detailHeartOn" name="heart" style="display:inline;"></ion-icon>
						</c:if>
							찜하기
						</button>
					</div>
				</div>
			</div>
			<div class="hotelCard">
				<div id="carouselExampleIndicators"class="carousel slide">
				  	<div class="carousel-indicators">
					  	<c:forEach var="i" begin="0" end="${fn:length(touris.tourisImages)}" step="1">
						    <button type="button" 
						    		data-bs-target="#carouselExampleIndicators" 
						    		data-bs-slide-to="${i}" class="active" aria-current="true"
									aria-label="Slide ${i+1}">
							</button>
					    </c:forEach>
					</div>
				  <div class="carousel-inner">
					    <div class="carousel-item active">
					      <img class="d-block w-100" src="${touris.tourismainImge}" alt="First slide" width="500px" height="333px">
					    </div>
				 	<c:forEach var="image" items="${touris.tourisImages}">
					    <div class="carousel-item">
					      <img class="d-block w-100" src="${image.tourisImages}" alt="Next slide" width="500px" height="333px">
					    </div>
					</c:forEach>
				  </div>
					<button class="carousel-control-prev" type="button"
							data-bs-target="#carouselExampleIndicators"
							data-bs-slide="prev">
							<span class="carousel-control-prev-icon" aria-hidden="true"></span>
							<span class="visually-hidden">Previous</span>
						</button>
						<button class="carousel-control-next" type="button"
							data-bs-target="#carouselExampleIndicators"
							data-bs-slide="next">
							<span class="carousel-control-next-icon" aria-hidden="true"></span>
							<span class="visually-hidden">Next</span>
					</button>
				</div>
<!-- 		          <div class="">
		            <table class="text-start align-top food_info_table">
		              <tr class="">
		                <th valign="top"><i class="fa-solid fa-location-dot fa-2xl" style="color: #000000;"></i></th>
		                <th class="fs-4" valign="top">영업시간</th>
		                <td>
		                  <div class="food_runtime">매일 12:00 ~ 22:00</div>
		                  <div class="food_runtime">휴게시간 15:00 ~ 18:00</div>
		                </td>
		              </tr>
		              <tr>
		                <th valign="top"><i class="fa-solid fa-utensils fa-2xl" style="color: #000000;"></i></th>
		                <th class="fs-4" valign="top">메뉴</th>
		                <td>
		                  <div class="food_main_mainmenu">젓갈스파게티 19,000원</div>
		                  <div class="food_main_mainmenu">런치코스 36,000원</div>
		                  <div class="food_main_mainmenu">디너코스 79,000원</div>
		                </td>
		              </tr>
		              <tr>
		                <th valign="top"><i class="fa-solid fa-phone fa-2xl" style="color: #000000;"></i></th>
		                <th class="fs-4" valign="top">연락처</th>
		                <td>
		                  <div class="food_main_phone">010-1234-1234</div>
		                </td>
		              </tr>
		            </table>
		          </div> -->
			</div>
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
