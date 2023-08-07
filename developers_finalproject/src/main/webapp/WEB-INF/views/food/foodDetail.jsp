<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"/> 

<head>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet" />
    <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script
      type="module"
      src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"
    ></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script
      nomodule
      src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"
    ></script>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.min.css"
    />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.all.min.js"></script>

    <link rel="stylesheet" href="${path }/css/food/foodDetail.css"/>
    <link rel="stylesheet" href="${path }/css/default.css" />

    <script src="https://use.fontawesome.com/releases/v6.4.0/js/all.js"></script>

   <script src="${path }/js/food/foodList.js" />
   <c:set var="f" value="${foods[0]}"/>
</head>

<jsp:include page="/WEB-INF/views/common/header.jsp" />
<section>
   <!-- ==================================================맛집 상세페이지============================================================== -->
   <div class="foodDetailCon row">
      <!-- <div id="hotelDetail" class="row"> -->
      <div class="row align-items-end">
         <span class="col w-auto">
            <div class="d-flex flex-row">
               <h3 class="display-6">${f.foodName}</h3>
               &nbsp;
               <p class="align-self-end food_type">이탈리안</p>
            </div>
            <h5 class="">${f.foodAddress }</h5>
         </span>
         <button id="detailHeart" class="col-sm-1 mx-0 h-50">
            <ion-icon id="detailHeartOff" name="heart-outline"></ion-icon>
            <ion-icon id="detailHeartOn" name="heart"></ion-icon>
            찜하기
         </button>
      </div>

      <hr class="m-3">

      <!-- 맛집 상세정보 페이지 -->
      <div class="food_photo">
         <p>사진</p>
      </div>

      <div class="row">
         <!-- 음식 슬라이드 -->
	  	<c:set var="fp" value="${f.foodPhoto}"/>
<%-- 	  	<c:if test="${fp.foodNo != f.foodNo }"></c:if>
  		<c:if test="${fp.foodNo = f.foodNo }"> --%>
         <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel" style="width: 500px;">
            <div class="carousel-indicators">
               <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
               <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
               <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
            </div>
            <div class="carousel-inner">
               <c:forEach var="imgs" items="${fp}">
                  <div class="carousel-item active">
                     <img src="${imgs.fpName}" class="d-block w-100" alt="참고 이미지">
                  </div>
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
  		<%-- </c:if> --%>

         <!-- 맛집 상세정보 텍스트 -->
         <div class="col">
            <table class="text-start align-top food_info_table">
               <tr class="">
                  <th valign="top"><i class="fa-solid fa-location-dot fa-2xl"
                     style="color: #000000;"></i></th>
                  <th class="fs-4" valign="top">영업시간</th>
                  <td>
                     <div class="food_runtime">${f.foodOpenTime}</div>
                     <div class="food_runtime">휴게시간 15:00 ~ 18:00</div>
                  </td>
               </tr>
               <tr>
                  <th valign="top"><i class="fa-solid fa-utensils fa-2xl"
                     style="color: #000000;"></i></th>
                  <th class="fs-4" valign="top">메뉴</th>
                  <td>
                     <div class="food_main_mainmenu">${f.foodMenu }</div>
                     <div class="food_main_mainmenu">런치코스 36,000원</div>
                     <div class="food_main_mainmenu">디너코스 79,000원</div>
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

      <hr class="m-3">

      <!-- 맛집 리뷰 페이지 -->
      <div class="align-items-end">
         <button type="button"
            class="btn btn-outline-dark m-4 float-end w-auto"
            data-bs-toggle="modal" data-bs-target="#reviewModal">리뷰쓰기</button>
      </div>

      <div class="modal fade" id="reviewModal" tabindex="-1"
         aria-labelledby="loginModalLabel" aria-hidden="true">
         <div class="modal-dialog modal-dialog-centered modal-lg">
            <div class="modal-content">
               <div class="headerLine"></div>
               <div class="modal-header p-4">
                  <span class="row">
                     <h3 class="modal-title p-0 w-auto" id="reviewModalLabel">${f.foodName }</h3>
                     <h5 class="modal-title p-0 pt-2 w-auto">&nbsp;에 대한 솔직한 후기를
                        적어주세요.</h5>
                  </span> <span class="row mr-1 p-0 align-items-center"> <span
                     class="star w-auto p-0"> ★★★★★ <span
                        class="review_insert_rating">★★★★★</span> <input type="range"
                        oninput="drawStar(this)" value="1" step="1" min="0" max="10">
                        <div class="star_insert_rating text-center text-dark">
                           <h5>0/5</h5>
                        </div>
                  </span>
                     <button type="button" class="btn-close" data-bs-dismiss="modal"
                        aria-label="Close"></button>
                  </span>
               </div>
               <form action="${path }/loginEnd" method="post"
                  enctype="multipart/form-data">
                  <div class="modal-body ml-4 mr-4 mt-4 mb-4">
                     <textarea name="FR_CONTENT" class="form-control" id="FR_CONTENT"
                        rows="10">작성내용은 매인페이지와 장소상세에 노출되며 매장주를 포함한 다른 사용자들이 볼 수 있으니, 서로를 배려하는 마음을 담아 작성해 주세요.
                            </textarea>
                  </div>
                  <div
                     class="custom-file pl-5 pr-5 mx-3 mt-4 mb-4 d-flex flex-row justify-content-between">
                     <span class="input_file_container row"> <span
                        class="col-lg-8 "> <!-- <label class="form-label input-sm-1" for="review_File">파일선택(최대 5장)</label> -->
                           <input type="file" class="form-control" id="review_File"
                           accept="image/*" onchange="setThumbnail(event);" multiple>
                     </span> <!-- 파일을 업로드 해야지만 전체삭제 옵션 활성화 --> <span
                        class="pt-2 text-muted w-auto" aria-hidden="true"
                        style="cursor: pointer;" onclick="fileDeleteAll();">전체삭제&times;</span>
                     </span>
                     <button type="submit" class="btn btn-outline-danger ">리뷰
                        올리기</button>
                  </div>
                  <div id="image_container"></div>
               </form>
            </div>
         </div>
      </div>

      <div class="detailFoodInfoCon flex-column">
         <div class="detailUserInfo justify-content-between">
            <div class="float-start d-flex flex-row ms-0">
               <img src="./image/user.jpg" alt="" />
               <div>
                  <span>유저닉네임</span> <span>2023-07-23</span>
               </div>
            </div>
            <div class="dropdown mx-3 h5">
               <a class="text-decoration-none text-dark" href="#" role="button"
                  id="dropdownMenuLink" data-bs-toggle="dropdown"
                  aria-expanded="false"> : </a>

               <ul class="dropdown-menu dropdown-menu-end"
                  aria-labelledby="dropdownMenuLink">
                  <li><a class="dropdown-item" href="#" data-bs-toggle="modal"
                     data-bs-target="#reviewUpdateModal">수정</a></li>
                  <li><a class="dropdown-item" href="#" data-bs-toggle="modal"
                     data-bs-target="#removeFood" onclick="">삭제</a></li>
               </ul>
            </div>
         </div>

         <!-- 수정 모달창 -->
         <div class="modal fade" id="reviewUpdateModal" tabindex="-1"
            aria-labelledby="loginModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-lg">
               <div class="modal-content">
                  <div class="headerLine"></div>
                  <div class="modal-header p-4">
                     <span class="row">
                        <h3 class="modal-title p-0 w-auto" id="reviewModalLabel">${f.foodName }</h3>
                        <h5 class="modal-title p-0 pt-2 w-auto">&nbsp;에 대한 솔직한 후기를
                           적어주세요.</h5>
                     </span> <span class="row mr-1 p-0 align-items-center"> <span
                        class="star w-auto p-0"> ★★★★★ <span
                           class="review_update_rating">★★★★★</span> <input type="range"
                           oninput="drawStar(this)" value="1" step="1" min="0" max="10">
                           <div class="star_update_rating text-center text-dark">
                              <h5>0/5</h5>
                           </div>
                     </span>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                           aria-label="Close"></button>
                     </span>
                  </div>
                  <form action="${path }/loginEnd" method="post"
                     enctype="multipart/form-data">
                     <div class="modal-body ml-4 mr-4 mt-4 mb-4">
                        <textarea name="FR_CONTENT" class="form-control" id="FR_CONTENT"
                           rows="10">(수정중)작성내용은 매인페이지와 장소상세에 노출되며 매장주를 포함한 다른 사용자들이 볼 수 있으니, 서로를 배려하는 마음을 담아 작성해 주세요.
                                </textarea>
                     </div>
                     <div
                        class="custom-file pl-5 pr-5 mx-3 mt-4 mb-4 d-flex flex-row justify-content-between">
                        <span class="input_file_container row"> <span
                           class="col-lg-8 "> <!-- <label class="form-label input-sm-1" for="review_File">파일선택(최대 5장)</label> -->
                              <input type="file" class="form-control" id="review_File"
                              accept="image/*" onchange="setThumbnail(event);" multiple>
                        </span> <!-- 파일을 업로드 해야지만 전체삭제 옵션 활성화 --> <span
                           class="pt-2 text-muted w-auto" aria-hidden="true"
                           style="cursor: pointer;" onclick="fileDeleteAll();">전체삭제&times;</span>
                        </span>
                        <button type="submit" class="btn btn-outline-danger ">리뷰
                           올리기</button>
                     </div>
                     <div id="image_container"></div>
                  </form>
               </div>
            </div>
         </div>

         <!-- 삭제 모달창 -->
         <div class="modal fade" id="removeFood" tabindex="-1"
            aria-labelledby="removeFoodModalLabel" aria-hidden="true">
            <div class="modal-dialog">
               <div class="modal-content">
                  <div class="modal-header">
                     <h5 class="modal-title" id="removeFoodModalLabel">리뷰 삭제</h5>
                     <input type="hidden" id="selected_food_no" name="foodNo" value="">
                     <button type="button" class="btn-close" data-bs-dismiss="modal"
                        aria-label="Close"></button>
                  </div>
                  <div class="modal-body">정말 삭제하시겠습니까?</div>
                  <div class="modal-footer">
                     <button type="button" class="btn btn-secondary"
                        data-bs-dismiss="modal">취소</button>
                     <button type="button" class="btn btn-danger" id="remove_food_btn">삭제하기</button>
                  </div>
               </div>
            </div>
         </div>


         <div class="starContainer">
            <span class="star"> ★★★★★ <span style="width: 88%">★★★★★</span>
            </span>
         </div>
         <div class="detailReviewImg">
            <img
               src="https://a0.muscache.com/im/pictures/33357308-3507-49e5-aaaf-0b5b9fe234fc.jpg?im_w=720"
               alt="" /> <img
               src="https://a0.muscache.com/im/pictures/ff86ea07-d2f2-4f2d-a07a-ac7f7427f9b5.jpg?im_w=720"
               alt="" /> <img
               src="https://a0.muscache.com/im/pictures/243cb4d5-aa38-4162-82a0-453018af3d90.jpg?im_w=720
              "
               alt="" /> <img
               src="https://a0.muscache.com/im/pictures/e9e304c6-c95b-46d1-87a5-c8a28066314d.jpg?im_w=720"
               alt="" /> <img
               src="https://a0.muscache.com/im/pictures/4e4cba04-2ca3-43e3-9808-42976cecdac6.jpg?im_w=720"
               alt="" />
         </div>
         <div class="detailReviewText detailFoodInfo">
            <pre style="overflow-y: hidden" class="default_food_review_height">
                깨끗하고
경치 좋고
해변에 가깝고 속초 아이도 보여서
안녕하세요. 저희는 다양한 휴식 공간을 연구하고 제공하는 Onda입니다. 이 곳에서 머무르실 모든 분들께서 편안하고 행복한 시간을 보내시길 바랍니다.

          [숙소 소개]
          청량한 동해 바다를 바라보며 여행을 즐길 수 있는 숙소입니다.
          
          [객실 유형]
          분리형(더블침대1)+화장실1+주방+거실
          * 입실하시는 인원을 정확히 설정하여 예약하셨다면 인원에 맞게 침구류를 제공해드립니다.
          숙소
          ※ 퇴실시간 초과 시 시간당 10,000원의 추가비용이 발생합니다.
          ※ 전기밥솥과 커피포트 이용을 원하실 경우 반드시 숙소로 사전문의 부탁드립니다.
              </pre>
            <div class="detailFoodInfoBtn d-flex justify-content-center">
               <button id="food_review_more" class="visually-hidden"></button>
               <label for="food_review_more"><i
                  class="fa-solid fa-angles-down" style="color: #000000;"></i></label>
            </div>
         </div>
      </div>
   </div>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />