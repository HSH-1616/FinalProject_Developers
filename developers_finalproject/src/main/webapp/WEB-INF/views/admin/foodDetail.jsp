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
         </span>
      </div>

      <hr class="m-3">

      <!-- 맛집 상세정보 페이지 -->
      <div class="food_photo">
         <p class="fs-4">상세정보</p>
      </div>

      <div class="row justify-content-center" style="height: 400px;">
      <form action="${path}/admin/updateFoodByFoodNo" method="get" class="row justify-content-center" style="height: 400px;">
         <div class="foodInfosection d-flex flex-row justify-content-center align-items-center" style="height: 350px;">
            <input type="hidden" name="foodNo" value="${f.foodNo}">
            <!-- 맛집 상세정보 텍스트 -->
            <table class="text-start align-top food_info_table">
               <tr class="">
                  <th valign="top"><i class="fa-solid fa-location-dot fa-2xl"
                     style="color: #000000;"></i></th>
                  <th class="fs-4" valign="top" style="width: 95px;">장소</th>
                  <td>
                     <%-- <% pageContext.setAttribute("enter", "\n"); %> --%>
                     <%-- <div class="food_runtime">${fn:replace(f.foodOpenTime,enter,'<br>')}</div> --%>
                     <textarea rows="4" cols="30" name="foodOpenTime" 
                     style="border: none;resize: none;">${f.foodOpenTime}</textarea>
                  </td>
               </tr>
               <tr>
                  <th valign="top"><i class="fa-solid fa-utensils fa-2xl"
                     style="color: #000000;"></i></th>
                  <th class="fs-4" valign="top">메뉴</th>
                  <td>
                     <textarea rows="3" cols="40" name="foodMenu" 
                     style="border: none;resize: none;">${f.foodMenu}</textarea>
                  </td>
               </tr>
               <tr>
                  <th valign="top"><i class="fa-solid fa-phone fa-2xl"
                     style="color: #000000;"></i></th>
                  <th class="fs-4" valign="top">연락처</th>
                  <td>
                     <input type="text" name="foodPhone" style="border: none;" value="${f.foodPhone }">
                  </td>
               </tr>
            </table>
            <table class="text-start align-top food_info_table">
               <tr class="">
                  <th valign="top"><i class="fa-solid fa-clock fa-2xl"
                     style="color: #000000;"></i></th>
                  <th class="fs-4" valign="top" style="width: 95px;">영업시간</th>
                  <td>
                     <textarea rows="4" cols="40" name="foodOpenTime" 
                     style="border: none;resize: none;">${f.foodOpenTime}</textarea>
                  </td>
               </tr>
               <tr>
                  <th valign="top"><i class="fa-solid fa-circle-check fa-2xl"
                     style="color: #000000;"></i></th>
                  <th class="fs-4" valign="top">승인여부</th>
                  <td>
                     <div class="form-check form-switch">
                        <!-- 이대로 사용하면 자료형이 문자열과 숫자로 달라서 오류가 발생한다. -->
                        <!-- <input class="form-check-input" type="checkbox" name="allow" role="switch" id="flexSwitchCheckChecked" ${f.allow == 1?'checked':''}>
                        <label class="form-check-label" for="flexSwitchCheckChecked"></label> -->
                      </div>
                  </td>
               </tr>
               <tr>
                  <th valign="top">&nbsp;</th>
                  <th class="fs-4" valign="top"> </th>
                  <td>
                     <div class="food_main_phone"> </div>
                  </td>
               </tr>
            </table>
         </div>
         <!-- 승인여부가 1이 아닌 경우에만 승인하기 버튼 활성화 -->
         <button type="submit" class="yoonBtn btnColorRed" style="width: 160px;">수정하기</button>
      </form>
      </div>
   </div>

   <script>
      var foodName = '${f.foodName}';
      var foodAddr = '${f.foodAddress}';
      var mapx;
      var mapy;
      var foodImg = '${f.foodPhoto[0].fpName}';

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

</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />