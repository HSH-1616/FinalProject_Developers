<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.min.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.all.min.js"></script>

<link rel="stylesheet" href="${path }/css/touris/tourisDetail.css" />
<script
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c0e169307572ef60ba8671f2af4eaff4&libraries=services,clusterer"></script>
<style>
.yoonBtn{
   display: flex;
   justify-content: center;
   font-weight: 400;
   text-align: center;
   border: 1px solid transparent;
   padding: .375rem .75rem;
   font-size: 1rem;
   line-height: 1.5;
   border-radius: .25rem;
   margin-bottom : 30px;
   text-align: center;
}
.btnColorDefault{
   background-color: #ffffff;
   color: #424242;
}
.btnColorRed{
   background-color: rgb(179, 19, 18);
   color: #ffffff;
}

.yoonBtn:hover{
   background-color: #cccccc;
}
</style>
<script>
	var tourisId="${touris.tourisId}";
	if('${loginMember}'==''){
		var memberId=null;
	}else{
		var memberId="${loginMember.memberId}";
	}
	console.log('${loginMember.tourisHeartlist}');
	/* console.log("이건뭐니?"+'${touris.tourisImages[0].tourisImages}'); */
</script>
<style>
	.m-table{
		border-collapse:separate;
		border-spacing: 50px 0px;
	}
</style>
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
						<c:if test="${loginMember!=null}">
							<c:set var="loop_flag" value="false" />
							<c:forEach var="list" items="${loginMember.tourisHeartlist}">
									<c:if test='${fn:contains(list.tourisId,touris.tourisId)}'>
										<ion-icon id="detailHeartOff" name="heart-outline" style="display:none;"></ion-icon>
										<ion-icon id="detailHeartOn" name="heart" style="display:inline;"></ion-icon>
										 <c:set var="loop_flag" value="true" />
									</c:if>
							</c:forEach>
							 <c:if test="${not loop_flag}">
							 		<ion-icon id="detailHeartOff" name="heart-outline" style="display:inline;"></ion-icon>
									<ion-icon id="detailHeartOn" name="heart" style="display:none;"></ion-icon>
							 </c:if>
						</c:if>
							찜하기
						</button>
					</div>
				</div>
			</div>
			
			<div class="hotelCard">
				<div id="carouselExampleIndicators"class="carousel slide" style="width:500px; height:333px;">
				  	<div class="carousel-indicators">
						    <button type="button" 
						    		data-bs-target="#carouselExampleIndicators" 
						    		data-bs-slide-to="0" class="active" aria-current="true"
									aria-label="Slide 1">
							</button>
					  	<c:forEach var="i" begin="1" end="${fn:length(touris.tourisImages)}" step="1">
						    <button type="button" 
						    		data-bs-target="#carouselExampleIndicators" 
						    		data-bs-slide-to="${i}" aria-current="true"
									aria-label="Slide ${i+1}">
							</button>
					    </c:forEach>
					</div>
				  <div class="carousel-inner">
				  <c:if test="${not empty touris.tourismainImge}">
					    <div class="carousel-item active">
					      <img class="d-block w-100 h-100" src="${touris.tourismainImge}" alt="First slide" >
					    </div>
				 	<c:forEach var="image" items="${touris.tourisImages}">
					    <div class="carousel-item">
					      <img class="d-block w-100 h-100" src="${image.tourisImages}" alt="Next slide" >
					    </div>
					</c:forEach>
				  </c:if>
  				  <c:if test="${empty touris.tourismainImge && not empty touris.tourisImages[0].tourisImages}">
					    <div class="carousel-item active">
					      <img class="d-block w-100 h-100" src="${touris.tourisImages[0].tourisImages}" alt="" >
					    </div>
				 	<c:forEach var="image" items="${touris.tourisImages}">
					    <div class="carousel-item">
					      <img class="d-block w-100 h-100" src="${image.tourisImages}" alt="Next slide" >
					    </div>
					</c:forEach>
				  </c:if>
				  <c:if test="${empty touris.tourismainImge && empty touris.tourisImages[0].tourisImages }">
  					    <div class="carousel-item active">
					      <img class="d-block w-100 h-100" src="${path }/images/common/logo.png" alt="" >
					    </div>
				  </c:if>
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
	          		<div class="">
			            <table class="w-100 h-100 m-table">
			              <tr>
			                <th class="fs-4">홈페이지</th>
			                <td>
		                 		<div class="ft-blue2"><a href="${touris.tourispage}" target="_blank">${touris.tourispage}</a></div>
			                </td>
			              </tr>
			              <tr>
			                <th class="fs-4">주소</th>
			                <td>
			                	<div class="">${touris.tourisAddress}</div>
			                </td>
			              </tr>
			              <tr>
			                <th class="fs-4" style="margin-right:10px;">문의 및 안내</th>
			                <td style="margin-left:10px;">
			                <c:if test="${empty touris.tourisPhone }">
			                	<div>${touris.tourisTel}</div>
			                </c:if>
			                <c:if test="${not empty touris.tourisPhone }">
			                	<div>${touris.tourisPhone}</div>
			                </c:if>
			                </td>
			              </tr>
           	              <tr>
			                <th class="fs-4">쉬는날</th>
			                <td>
			                  <div class="">${touris.tourisDayoff}</div>
			                </td>
			              </tr>
           	              <tr>
			                <th class="fs-4">이용시간</th>
			                <td>
			                  <div class="">${touris.tourisUsetime}</div>
			                </td>
			              </tr>
           	              <tr>
			                <th class="fs-4">주차시설</th>
			                <td>
			                  <div class="">${touris.tourisParking}</div>
			                </td>
			              </tr>
			            </table>
			          </div>		
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
	<div class="d-flex justify-content-center">
			<button class="yoonBtn btnColorRed" onclick="history.back(-1);" style="justify-content:center; width:500px;">목록으로</button>
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
			Kakao.init('c0e169307572ef60ba8671f2af4eaff4');
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
