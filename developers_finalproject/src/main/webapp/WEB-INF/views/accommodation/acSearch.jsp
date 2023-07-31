<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<jsp:include page="/WEB-INF/views/accommodation/acSearchBar.jsp" />
<link rel="stylesheet" href="${path }/css/accommodation/acList.css" />
<link rel="stylesheet" href="${path }/css/accommodation/acSearch.css" />
<script
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=737cdc9322ba7fec26566e0863b53498&libraries=services,clusterer"></script>

<div id="hotelListContainer">
	<div id="filterMini">
		<button>
			<ion-icon name="options"></ion-icon>
			<span>필터</span>
		</button>
	</div>
	<div id="filterDetail">
		<div id="filterCon">
			<div id="filter">
				<div id="filterHeader">필터</div>
				<div class="middle">
					<div class="multi-range-slider">
						<!-- 진짜 슬라이더 -->
						<input type="range" id="input-left" min="0" max="1000000"
							value="300000" /> <input type="range" id="input-right" min="0"
							max="1000000" value="600000" />
						<!-- 커스텀 슬라이더 -->
						<div class="slider">
							<div class="track"></div>
							<div class="range"></div>
							<div class="thumb left"></div>
							<div class="thumb right"></div>
						</div>
					</div>
					<input type="text" id="minVal" /> <input type="text" id="maxVal" />
				</div>
				<div class="hotelType">
					<div class="ht pension">
						<p>펜션</p>
						<img src="./image/pension.png" alt="" />
					</div>
					<div class="ht hotel">
						<p>호텔</p>
						<img src="./image/hotel.png" alt="" />
					</div>
					<div class="ht motel">
						<p>모텔</p>
						<img src="./image/motel.png" alt="" />
					</div>
				</div>
				<div class="hotelCount countFirst">
					<div class="hotelCountCon room">
						<div class="hotelCountHeader">방개수</div>
						<button class="hotelCountBtn room" id="minus">
							<ion-icon name="remove-circle-outline"></ion-icon>
						</button>
						<div class="hotelCountInput room">
							<input type="number" name="roomCount" id="roomCount" value="1"
								min="1" max="5" />
						</div>
						<button class="hotelCountBtn room" id="plus">
							<ion-icon name="add-circle-outline"></ion-icon>
						</button>
					</div>
					<div class="hotelCountInfo room"></div>
				</div>
				<div class="hotelCount">
					<div class="hotelCountCon bed">
						<div class="hotelCountHeader">침대개수</div>
						<button class="hotelCountBtn bed" id="minus">
							<ion-icon name="remove-circle-outline"></ion-icon>
						</button>
						<div class="hotelCountInput bed">
							<input type="number" name="bedCount" id="bedCount" value="1"
								min="1" max="5" />
						</div>
						<button class="hotelCountBtn bed" id="plus">
							<ion-icon name="add-circle-outline"></ion-icon>
						</button>
					</div>
					<div class="hotelCountInfo bed"></div>
				</div>
				<div class="hotelCount">
					<div class="hotelCountCon bathroom">
						<div class="hotelCountHeader">욕실개수</div>
						<button class="hotelCountBtn bathroom" id="minus">
							<ion-icon name="remove-circle-outline"></ion-icon>
						</button>
						<div class="hotelCountInput bathroom">
							<input type="number" name="bathroomCount" id="bathroomCount"
								value="1" min="1" max="5" />
						</div>
						<button class="hotelCountBtn bathroom" id="plus">
							<ion-icon name="add-circle-outline"></ion-icon>
						</button>
					</div>
					<div class="hotelCountInfo bathroom"></div>
				</div>
			</div>
		</div>
	</div>
	<div id="hotelList">
		<c:choose>
			<c:when test="${not empty as}">
				<c:forEach var="al" items="${as}" varStatus="status">
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
											<span style="color:#b31312">리뷰 없음</span>
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
	<div id="mapContainer">
	<div id="acSearchMapWrapper">
		<div id="acSearchMap" style="width:100%;height:100%"></div>
		<div id="roadviewControl" onclick="setRoadviewRoad()"></div>
	</div>
	  <div id="rvWrapper">
        <div id="roadview"  style="width:100%;height:100%;"></div> <!-- 로드뷰를 표시할 div 입니다 -->
         <div id="close" title="로드뷰닫기" onclick="closeRoadview()"><span class="img"></span></div>
    </div>
    
    <input type="button" id="btnRoadview" onclick="toggleMap(false)" title="로드뷰 보기" value="로드뷰">
    <input type="button" id="btnMap" onclick="toggleMap(true)" title="지도 보기" value="지도">
		<!-- 지도타입 컨트롤 div 입니다 -->
		<div class="custom_typecontrol radius_border">
			<span id="btnRoadmap" class="selected_btn"
				onclick="setMapType('roadmap')">지도</span> <span id="btnSkyview"
				class="nbtn" onclick="setMapType('skyview')">스카이뷰</span>
		</div>
		<!-- 지도 확대, 축소 컨트롤 div 입니다 -->
		<div class="custom_zoomcontrol radius_border">
			<span onclick="zoomIn()"><img
				src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/ico_plus.png"
				alt="확대"></span> <span onclick="zoomOut()"><img
				src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/ico_minus.png"
				alt="축소"></span>
		</div>
	</div>
</div>
<script>
		var asList=[]
		var asFile=[]
		<c:forEach var="al" items="${as}" varStatus="i">
		asList.push({
			title : "${al.acTitle}",
			price : "${al.acPrice}",
			address : "${al.acAddress}",	
			type : "${al.acType}",
			review :"${al.reviewGrade}",
			people :"${al.acPeople}",
			room : "${al.acRoom}",
			bed : "${al.acBed}",
			bath : "${al.acBathRoom}",
			mainImg:[
				<c:forEach var="af" items="${al.acFiles}" varStatus="i">
					<c:if test="${fn:contains(af.afMain, 'Y')}">
						"${af.afName}"
					</c:if>					
				</c:forEach>],
			img:[
				<c:forEach var="af" items="${al.acFiles}" varStatus="i">
					<c:if test="${fn:contains(af.afMain, 'N')}">
						"${af.afName}",
					</c:if>					
				</c:forEach>]
		});		
		</c:forEach>
		
		
		var overlayOn = false,
		container = document.getElementById('mapContainer'), // 지도와 로드뷰를 감싸고 있는 div 입니다
		mapWrapper = document.getElementById('acSearchMapWrapper'), // 지도를 감싸고 있는 div 입니다
		mapContainer = document.getElementById('acSearchMap'), // 지도를 표시할 div 입니다
		rvContainer = document.getElementById('roadview'), // 로드뷰를 표시할 div 입니다
	    
	    mapOption = {
	        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    };  
		
	
		// 지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption); 
		
		// 로드뷰 객체를 생성합니다 
		var rv = new kakao.maps.Roadview(rvContainer); 
		
		// 좌표로부터 로드뷰 파노라마 ID를 가져올 로드뷰 클라이언트 객체를 생성합니다 
		var rvClient = new kakao.maps.RoadviewClient(); 
		
		  kakao.maps.event.addListener(rv, 'position_changed', function() {

      	    // 현재 로드뷰의 위치 좌표를 얻어옵니다 
      	    var rvPosition = rv.getPosition();

      	    // 지도의 중심을 현재 로드뷰의 위치로 설정합니다
      	    map.setCenter(rvPosition);

      	    // 지도 위에 로드뷰 도로 오버레이가 추가된 상태이면
      	    if(overlayOn) {
      	        // 마커의 위치를 현재 로드뷰의 위치로 설정합니다
      	        marker2.setPosition(rvPosition);
      	    }
      	});
         
		// 마커 이미지를 생성합니다
		  var markImage2 = new kakao.maps.MarkerImage(
		      'https://t1.daumcdn.net/localimg/localimages/07/2018/pc/roadview_minimap_wk_2018.png',
		      new kakao.maps.Size(26, 46),
		      {
		          // 스프라이트 이미지를 사용합니다.
		          // 스프라이트 이미지 전체의 크기를 지정하고
		          spriteSize: new kakao.maps.Size(1666, 168),
		          // 사용하고 싶은 영역의 좌상단 좌표를 입력합니다.
		          // background-position으로 지정하는 값이며 부호는 반대입니다.
		          spriteOrigin: new kakao.maps.Point(705, 114),
		          offset: new kakao.maps.Point(13, 46)
		      }
		  );
		  
		// 드래그가 가능한 마커를 생성합니다
		  var marker2 = new kakao.maps.Marker({
			    image : markImage2,
			    position: new kakao.maps.LatLng(33.450701, 126.570667),
			    draggable: true
			});
		
		// 마커에 dragend 이벤트를 등록합니다
		  kakao.maps.event.addListener(marker2, 'dragend', function(mouseEvent) {

		      // 현재 마커가 놓인 자리의 좌표입니다 
		      var position = marker2.getPosition();

		      // 마커가 놓인 위치를 기준으로 로드뷰를 설정합니다
		      toggleRoadview(position);
		  });
		
		  kakao.maps.event.addListener(map, 'click', function(mouseEvent){
			    
			    // 지도 위에 로드뷰 도로 오버레이가 추가된 상태가 아니면 클릭이벤트를 무시합니다 
			    if(!overlayOn) {
			        return;
			    }

			    // 클릭한 위치의 좌표입니다 
			    var position = mouseEvent.latLng;

			    // 마커를 클릭한 위치로 옮깁니다
			    marker2.setPosition(position);

			    // 클락한 위치를 기준으로 로드뷰를 설정합니다
			    toggleRoadview(position);
			});
		
		// 전달받은 좌표(position)에 가까운 로드뷰의 파노라마 ID를 추출하여
		// 로드뷰를 설정하는 함수입니다
		function toggleRoadview(position){
		    rvClient.getNearestPanoId(position, 50, function(panoId) {
		        // 파노라마 ID가 null 이면 로드뷰를 숨깁니다
		        if (panoId === null) {
		            toggleMapWrapper(true, position);
		        } else {
		         toggleMapWrapper(false, position);

		            // panoId로 로드뷰를 설정합니다
		            rv.setPanoId(panoId, position);
		        }
		    });
		}  
		
		// 지도를 감싸고 있는 div의 크기를 조정하는 함수입니다
		function toggleMapWrapper(active, position) {
		    if (active) {

		        // 지도를 감싸고 있는 div의 너비가 100%가 되도록 class를 변경합니다 
		        container.className = '';

		        // 지도의 크기가 변경되었기 때문에 relayout 함수를 호출합니다
		        map.relayout();

		        // 지도의 너비가 변경될 때 지도중심을 입력받은 위치(position)로 설정합니다
		        map.setCenter(position);
		    } else {

		        // 지도만 보여지고 있는 상태이면 지도의 너비가 50%가 되도록 class를 변경하여
		        // 로드뷰가 함께 표시되게 합니다
		        if (container.className.indexOf('view_roadview') === -1) {
		            container.className = 'view_roadview';

		            // 지도의 크기가 변경되었기 때문에 relayout 함수를 호출합니다
		            map.relayout();

		            // 지도의 너비가 변경될 때 지도중심을 입력받은 위치(position)로 설정합니다
		            map.setCenter(position);
		        }
		        map.relayout();

	            // 지도의 너비가 변경될 때 지도중심을 입력받은 위치(position)로 설정합니다
	            map.setCenter(position);
		    }
		}
		
		// 지도 위의 로드뷰 도로 오버레이를 추가,제거하는 함수입니다
		function toggleOverlay(active) {
		    if (active) {
		        overlayOn = true;

		        // 지도 위에 로드뷰 도로 오버레이를 추가합니다
		        map.addOverlayMapTypeId(kakao.maps.MapTypeId.ROADVIEW);

		        // 지도 위에 마커를 표시합니다
		        marker2.setMap(map);

		        // 마커의 위치를 지도 중심으로 설정합니다 
		        marker2.setPosition(map.getCenter());

		        // 로드뷰의 위치를 지도 중심으로 설정합니다
		        toggleRoadview(map.getCenter());
		    } else {
		        overlayOn = false;

		        // 지도 위의 로드뷰 도로 오버레이를 제거합니다
		        map.removeOverlayMapTypeId(kakao.maps.MapTypeId.ROADVIEW);

		        // 지도 위의 마커를 제거합니다
		        marker2.setMap(null);
		    }
		}

		// 지도 위의 로드뷰 버튼을 눌렀을 때 호출되는 함수입니다
		function setRoadviewRoad() {
		    var control = document.getElementById('roadviewControl');

		    // 버튼이 눌린 상태가 아니면
		    if (control.className.indexOf('active') === -1) {
		        control.className = 'active';
		        // 로드뷰 도로 오버레이가 보이게 합니다
		        toggleOverlay(true);
		    } else {
		        control.className = '';
		        // 로드뷰 도로 오버레이를 제거합니다
		        toggleOverlay(false);
		    }
		}

		// 로드뷰에서 X버튼을 눌렀을 때 로드뷰를 지도 뒤로 숨기는 함수입니다
		function closeRoadview() {
		    var position = marker2.getPosition();
		    toggleMapWrapper(true, position);
		}  
				
		function setMapType(maptype) { 
		    var roadmapControl = document.getElementById('btnRoadmap');
		    var skyviewControl = document.getElementById('btnSkyview'); 
		    if (maptype === 'roadmap') {
		        map.setMapTypeId(kakao.maps.MapTypeId.ROADMAP);    
		        roadmapControl.className = 'selected_btn';
		        skyviewControl.className = 'nbtn';
		    } else {
		        map.setMapTypeId(kakao.maps.MapTypeId.HYBRID);    
		        skyviewControl.className = 'selected_btn';
		        roadmapControl.className = 'nbtn';
		    }
		}

		// 지도 확대, 축소 컨트롤에서 확대 버튼을 누르면 호출되어 지도를 확대하는 함수입니다
		function zoomIn() {
		    map.setLevel(map.getLevel() - 1);
		}

		// 지도 확대, 축소 컨트롤에서 축소 버튼을 누르면 호출되어 지도를 확대하는 함수입니다
		function zoomOut() {
		    map.setLevel(map.getLevel() + 1);
		}
		
		var clusterer = new kakao.maps.MarkerClusterer({
			map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
			gridSize: 35,					
			averageCenter: false, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
			minClusterSize : 1,//클러스터링 할 최소 마커 수 (default: 2)
			minLevel: 7 // 클러스터 할 최소 지도 레벨 
		});	
		
		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();

		$.each(asList,function(i,l){						
			geocoder.addressSearch(l.address, function(result, status) {
				 if (status === kakao.maps.services.Status.OK) {
				        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);			
				        asList[i]['coords']=coords
				    console.log(l.title)			
				 }				
			});
		})
		
		//geocoder가 js코드보다 늦게 실행되기 때문에 지연시간을 줌
		
		var clickedOverlay = null;
		
		setTimeout(function(){
			$.each(asList,function(i,l){		
				
				  if(l.type=="펜션"){
			        	var imageSrc = '${path}/images/accommodation/pensionMarker.png'
			        }else if(l.type=="호텔"){
			        	var imageSrc = '${path}/images/accommodation/hotelMarker.png'		
			        }else {
			        	var imageSrc = '${path}/images/accommodation/motelMarker.png'
			        }
			    	var imageSize = new kakao.maps.Size(60, 60) 
			    	var imageOption = {offset: new kakao.maps.Point(27, 69)}; 
					
			    	// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
					var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
					markerPosition = new kakao.maps.LatLng(37.54699, 127.09598); // 마커가 표시될 위치입니다
			    
				
					
				var marker = new kakao.maps.Marker({
		        	//map: map, // 마커를 표시할 지도
		       	 	position: l.coords, // 마커의 위치 */
		       	 	image: markerImage 
		   		 });
				
				
				// 클러스터러에 마커들을 추가합니다
				clusterer.addMarker(marker);
				clusterer.setMap(map);
				
				
				map.setCenter(l.coords);								
			  var content=
		        	'<div class="mapDetail">'+		           	 	
		                '<div id="mapDetailImg'+i+'"class="carousel slide">'+
		                  '<div class="carousel-indicators">';
		         
		        		for(var j=0;j<l.img.length+1;j++){
		        			content+=
		                 	'<button type="button" data-bs-target="#mapDetailImg'+i+'"'+ 
		                      	'data-bs-slide-to="'+j+'"'+
		                      	'class="active"'+
		                      	'aria-current="true"'+
		                      	'aria-label="Slide '+(j+1)+'">'+
		                     '</button>'                      		
		        		}	                  
		                 content+=
		                 '</div>'+
		                 '<div class="carousel-inner">'+
		                    '<div class="carousel-item active">'+
		                      '<img src="${path}/images/upload/accommodation/'+l.mainImg+'"class="d-block w-100" alt="..."/>'+
		                  '</div>'
		            	$.each(l.img,function(j,f){			        
		                content+=  
		                  '<div class="carousel-item">'+
		                      '<img src="${path}/images/upload/accommodation/'+f+'"class="d-block w-100" alt="..."/>'+
		                    '</div>'               
		                 });
		                 content+=
		                 '</div>'+   
		                  '<button class="carousel-control-prev" type="button"'+ 
		                  'data-bs-target="#mapDetailImg'+i+'"'+ 
		                   'data-bs-slide="prev">'+
		                   '<span class="carousel-control-prev-icon" aria-hidden="true"></span>'+
		                   '<span class="visually-hidden">Previous</span>'+
		                  '</button>'+
		        		  '<button class="carousel-control-next" type="button"'+ 
		                  'data-bs-target="#mapDetailImg'+i+'"'+ 
		                   'data-bs-slide="next">'+
		                   '<span class="carousel-control-next-icon" aria-hidden="true"></span>'+
		                   '<span class="visually-hidden">Next</span>'+
		                  '</button>'+
		                '</div>'+
		                '<div>'+
		                  '<div class="mapDetailItem">'+
		                    '<div class="mapDetailTitle">'+
		                      '<span>'+l.title.substr(0,12)+"..."+'</span>'+
		                      '<div class="starContainer">'
		                      	if(l.review!="0.0"){
		                        content+='<span class="star">★★★★★<span style="width:'+l.review*20+'%">★★★★★</span></span>'
		                      	}else{
		                      	content+='<span class="notReview">리뷰없음</span>'
		                      	}
		                     content+=
		                      '</div>'+
		                    '</div>'+
		                    '<div class="mapDetailAddress">'+
		                      '<span>'+l.address+'</span>'+
		                    '</div>'+
		                    '<div class="mapDetailCount">'+
		                      '<span>최대 인원'+l.people+'명</span>'+
		                      '<span>방'+l.room+'개</span>'+
		                      '<span>침대'+l.bed+'개</span>'+
		                      '<span>욕실'+l.bath+'개</span>'+
		                    '</div>'+
		                    '<div class="mapDetailPrice">'+
		                      '<span>₩'+l.price.replace(/\B(?=(\d{3})+(?!\d))/g, ",")+'/박</span>'+            
		                    '</div>'+
		                  '</div>'+
		                '</div>'+
		              '</div>'
						
		              	var infowindow = new kakao.maps.InfoWindow({
		    				content : '<div class="infoWindow">₩'+l.price.replace(/\B(?=(\d{3})+(?!\d))/g, ",")+'</div>' // 인포윈도우에 표시할 내용
						});

						// 인포윈도우를 지도에 표시한다
						//infowindow.open(map, marker);

						
		                var overlay = new kakao.maps.CustomOverlay({
		                    content: content,
		                    position: marker.getPosition(),       
		                    clickable : true
		                });
						
		               kakao.maps.event.addListener(marker, 'click', function() {
		            	   if (clickedOverlay) {
		            	        clickedOverlay.setMap(null);
		            	    }
		            	    overlay.setMap(map);
		            	    clickedOverlay = overlay;
		               });
		                     
		               kakao.maps.event.addListener(map, 'click', function() {
		                    overlay.setMap(null);
		               });	             
			})
			

					
			
			$(".infoWindow").parent().prev().hide()
			$(".infoWindow").parent().css({"width":"100%","text-align":"center","top":"6px"})
			$(".infoWindow").parent().parent().css({
				"border-radius": "7px",
				"border":"none",
				"width": "150px",
		    	"height": "30px",
		    	"box-shadow" : "0 1px 2px rgba(0, 0, 0, 0.07), 0 2px 4px rgba(0, 0, 0, 0.07), 0 4px 8px rgba(0, 0, 0, 0.07), 0 8px 16px rgba(0, 0, 0, 0.07), 0 16px 32px rgba(0, 0, 0, 0.07), 0 32px 64px rgba(0, 0, 0, 0.07)",		    			    				  
			})				
		},100)
		
		
	
</script>
<script src="${path }/js/accommodation/acSearch.js"></script>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />