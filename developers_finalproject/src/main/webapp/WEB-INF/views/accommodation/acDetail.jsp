<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
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
	<div id="blurSearchBar"></div>
	<div class="overImgCon">
		<div class="overImg main">
			<button>
				<ion-icon name="close"></ion-icon>
			</button>
			<img src="" alt="" />
		</div>
		<div class="overImg review">
			<button>
				<ion-icon name="close"></ion-icon>
			</button>
			<img src="" alt="" />
		</div>
	</div>

	<div class="hotelDetailCon">
		<div id="hotelDetail">
			<div class="hotelDetailTitle">
				<h2>${ad.acTitle }</h2>
				<div class="hotelDetailSubTitle">
					<div class="detailAdress">
						<input type="hidden" id="kakaoAddr" value="${ad.acAddress}">
						<span>${ad.acAddress } </span>
					</div>
					<div class="detailShare">
						<button id="detailSharing">
							<ion-icon name="share-outline"></ion-icon>
							공유하기
						</button>
						<button id="detailHeart">
							<ion-icon id="detailHeartOff" name="heart-outline"></ion-icon>
							<ion-icon id="detailHeartOn" name="heart"></ion-icon>
							찜하기
						</button>
					</div>
				</div>
			</div>
			<div id="shareCon">
				<div id="shareBox">
					<div id="share">
						<div id="shareHead">
							<span>숙박지 공유하기</span>
							<button>
								<ion-icon name="close-outline"></ion-icon>
							</button>
						</div>
						<div id="shareContent">
							<c:forEach var="af" items="${ad.acFiles }">
								<c:if test="${fn:contains(af.afMain,'Y')}">
									<img src="${path}/images/upload/accommodation/${af.afName}"
										alt="" />
								</c:if>
							</c:forEach>
							<div>
								<div>
									<span>${ad.acTitle }</span>
								</div>
								<p>
									<span>최대인원 ${ad.acPeople }명</span> <span>방 ${ad.acRoom }개</span>
									<span>침대 ${ad.acBed }개</span> <span>욕실 ${ad.acBathRoom }개</span>
								</p>
							</div>
						</div>
						<div id="shareIconCon">
							<div class="shareIcon" onclick="kakaoShare()">
								<div>
									<img src="${path }/images/accommodation/kakaoIcon.png" alt="">
									<span>카카오톡 공유하기</span>
								</div>
							</div>
							<div class="shareIcon" onclick="naverShare()">
								<div>
									<img src="${path }/images/accommodation/naverIcon.jpeg" alt="">
									<span>네이버 공유하기</span>
								</div>
							</div>
							<div class="shareIcon" onclick="facebookShare()">
								<div>
									<img src="${path }/images/accommodation/facebookIcon.png"
										alt=""> <span>페이스북 공유하기</span>
								</div>
							</div>
							<div class="shareIcon" onclick="twitterShare()">
								<div>
									<img src="${path }/images/accommodation/twitterIcon.png" alt="">
									<span>트위터 공유하기</span>
								</div>
							</div>
							<div class="shareIcon" onclick="linkShare()">
								<div>
									<img src="${path }/images/accommodation/linkIcon.png" alt="">
									<span>링크 복사하기</span>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
			<div class="hotelDetailImg">
				<div class="mainImageCon">
					<div class="mainImage">
						<c:forEach var="af" items="${ad.acFiles }">
							<c:if test="${fn:contains(af.afMain,'Y')}">
								<img src="${path}/images/upload/accommodation/${af.afName}"
									alt="" />
							</c:if>
						</c:forEach>
					</div>

				</div>
				<div class="subImageCon">
					<div class="subImageLine firstline">
						<c:forEach var="af" items="${ad.acFiles }" varStatus="i">
							<c:if
								test="${fn:contains(af.afMain,'N') and i.index<3 and i.index>0}">
								<div class="subImage">
									<img src="${path}/images/upload/accommodation/${af.afName}"
										alt="" />
								</div>
							</c:if>
						</c:forEach>
					</div>

					<div class="subImageLine lastline">
						<c:forEach var="af" items="${ad.acFiles }" varStatus="i">
							<c:if
								test="${fn:contains(af.afMain,'N') and i.index>2 and i.index<5}">
								<div class="subImage">
									<img src="${path}/images/upload/accommodation/${af.afName}"
										alt="" />
								</div>
							</c:if>
						</c:forEach>
					</div>
					<div id="hotelImageBtn">
						<button>사진 더보기</button>
					</div>
				</div>
			</div>
			<div class="detailHotelImage">
				<div>
					<c:forEach var="af" items="${ad.acFiles }">
						<img alt="" src="${path}/images/upload/accommodation/${af.afName}">
					</c:forEach>
				</div>
			</div>

			<div class="hotelDetailBody">
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
								<img src="${path}/images/accommodation/peopleDetail.png" alt="" />
								<p>최대 ${ad.acPeople}명</p>
							</div>
						</div>
					</div>
					<div class="detailHotelInfoCon">
						<div class="detailHotelTitle">
							<h2>상세설명</h2>
							<hr />
						</div>
						<div class="detailHotelInfo">
							<pre>
                   				${ad.acContent }
                 			</pre>
							<div class="detailHotelInfoBtn">
								<button>더보기</button>
							</div>
						</div>
					</div>
					<div class="detailHotelFcCon">
						<div class="detailHotelTitle">
							<h2>편의시설</h2>
							<hr />
						</div>
						<c:if test="${empty ad.afa }">
						편의시설이 없습니다.
						</c:if>
						<c:if test="${not empty ad.afa }">
							<div class="detailHotelFc">
								<c:if test="${ad.afa.afaCamera==1 }">
									<div>
										<div class="blurFc"></div>
										<span>보안 카메라</span> <img
											src="data:image/jpeg;base64,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"
											alt="" />
									</div>
								</c:if>
								<c:if test="${ad.afa.afaAircon==1 }">
									<div>
										<div class="blurFc"></div>
										<span>에어컨</span> <img
											src="data:image/jpeg;base64,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"
											alt="" />
									</div>
								</c:if>
								<c:if test="${ad.afa.afaKitchen==1 }">
									<div>
										<div class="blurFc"></div>
										<span>주방</span> <img
											src="data:image/jpeg;base64,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"
											alt="" />
									</div>
								</c:if>
								<c:if test="${ad.afa.afaWifi==1 }">
									<div>
										<div class="blurFc"></div>
										<span>무선 인터넷</span> <img
											src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQt46MJ60DH3NWNy7YwarKPpPrZw_LPfQXklw&usqp=CAU"
											alt="" />
									</div>
								</c:if>
								<c:if test="${ad.afa.afaWasher==1 }">
									<div>
										<div class="blurFc"></div>
										<span>세탁기</span> <img
											src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQlqlUjnBZd8uPFfd3qRp-b_XrDRxgS1a0zcdYBWjDTDCY0JYeHDpB0kTJxdX6IlcvmGps&usqp=CAU"
											alt="" />
									</div>
								</c:if>
								<c:if test="${ad.afa.afaParking==1 }">
									<div>
										<div class="blurFc"></div>
										<span>무료 주차</span> <img
											src="data:image/jpeg;base64,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"
											alt="" />
									</div>
								</c:if>
								<c:forEach var="afal" items="${afal }">
									<c:if test="${not empty afal }">
										<div>
											<div class="blurFc"></div>
											<span>${afal.afalName }</span> <img
												src="${path}/images/upload/accommodation/afal/${afal.afalImg}"
												alt="" />
										</div>
									</c:if>
								</c:forEach>
							</div>
						</c:if>
					</div>
					<div class="detailHotelDayCon">
						<div class="detailHotelTitle">
							<h2>숙박기간</h2>
							<hr />
						</div>
						<c:forEach var="arv" items="${ad.arv }">
							<input type="hidden" class="checkIn" val="${arv.checkIn }">
							<input type="hidden" class="checkOut" val="${arv.checkOut }">
						</c:forEach>
						<div class="searchCalander">
							<div class="calHeader">
								<div class="calBtnWrap">
									<a href="javascript:;" class="calBtn prev"><ion-icon
											name="chevron-back-outline"></ion-icon></a>
								</div>
								<div class="calMonth"></div>
								<div class="calNextMonth"></div>
								<div class="calBtnWrap">
									<a href="javascript:;" class="calBtn next"><ion-icon
											name="chevron-forward-outline"></ion-icon></a>
								</div>
							</div>
							<div class="calWrapper">
								<div class="calContainer">
									<div class="calDays">
										<div class="weekDay">SUN</div>
										<div class="weekDay">MON</div>
										<div class="weekDay">TUE</div>
										<div class="weekDay">WED</div>
										<div class="weekDay">THU</div>
										<div class="weekDay">FRI</div>
										<div class="weekDay">SAT</div>
									</div>
									<input type="hidden" id="reInDay" value="" /> <input
										type="hidden" id="fnum" value="" /> <input type="hidden"
										id="selectFnum" value="">
									<div class="hotelDates now"></div>
								</div>
								<div class="calContainer">
									<div class="calDays">
										<div class="weekDay">SUN</div>
										<div class="weekDay">MON</div>
										<div class="weekDay">TUE</div>
										<div class="weekDay">WED</div>
										<div class="weekDay">THU</div>
										<div class="weekDay">FRI</div>
										<div class="weekDay">SAT</div>
									</div>
									<input type="hidden" id="lnum" value="" /> <input
										type="hidden" id="selectLnum" value="">
									<div class="hotelDates next"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="detailHotelReviewCon">
						<div class="detailHotelTitle">
							<h2>후기</h2>
							<hr />
						</div>
						<c:if test="${empty ar}">
							후기가 없습니다.
						</c:if>
						<c:if test="${not empty ar }">
							<c:forEach var="r" items="${ar}">
								<div class="detailHotelReview">
									<div class="detailUserInfo">
										<img src="${r.member.memberImage}" alt="" />
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
										<c:if test="${not empty r.arFiles}">
											<c:forEach var="rf" items="${r.arFiles }">

												<img
													src="${path }/images/upload/accommodation/review/${rf.arfName}"
													alt="" />

											</c:forEach>
										</c:if>
									</div>
									<div class="detailReviewText">
										<pre>
									${r.arContent }
                   			 	</pre>
									</div>
								</div>
							</c:forEach>
						</c:if>
					</div>
				</div>
				<div class="detailHotelModalCon">
					<div class="detailHotelModal">
						<div class="detailHotelPrice">
							<div>
								<span><span> ₩</span> <span id="exPrice1"><fmt:formatNumber
											value="${ad.acPrice }" pattern="#,###" /></span> <span> /박</span></span>
							</div>
						</div>
						<form id="detailForm" action="${path}/ac/acPay" method="post">
							<div class="detailHotelFunc">
								<input type="hidden" name="no" value="${ad.acId }"> <input
									type="hidden" name="resultPrice" value="${ad.acId }">
								<div>
									<div class="detailHotelCheckDay">
										<div>
											<span>체크인</span> <input type="text" name="checkIn"
												id="detailHotelCheckIn" placeholder="날짜추가" readonly />
											<ion-icon id="inDayBtn" name="close-circle-outline"></ion-icon>
										</div>
										<div>
											<span>체크아웃</span> <input type="text" name="checkOut"
												id="detailHotelCheckOut" placeholder="날짜추가" readonly />
											<ion-icon id="outDayBtn" name="close-circle-outline"></ion-icon>
										</div>
									</div>
									<div class="detailHotelPeople">
										<div>
											<span>인원</span> <input type="text" readonly id="payPeople"
												name="people" value="인원추가"> <input type="hidden"
												id="maxPeople" value="${ad.acPeople }">
											<ion-icon name="chevron-down-outline"></ion-icon>
											<ion-icon name="chevron-up-outline"></ion-icon>
										</div>
									</div>
									<div class="searchPeople detail">
										<div id="searchCheckCon">
											<div id="searchCheck">
												<div id="peopleText">인원수</div>
												<div id="peopleBtn">
													<button type="button" class="countBtn"
														onclick="countFn2('minus')">
														<ion-icon class="people minus"
															name="remove-circle-outline"></ion-icon>
													</button>
													<div id="peopleCountCon">
														<input type="number" id="peopleCount" value="0" min="1"
															max="10" />
													</div>
													<button type="button" class="countBtn"
														onclick="countFn2('plus')">
														<ion-icon name="add-circle-outline"></ion-icon>
													</button>
												</div>
											</div>

											<div id="countInfo"></div>
										</div>
									</div>
								</div>

							</div>

							<div class="detailHotelPriceCalc">
								<div>
									<div class="detailHotelEx">
										<span> <span>₩</span> <span id="exPrice2"> <fmt:formatNumber
													value="${ad.acPrice }" pattern="#,###" />
										</span>
										</span> <span class="multiply">X</span> <span id="exDay">1</span><span
											style="font-size: 0.8rem">박</span>
									</div>
									<span style="color: gray">₩ <span id="resultPrice"><fmt:formatNumber
												value="${ad.acPrice }" pattern="#,###" /></span>
									</span>
								</div>
							</div>
							<div class="hrCon">
								<hr />
							</div>
							<div class="detailHotelPriceResult">
								<div>
									<span>총 합계</span> <span style="color: #b31312">₩ <span
										id="realResultPrice"><fmt:formatNumber
												value="${ad.acPrice }" pattern="#,###" /></span>
									</span>
								</div>
							</div>
							<div class="detailHotelBtn">
								<button type="button">예약하기</button>
							</div>
							<div class="detailHotelBtn on">
								<button>예약하기</button>
							</div>
						</form>
					</div>
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
				<p>${ad.acAddress }</p>
				<div id="mapContainer"></div>
			</div>
		</div>
	</div>
	<script>
	
	 <c:if test="${not empty loginMember }">

	</c:if> 
	
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
	
		function kakaoShare() {
			Kakao.Share.sendCustom({	
					    templateId: 97428, // 메시지템플릿 번호
					    templateArgs: {	
					      IMG1 : "${path}/images/upload/accommodation/"+img1,
					      IMG2 :  "${path}/images/upload/accommodation/"+img2,
					      IMG3 :  "${path}/images/upload/accommodation/"+img3,
					      TITLE: '${ad.acTitle}', // 제목 텍스트
					      THU : "${path}/images/common/logo.png",
					      PATH : "${path}/ac/acDetail?no=${ad.acId}",
					      DESC : ""		  
					    },
					});
		}

		var addr = $("#kakaoAddr").val()
		var acType = $("#acType").val()

		var geocoder = new kakao.maps.services.Geocoder();
		geocoder
				.addressSearch(
						addr,
						function(result, status) {
							if (status === kakao.maps.services.Status.OK) {
								var coords = new kakao.maps.LatLng(result[0].y,
										result[0].x);
								if (acType == "펜션") {
									var imageSrc = "${path}/images/accommodation/pensionMarker.png"
								} else if (acType == "호텔") {
									var imageSrc = "${path}/images/accommodation/hotelMarker.png"
								} else {
									var imageSrc = "${path}/images/accommodation/motelMarker.png"
								}
								var imageSize = new kakao.maps.Size(60, 60)
								var imageOption = {
									offset : new kakao.maps.Point(27, 69)
								};

								// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
								var markerImage = new kakao.maps.MarkerImage(
										imageSrc, imageSize, imageOption)

								var marker = new kakao.maps.Marker({
									position : coords, // 마커의 위치 */
									image : markerImage
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
		
		var checkInOutDay = []
		var checkHolyDay=[]
		<c:forEach var="arv" items="${ad.arv}">
		<c:if test="${arv.apId!=0}">
		checkInOutDay.push({
			checkIn : "${arv.checkIn}",
			checkOut : "${arv.checkOut}"
		})
		</c:if>
		<c:if test="${arv.apId==0}">
		checkHolyDay.push({
			checkIn : "${arv.checkIn}",
			checkOut : "${arv.checkOut}"
		})
		</c:if>
		</c:forEach>
		
			
			
			
		$(".detailHotelBtn.on").on("click",function(e){			
			if(${empty loginMember}){
				e.preventDefault()
				$("#modal").css("display","flex")
			}else{
				
			}
		})
		
		var acId=${ad.acId}
		var memberId=""
		$("#detailHeart").on("click",function(e){			
		if(${empty loginMember}){
			e.preventDefault()
			$("#modal").css("display","flex")
		}else{
			memberId="${loginMember.memberId}"
		}
		})
		
		<c:forEach var="ah" items="${ah}">
			<c:if test="${ah.memberId==loginMember.memberId }">
				$("#detailHeartOn").show()
				$("#detailHeartOff").hide()
			</c:if>
			<c:if test="${ah.memberId!=loginMember.memberId }">
				$("#detailHeartOn").hide()
				$("#detailHeartOff").show()
			</c:if>
		</c:forEach>
			
	</script>
	<script src="${path }/js/accommodation/acSearchBar.js"></script>
	<script src="${path }/js/accommodation/acDetail.js"></script>

</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
