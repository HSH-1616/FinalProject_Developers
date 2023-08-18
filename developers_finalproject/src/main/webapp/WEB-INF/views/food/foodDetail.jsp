<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath}"/> 
<head>
   <script> const jspath = '${path}'; </script>
    <!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script> -->
    <!-- <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script> -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <%-- <script src="${path }/js/food/foodList.js"></script> --%>
    <script src="${path }/js/food/foodDetail.js"></script>
    <!-- <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script> -->
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <!-- <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script> -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.all.min.js"></script>
    <script src="https://use.fontawesome.com/releases/v6.4.0/js/all.js"></script>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4428a904f18357ee0a3f795c4918e96a&libraries=services,clusterer,drawing"></script>


    
   <!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"> -->
    <!-- <link rel="stylesheet" href="https://unpkg.com/aos@2.3.1/dist/aos.css"/> -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.min.css"/>
    <link rel="stylesheet" href="${path }/css/food/foodDetail.css"/>
    <link rel="stylesheet" href="${path }/css/default.css" />


   <c:set var="f" value="${foods[0]}"/>
</head>

<jsp:include page="/WEB-INF/views/common/header.jsp" />

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

<section>
   <!-- ==================================================맛집 상세페이지============================================================== -->
   <div class="foodDetailCon col justify-content-center">
      <!-- <div id="hotelDetail" class="row"> -->
      <div class="row align-items-end">
         <span class="col w-auto">
            <div class="d-flex flex-row">
               <h3 class="display-6">${f.foodName}</h3>
               &nbsp;
            </div>
            <h5 class="text-secondary">${f.foodAddress}</h5>
         </span>
			<button id="detailHeart">
				<c:if test="${loginMember==null}">
					<ion-icon id="detailHeartOff" name="heart-outline"></ion-icon>
					<ion-icon id="detailHeartOn" name="heart"></ion-icon>
				</c:if>
				<c:if test="${loginMember!=null}">
					<c:set var="loop_flag" value="false" />
					<c:forEach var="list" items="${loginMember.tourisHeartlist}">
						<c:if test='${fn:contains(list.tourisId,touris.tourisId)}'>
							<ion-icon id="detailHeartOff" name="heart-outline"
								style="display:none;"></ion-icon>
							<ion-icon id="detailHeartOn" name="heart" style="display:inline;"></ion-icon>
							<c:set var="loop_flag" value="true" />
						</c:if>
					</c:forEach>
					<c:if test="${not loop_flag}">
						<ion-icon id="detailHeartOff" name="heart-outline"
							style="display:inline;"></ion-icon>
						<ion-icon id="detailHeartOn" name="heart" style="display:none;"></ion-icon>
					</c:if>
				</c:if>
				찜하기
			</button>
		</div>

      <hr class="m-3">

      <!-- 맛집 상세정보 페이지  -->
      <div class="food_photo">
         <p class="fs-4">상세정보</p>
      </div>

      <div class="row justify-content-center" style="height: 450px;">
         <!-- 음식 슬라이드 -->
         <div class="foodInfosection d-flex flex-row justify-content-center align-items-center">
            <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel" style="width: 550px;">
               <div class="carousel-indicators">
                  <button type="button"
                     data-bs-target="#carouselExampleIndicators"
                     data-bs-slide-to="0" class="active" aria-current="true"
                     aria-label="Slide 1">
                  </button>
                  <c:forEach var="i" begin="1" end="${fn:length(f.foodPhoto)-1}" step="1">
                     <button type="button"
                     data-bs-target="#carouselExampleIndicators"
                     data-bs-slide-to="${i}" aria-current="true"
                     aria-label="Slide ${i+1}">
                     </button>
                  </c:forEach>
               </div>
               <div class="carousel-inner">               
                  <c:forEach var="fp" items="${f.foodPhoto}">
                     <c:if test="${fn:contains(fp.fpMain, 1)}">
                        <div class="carousel-item active">
                           <img src="${fp.fpName}"
                              class="d-block w-100" alt="참고 이미지" />
                        </div>
                     </c:if>
                     <c:if test="${fn:contains(fp.fpMain, 0)}">
                        <div class="carousel-item">
                           <img src="${fp.fpName}"
                              class="d-block w-100" alt="참고 이미지" />
                        </div>
                     </c:if>
                  </c:forEach>
               </div>
               <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                  <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                  <span class="visually-hidden">Previous</span>
               </button>
               <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                  <span class="carousel-control-next-icon" aria-hidden="true"></span>
                  <span class="visually-hidden">Next</span>
               </button>
            </div>
            

            <!-- 맛집 상세정보 텍스트 -->
            <div class="ms-5">
               <table class="text-start align-top food_info_table">
                  <tr class="">
                     <th valign="top"><i class="fa-solid fa-location-dot fa-2xl"
                        style="color: #000000;"></i></th>
                     <th class="fs-4" valign="top" style="width: 95px;">영업시간</th>
                     <td>
                        <% pageContext.setAttribute("LF", "\n"); %>
                        <div class="food_runtime">${fn:replace(f.foodOpenTime,LF,'<br>')}</div>
                     </td>
                  </tr>
                  <tr>
                     <th valign="top"><i class="fa-solid fa-utensils fa-2xl"
                        style="color: #000000;"></i></th>
                     <th class="fs-4" valign="top">메뉴</th>
                     <td>
                        <div class="food_main_mainmenu">${f.foodMenu }</div>
                     </td>
                  </tr>
                  <tr>
                     <th valign="top"><i class="fa-solid fa-phone fa-2xl"
                        style="color: #000000;"></i></th>
                     <th class="fs-4" valign="top">연락처</th>
                     <td>
                        <div class="food_main_phone">${f.foodPhone }</div>
                     </td>
                  </tr>
               </table>
            </div>
         </div>
      </div>
      <!-- ============================================================================================================= -->
      <!-- ====================================================지도===================================================== -->
      <!-- ============================================================================================================= -->
      <div id="map" class="row" style="width:auto;height:450px;margin: 5rem;"></div>
      
      <hr class="m-3">

      <!-- 맛집 리뷰 생성&수정 -->
      <div class="align-items-end" style="height: 75px;">
         <button type="button" class="insertReviewBtn yoonbtn btnColorDefault m-3 float-end w-auto"
            data-bs-toggle="modal" onclick="insertModal('${loginMember}');">리뷰쓰기</button>
      </div>

      <div class="modal fade" id="reviewModal" tabindex="-1"
         aria-labelledby="loginModalLabel" aria-hidden="true">
         <div class="modal-dialog modal-dialog-centered modal-lg">
            <div class="modal-content">
               <div class="headerLine"></div>
               <div class="modalForm">
               <!-- <form action="${path }/food/insertFoodReview.do" method="post" enctype="multipart/form-data" class="modalForm"> -->
               <input type="hidden" name="foodNo" value="${f.foodNo}">
               <input type="hidden" name="memberId" value="${loginMember.memberId}">
               <div class="modal-header p-4">
                  <div class="row">
                     <h3 class="modal-title p-0 w-auto" id="reviewModalLabel">${f.foodName }</h3>
                     <h5 class="modal-title p-0 pt-2 w-auto">&nbsp;에 대한 솔직한 후기를 적어주세요.</h5>
                  </div>
                  <div class="row mr-1 p-0 align-items-center">
                     <span class="star w-auto p-0">★★★★★
                        <span class="review_rating">★★★★★</span>
                           <input type="range" class="starcountnum" name="frGrade" oninput="drawStar(this)" value="0" step="1" min="0" max="10">
                           <div class="star_rating text-center text-dark">
                              <h5>0/5</h5>
                           </div>
                     </span>
                     <button type="button" class="btn-close" 
                     data-bs-dismiss="modal" aria-label="Close">
                     </button>
                  </div>
               </div>
                  <div class="modal-body ml-4 mr-4 mt-4 mb-4">
                     <textarea name="frContent" class="form-control" id="FR_CONTENT"
                        rows="10">작성내용은 매인페이지와 장소상세에 노출되며 매장주를 포함한 다른 사용자들이 볼 수 있으니, 서로를 배려하는 마음을 담아 작성해 주세요.
                     </textarea>
                  </div>
                  <div class="custom-file pl-5 pr-5 mx-3 mt-4 mb-4 d-flex flex-row justify-content-between">
                     <span class="input_file_container row"> <span
                        class="col-lg-8 ">
                           <input type="file" id="upFile" name="Files" class="form-control review_File insertFlag"
                           accept="image/*" onchange="setThumbnail(event);" multiple>
                     </span> 
                     <!-- 파일을 업로드 해야지만 전체삭제 옵션 활성화 --> 
                     <span class="pt-2 text-muted w-auto deleteAllImg" aria-hidden="true"></span>
                     </span>
                     <button type="submit" class="yoonBtn btnColorRed submitModal"></button>
                  </div>
                  <div class="image_container insertFood"></div>
               <!-- </form> -->
               </div>
            </div>
         </div>
      </div>
      <c:if test="${f.foodReview[0].frNo > 0}">
         <c:forEach var="fr" items="${f.foodReview}" varStatus="vs">
            <div class="detailFoodInfoCon flex-column">
               <div class="detailUserInfo justify-content-between">
                  <div class="float-start d-flex flex-row ms-0">
                     <img src="${fr.member.memberImage}" alt="프로필사진" />
                     <div>
                        <span>${fr.member.memberNickname}</span>
                        <span>${fr.frWriterDate}</span>
                     </div>
                  </div>
                  <!-- 작성자||관리자 만 메뉴보이게 -->
                  <c:if test="${loginMember.memberId==f.foodReview[0].memberId||loginAdmin!=null }">
                  <div class="dropdown mx-3 h5">
                     <a class="text-decoration-none text-dark" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
                        :  
                     </a>
                  
                     <ul class="dropdown-menu dropdown-menu-end text-center" aria-labelledby="dropdownMenuLink" style="width: 100px;">
                        <!-- 작성자만 보이게 -->
                        <c:if test="${loginMember.memberId==f.foodReview[0].memberId}">
                           <li><a class="dropdown-item" href="#" 
                           data-bs-toggle="modal" data-bs-target="#reviewModal" onclick="updateModal(event);">수정</a></li>
                        </c:if>
                        <li><a class="dropdown-item" href="#" data-bs-toggle="modal" 
                        data-bs-target="#removeFood" onclick="insertFrNo(${fr.frNo});">삭제</a></li>
                     </ul>
                  </div>
                  </c:if>
               </div>
      
               <!-- 삭제 모달창 -->
               <div class="modal fade" id="removeFood" tabindex="-1" aria-labelledby="removeFoodModalLabel" aria-hidden="true">
                  <div class="modal-dialog">
                     <div class="modal-content">
                        <!-- <form action="${path}/food/deleteFoodReview.do" style="margin:0px;" > -->
                           <div class="modal-header">
                              <h5 class="modal-title" id="removeFoodModalLabel">리뷰 삭제</h5>
                              <input type="hidden" id="selected_food_no" name="frNo" value="${fr.frNo}">
                              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                           </div>
                           <div class="modal-body">정말 삭제하시겠습니까?</div>
                           <div class="modal-footer">
                              <button type="button" class="yoonBtn btnColorDefault" data-bs-dismiss="modal">취소</button>
                              <button type="submit" class="yoonBtn btnColorRed" id="remove_food_btn">삭제하기</button>
                           </div>
                        <!-- </form> -->
                     </div>
                  </div>
               </div>
      
               <div class="starContainer">
                  <span class="star">
                     ★★★★★
                     <span class="star_number" style="width: ${fr.frGrade * 20}%">★★★★★</span> <!-- update data -->
                     <input type="hidden" class="starGrade" value="${fr.frGrade * 20}%">
                  </span>
               </div>
               <!-- 리뷰 이미지 -->
               <div class="detailReviewImg">
                  <c:if test="${fr.foodReviewPhoto[0].rpName == null}">
                  </c:if>
                  <c:if test="${fr.foodReviewPhoto[0].rpName != null}">
                     <c:forEach var="imgs" items="${fr.foodReviewPhoto}">
                     <img src="${path}/images/upload/food/${imgs.rpRename}" alt="리뷰이미지"/> <!-- update data -->
                     </c:forEach>
                  </c:if>
               </div>
               <div class="detailReviewText detailFoodInfo">
                  <pre style="overflow-y: hidden" class="default_food_review_height"> <!-- update data -->
                  ${fr.frContent}
                  </pre>
                  <div class="detailFoodInfoBtn d-flex justify-content-center">
                     <button id="food_review_more" class="visually-hidden"></button>
                     <label for="food_review_more"><i class="fa-solid fa-angles-down" style="color: #000000;"></i></label>
                  </div>
               </div>
            </div>
            <div style="height: 50px;"></div>
         </c:forEach>
      </c:if>
      <c:if test="${f.foodReview[0].frNo < 1}">
         <div>등록된 리뷰가 없습니다.</div>
      </c:if>
   </div>
   </section>

   <script>
      var foodName = '${f.foodName}';
      var foodAddr = '${f.foodAddress}';
      var mapx;
      var mapy;
      var foodImg = '${f.foodPhoto[0].fpName}';

      var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
      mapOption = {
         center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
         level: 3 // 지도의 확대 레벨
      };  

      // 지도를 생성합니다    
      var map = new kakao.maps.Map(mapContainer, mapOption); 

      // 주소-좌표 변환 객체를 생성합니다
      var geocoder = new kakao.maps.services.Geocoder();

      // 주소로 좌표를 검색합니다
      geocoder.addressSearch(foodAddr, function(result, status) {

         // 정상적으로 검색이 완료됐으면 
         if (status === kakao.maps.services.Status.OK) {

            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
            mapx = result[0].x;
            mapy = result[0].y;

            // 결과값으로 받은 위치를 마커로 표시합니다
            var marker = new kakao.maps.Marker({
                  map: map,
                  position: coords
            });

            var content = '<div class="wrap">' + 
            '                 <div class="info">' + 
            '                    <div class="title text-wrap">' + 
                                    foodName + 
            '                       <div class="close" onclick="closeOverlay()" title="닫기"></div>' + 
            '                    </div>' + 
            '                    <div class="body">' + 
            '                       <div class="img">' +
            '                          <img src='+foodImg+' width="73" height="70">' +
            '                       </div>' + 
            '                       <div class="desc">' + 
            '                          <div class="ellipsis">'+foodAddr+'</div>' +  
            '                          <div><a href="https://map.kakao.com/link/search/'+foodName+'" target="_blank" class="link">자세히 보기</a></div>' + 
            '                       </div>' + 
            '                    </div>' + 
            '                 </div>' +    
            '               </div>'
            '               ::after';

            // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            map.setCenter(coords);

            var overlay = new kakao.maps.CustomOverlay({
               content: content,
               map: map,
               position: marker.getPosition()       
            });

            // 마커를 클릭했을 때 커스텀 오버레이를 표시합니다
            kakao.maps.event.addListener(marker, 'click', function() {
               overlay.setMap(map);
            });

            function closeOverlay() {
               overlay.setMap(null);     
            }
         } 
      });
      //별점 확인
      $(".starcountnum").click(e=>{
         console.log($(".starcountnum").val()/2);
      })

      $(".submitModal").click(e=>{
         //ajax 통신(등록)
         if($(".submitModal").hasClass("insertFoodReview") === true){
            //js가 제공하는 FormData클래스를 이용함
            const form=new FormData();
            //append로 서버에 전송할 데이터를 넣을 수 있음
            //key-value형식으로 저장
            const fileInput=$("#upFile");
            //console.log(fileInput[0].files);
            $.each(fileInput[0].files,(i,f)=>{
               form.append("upFile",f);
               console.log(form);
            });
            form.append("foodNo","${f.foodNo}");
            //form.append("memberId","${loginMember.memberId}");
            form.append("frGrade",$(".starcountnum").val()/2);
            form.append("frContent",$("#FR_CONTENT").val());
            console.log($(".starcountnum").val()/2);
            if($(".starcountnum").val()/2 == 0){
               alert("별점, 후기를 작성해주세요");
            }else{
               $.ajax({
                  url:"${path}/food/insertFoodReview.do",
                  data:form,
                  type:"post",
                  enctype: "multipart/form-data",
                  processData:false,
                  contentType:false,
                  cache: false,
                  success:data=>{
                     alert("업로드가 완료되었습니다.");
                     location.reload();
                     $('window').scrollTop(0,300);
                  },
                  error:(r,s,e)=>{
                     console.log("업로드실패 "+r.s+"\n"+"msg "+r.responseText+"\n"+"error "+e);
                     alert("업로드 실패");
                  },
                  complete:()=>{
                     $(".upFile").val('');
                  }
               });
            }
         }
         //ajax 통신(수정)
         if($(".submitModal").hasClass("updateFoodReview") === true){
            const form=new FormData();
            const fileInput=$("#upFile");

            $.each(fileInput[0].files,(i,f)=>{
               form.append("upFile",f);
               console.log(form);
            });
            form.append("foodNo","${f.foodNo}");
            form.append("frGrade",$(".starcountnum").val()/2);
            form.append("frContent",$("#FR_CONTENT").val());
            form.append("frNo",$("#selected_food_no").val());
   
            $.ajax({
               url:"${path}/food/updateFoodReview.do",
               data:form,
               type:"post",
               enctype: "multipart/form-data",
               processData:false,
               contentType:false,
               cache: false,
               success:data=>{
                  alert("수정이 완료되었습니다.");
                  location.reload();
                  $('window').scrollTop(0,300);
               },
               error:(r,s,e)=>{
                  console.log("수정 실패 "+r.s+"\n"+"msg "+r.responseText+"\n"+"error "+e);
                  alert("수정 실패");
               },
               complete:()=>{
                  $(".upFile").val('');
               }
            });
         }
      });

      //ajax 통신(삭제)
      $("#remove_food_btn").click(e=>{
         const data = {frNo:$("#selected_food_no").val()};

         $.ajax({
            url:"${path}/food/deleteFoodReview.do",
            type:"post",
            data:data,
            success:data=>{
               alert("삭제가 완료되었습니다.");
               // location.reload();
               $('window').scrollTop(0,300);
            },
            error:(r,s,e)=>{
               console.log("삭제실패 "+r.s+"\n"+"msg "+r.responseText+"\n"+"error "+e);
               alert("삭제 실패");
            },
            complete:()=>{
               // $(".upFile").val('');
               location.reload();
            }
         });
      });
   </script>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />