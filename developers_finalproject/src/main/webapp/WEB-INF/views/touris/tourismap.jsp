<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관광경로만들기 해당페이지</title>
<script>
 	const latitude = '${titletouris.areaLatitude}';
	const Longitude = '${titletouris.areaLongitude}';
	const tourisAreaid = '${titletouris.areaId }';
	const loginmemberid = '${loginMember.memberId}';
</script>
</head>
<body>

<jsp:include page="/WEB-INF/views/touris/tourisheader.jsp"/> 
	<section>
      <div class="" id="sidebar-left">
        <div id="sidebtn"><button></button></div>
        
        <div id="area">
        <div id="schedule-area">
          <div id="schedule-top">
            <div id="travelarea">
              		<div id="traveltitle">${titletouris.areaName}</div>
              		<div>${titletouris.areaEng }</div>
              <div id="travels-chedule"></div>
            </div>
            <div id="travelplan">
              <div id="travelplan-input"><input type="text" id="calander" placeholder="날짜를 선택해 주세요" name="datefilter" value onfocus="blur()" /></div>
              <div id="travel-weather">
              <div class="weatherimgarea">
                <img src="http://openweathermap.org/img/w/${weatherIcon}.png" alt="" width="120px" height="120px">
              </div>
              <div class="weatherexarea">
              	<div class="temperature"><span>${temperature } &#8451;</span></div>
              	<div class="wethertext"><span>${weatherDescription }</span></div>
              	<div class="wethercity"><span>${city }, ${country }</span></div>
              </div>
              </div>
            </div>
          </div>
					<div id="select-traveltitle">
						<div class="weatherbottomarea"
							style="display: flex; width: 290.13px; justify-content: space-around;">
							<div style="display: grid">
								<svg version="1.1" id="Layer_1"
									xmlns="http://www.w3.org/2000/svg"
									xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
									viewBox="0 0 30 30" style="enable-background: new 0 0 30 30;"
									width="30" height="30" xml:space="preserve">
     <path
										d="M3.1,16.97c0,0.24,0.09,0.45,0.28,0.62c0.16,0.19,0.37,0.28,0.63,0.28H18.7c0.29,0,0.53,0.1,0.73,0.3
       c0.2,0.2,0.3,0.45,0.3,0.74c0,0.29-0.1,0.53-0.3,0.72c-0.2,0.19-0.44,0.29-0.74,0.29c-0.29,0-0.54-0.1-0.73-0.29
       c-0.16-0.18-0.36-0.26-0.6-0.26c-0.25,0-0.46,0.09-0.64,0.26s-0.27,0.38-0.27,0.61c0,0.25,0.09,0.46,0.28,0.63
       c0.56,0.55,1.22,0.83,1.96,0.83c0.78,0,1.45-0.27,2.01-0.81c0.56-0.54,0.83-1.19,0.83-1.97s-0.28-1.44-0.84-2
       c-0.56-0.56-1.23-0.84-2-0.84H4.01c-0.25,0-0.46,0.09-0.64,0.26C3.19,16.51,3.1,16.72,3.1,16.97z M3.1,13.69
       c0,0.23,0.09,0.43,0.28,0.61c0.17,0.18,0.38,0.26,0.63,0.26h20.04c0.78,0,1.45-0.27,2.01-0.82c0.56-0.54,0.84-1.2,0.84-1.97
       c0-0.77-0.28-1.44-0.84-1.99s-1.23-0.83-2.01-0.83c-0.77,0-1.42,0.27-1.95,0.8c-0.18,0.16-0.27,0.38-0.27,0.67
       c0,0.26,0.09,0.47,0.26,0.63c0.17,0.16,0.38,0.24,0.63,0.24c0.24,0,0.45-0.08,0.63-0.24c0.19-0.21,0.42-0.31,0.7-0.31
       c0.29,0,0.53,0.1,0.73,0.3c0.2,0.2,0.3,0.44,0.3,0.73c0,0.29-0.1,0.53-0.3,0.72c-0.2,0.19-0.44,0.29-0.73,0.29H4.01
       c-0.25,0-0.46,0.09-0.64,0.26C3.19,13.23,3.1,13.44,3.1,13.69z" />
     </svg>
								<span style="font-size: 12.5px; font-weight: 500;">${windSpeed}m/s</span>
							</div>
							<div style="display: grid">
								<svg version="1.1" id="Layer_1"
									xmlns="http://www.w3.org/2000/svg"
									xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
									viewBox="0 0 30 30" style="enable-background: new 0 0 30 30;"
									width="30" height="30" xml:space="preserve">
<path
										d="M7.56,17.19c0-0.88,0.24-1.89,0.72-3.03s1.1-2.25,1.86-3.31c1.56-2.06,2.92-3.62,4.06-4.67l0.75-0.72
 c0.25,0.26,0.53,0.5,0.83,0.72c0.41,0.42,1.04,1.11,1.88,2.09s1.57,1.85,2.17,2.65c0.71,1.01,1.32,2.1,1.81,3.25
 s0.74,2.16,0.74,3.03c0,1-0.19,1.95-0.58,2.86c-0.39,0.91-0.91,1.7-1.57,2.36c-0.66,0.66-1.45,1.19-2.37,1.58
 c-0.92,0.39-1.89,0.59-2.91,0.59c-1,0-1.95-0.19-2.86-0.57c-0.91-0.38-1.7-0.89-2.36-1.55c-0.66-0.65-1.19-1.44-1.58-2.35
 S7.56,18.23,7.56,17.19z M9.82,14.26c0,0.83,0.17,1.49,0.52,1.99c0.35,0.49,0.88,0.74,1.59,0.74c0.72,0,1.25-0.25,1.61-0.74
 c0.35-0.49,0.53-1.15,0.54-1.99c-0.01-0.84-0.19-1.5-0.54-2c-0.35-0.49-0.89-0.74-1.61-0.74c-0.71,0-1.24,0.25-1.59,0.74
 C9.99,12.76,9.82,13.42,9.82,14.26z M11.39,14.26c0-0.15,0-0.27,0-0.35s0.01-0.19,0.02-0.33c0.01-0.14,0.02-0.25,0.05-0.32
 s0.05-0.16,0.09-0.24c0.04-0.08,0.09-0.15,0.15-0.18c0.07-0.04,0.14-0.06,0.23-0.06c0.14,0,0.25,0.04,0.33,0.12s0.14,0.21,0.17,0.38
 c0.03,0.18,0.05,0.32,0.06,0.45s0.01,0.3,0.01,0.52c0,0.23,0,0.4-0.01,0.52c-0.01,0.12-0.03,0.27-0.06,0.45
 c-0.03,0.17-0.09,0.3-0.17,0.38s-0.19,0.12-0.33,0.12c-0.09,0-0.16-0.02-0.23-0.06c-0.07-0.04-0.12-0.1-0.15-0.18
 c-0.04-0.08-0.07-0.17-0.09-0.24c-0.02-0.08-0.04-0.19-0.05-0.32c-0.01-0.14-0.02-0.25-0.02-0.32S11.39,14.41,11.39,14.26z
  M11.98,22.01h1.32l4.99-10.74h-1.35L11.98,22.01z M16.28,19.02c0.01,0.84,0.2,1.5,0.55,2c0.35,0.49,0.89,0.74,1.6,0.74
 c0.72,0,1.25-0.25,1.6-0.74c0.35-0.49,0.52-1.16,0.53-2c-0.01-0.84-0.18-1.5-0.53-1.99c-0.35-0.49-0.88-0.74-1.6-0.74
 c-0.71,0-1.25,0.25-1.6,0.74C16.47,17.52,16.29,18.18,16.28,19.02z M17.85,19.02c0-0.23,0-0.4,0.01-0.52
 c0.01-0.12,0.03-0.27,0.06-0.45s0.09-0.3,0.17-0.38s0.19-0.12,0.33-0.12c0.09,0,0.17,0.02,0.24,0.06c0.07,0.04,0.12,0.1,0.16,0.19
 c0.04,0.09,0.07,0.17,0.1,0.24s0.04,0.18,0.05,0.32l0.01,0.32l0,0.34c0,0.16,0,0.28,0,0.35l-0.01,0.32l-0.05,0.32l-0.1,0.24
 l-0.16,0.19l-0.24,0.06c-0.14,0-0.25-0.04-0.33-0.12s-0.14-0.21-0.17-0.38c-0.03-0.18-0.05-0.33-0.06-0.45S17.85,19.25,17.85,19.02z
 " />
</svg>
								<span style="font-size: 12.5px; font-weight: 500;">${humidity}%</span>
							</div>
							<div style="display: grid">
								<svg version="1.1" id="Layer_1"
									xmlns="http://www.w3.org/2000/svg"
									xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
									viewBox="0 0 30 30" style="enable-background: new 0 0 30 30;"
									width="30" height="30" xml:space="preserve">
     <path
										d="M3.89,17.6c0-0.99,0.31-1.88,0.93-2.65s1.41-1.27,2.38-1.49c0.26-1.17,0.85-2.14,1.78-2.88c0.93-0.75,2-1.12,3.22-1.12
       c1.18,0,2.24,0.36,3.16,1.09c0.93,0.73,1.53,1.66,1.8,2.8h0.27c1.18,0,2.18,0.41,3.01,1.24s1.25,1.83,1.25,3
       c0,1.18-0.42,2.18-1.25,3.01s-1.83,1.25-3.01,1.25H8.16c-0.58,0-1.13-0.11-1.65-0.34S5.52,21,5.14,20.62
       c-0.38-0.38-0.68-0.84-0.91-1.36S3.89,18.17,3.89,17.6z M5.34,17.6c0,0.76,0.28,1.42,0.82,1.96s1.21,0.82,1.99,0.82h9.28
       c0.77,0,1.44-0.27,1.99-0.82c0.55-0.55,0.83-1.2,0.83-1.96c0-0.76-0.27-1.42-0.83-1.96c-0.55-0.54-1.21-0.82-1.99-0.82h-1.39
       c-0.1,0-0.15-0.05-0.15-0.15l-0.07-0.49c-0.1-0.94-0.5-1.73-1.19-2.35s-1.51-0.93-2.45-0.93c-0.94,0-1.76,0.31-2.46,0.94
       c-0.7,0.62-1.09,1.41-1.18,2.34l-0.07,0.42c0,0.1-0.05,0.15-0.16,0.15l-0.45,0.07c-0.72,0.06-1.32,0.36-1.81,0.89
       C5.59,16.24,5.34,16.87,5.34,17.6z M14.19,8.88c-0.1,0.09-0.08,0.16,0.07,0.21c0.43,0.19,0.79,0.37,1.08,0.55
       c0.11,0.03,0.19,0.02,0.22-0.03c0.61-0.57,1.31-0.86,2.12-0.86c0.81,0,1.5,0.27,2.1,0.81c0.59,0.54,0.92,1.21,0.99,2l0.09,0.64h1.42
       c0.65,0,1.21,0.23,1.68,0.7c0.47,0.47,0.7,1.02,0.7,1.66c0,0.6-0.21,1.12-0.62,1.57s-0.92,0.7-1.53,0.77c-0.1,0-0.15,0.05-0.15,0.16
       v1.13c0,0.11,0.05,0.16,0.15,0.16c1.01-0.06,1.86-0.46,2.55-1.19s1.04-1.6,1.04-2.6c0-1.06-0.37-1.96-1.12-2.7
       c-0.75-0.75-1.65-1.12-2.7-1.12h-0.15c-0.26-1-0.81-1.82-1.65-2.47c-0.83-0.65-1.77-0.97-2.8-0.97C16.28,7.29,15.11,7.82,14.19,8.88
       z" />
     </svg>
								<span style="font-size: 12.5px; font-weight: 500;">${clouds}%</span>
							</div>
						</div>
					</div>
				</div>
          <div id="select-area">
            <div class="select-travelname">
            <div class="mydict">
              <div class="selectlistdivarea">
              <span>선택목록</span>
                <!-- <div class="mydictlabel">
                  <input type="radio" name="radio" checked="">
                  <span>선택목록</span>
                </div> -->
                <!-- <label class="mydictlabel">
                  <input type="radio" name="radio">
                  <span>맛집</span>
                </label> -->
              </div>
            </div>
            </div>
            <div id="select-trvellist">
              <button class="select-delte">
                <p>선택한장소전체삭제</p>
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                  <path stroke-linecap="round" stroke-linejoin="round" d="M20.25 7.5l-.625 10.632a2.25 2.25 0 01-2.247 2.118H6.622a2.25 2.25 0 01-2.247-2.118L3.75 7.5m6 4.125l2.25 2.25m0 0l2.25 2.25M12 13.875l2.25-2.25M12 13.875l-2.25 2.25M3.375 7.5h17.25c.621 0 1.125-.504 1.125-1.125v-1.5c0-.621-.504-1.125-1.125-1.125H3.375c-.621 0-1.125.504-1.125 1.125v1.5c0 .621.504 1.125 1.125 1.125z" />
                </svg>                
              </button>
              <ul class="listarea">
                 <li class="list-text"><hs><span class="span1">가고 싶은 장소들을 검색하여 추가해주세요.</span><br><span class="span2">설정하신 일자별 여행기간 내에서</span><br><span class="span3">여행경로를 순서대로 작성 할 수 있습니다.</span><br></hs>
                  <svg xmlns="http://www.w3.org/2000/svg" class="list-texticon" fill="none" width="24" height="24" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M12 6v12m6-6H6" />
                  </svg>
                </li>
          
                
                <!--  -->
              </ul>
                  <ul class="listarea2">
                 <li class="list-text"><hs><span class="span1">가고 싶은 장소들을 검색하여 추가해주세요.</span><br><span class="span2">설정하신 일자별 여행기간 내에서</span><br><span class="span3">여행경로를 순서대로 작성 할 수 있습니다.</span><br></hs>
                  <svg xmlns="http://www.w3.org/2000/svg" class="list-texticon" fill="none" width="24" height="24" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M12 6v12m6-6H6" />
                  </svg>
                </li>
          
                
                
              </ul>
            </div>
          </div>
        </div>
        
      </div>
    </div>
      <div id="map">
        <div class="sildertoggle">
          <div class="toggle-header" id="input"><label class="value">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6">
              <path fill-rule="evenodd" d="M3 6.75A.75.75 0 013.75 6h16.5a.75.75 0 010 1.5H3.75A.75.75 0 013 6.75zM3 12a.75.75 0 01.75-.75h16.5a.75.75 0 010 1.5H3.75A.75.75 0 013 12zm0 5.25a.75.75 0 01.75-.75h16.5a.75.75 0 010 1.5H3.75a.75.75 0 01-.75-.75z" clip-rule="evenodd" />
            </svg>
            <span>메뉴</span>
          </label>
          
          </div>
          <div class="toggle-body" id="input">
            <button class="value">
              <svg viewBox="0 0 16 16" xmlns="http://www.w3.org/2000/svg" data-name="Layer 2"><path fill="#7D8590" d="m1.5 13v1a.5.5 0 0 0 .3379.4731 18.9718 18.9718 0 0 0 6.1621 1.0269 18.9629 18.9629 0 0 0 6.1621-1.0269.5.5 0 0 0 .3379-.4731v-1a6.5083 6.5083 0 0 0 -4.461-6.1676 3.5 3.5 0 1 0 -4.078 0 6.5083 6.5083 0 0 0 -4.461 6.1676zm4-9a2.5 2.5 0 1 1 2.5 2.5 2.5026 2.5026 0 0 1 -2.5-2.5zm2.5 3.5a5.5066 5.5066 0 0 1 5.5 5.5v.6392a18.08 18.08 0 0 1 -11 0v-.6392a5.5066 5.5066 0 0 1 5.5-5.5z"></path></svg>
              이용방법
            </button>
            <button class="value" id="registspot">
              <svg id="Line" viewBox="0 0 32 32" xmlns="http://www.w3.org/2000/svg"><path fill="#7D8590" id="XMLID_1646_" d="m17.074 30h-2.148c-1.038 0-1.914-.811-1.994-1.846l-.125-1.635c-.687-.208-1.351-.484-1.985-.824l-1.246 1.067c-.788.677-1.98.631-2.715-.104l-1.52-1.52c-.734-.734-.78-1.927-.104-2.715l1.067-1.246c-.34-.635-.616-1.299-.824-1.985l-1.634-.125c-1.035-.079-1.846-.955-1.846-1.993v-2.148c0-1.038.811-1.914 1.846-1.994l1.635-.125c.208-.687.484-1.351.824-1.985l-1.068-1.247c-.676-.788-.631-1.98.104-2.715l1.52-1.52c.734-.734 1.927-.779 2.715-.104l1.246 1.067c.635-.34 1.299-.616 1.985-.824l.125-1.634c.08-1.034.956-1.845 1.994-1.845h2.148c1.038 0 1.914.811 1.994 1.846l.125 1.635c.687.208 1.351.484 1.985.824l1.246-1.067c.787-.676 1.98-.631 2.715.104l1.52 1.52c.734.734.78 1.927.104 2.715l-1.067 1.246c.34.635.616 1.299.824 1.985l1.634.125c1.035.079 1.846.955 1.846 1.993v2.148c0 1.038-.811 1.914-1.846 1.994l-1.635.125c-.208.687-.484 1.351-.824 1.985l1.067 1.246c.677.788.631 1.98-.104 2.715l-1.52 1.52c-.734.734-1.928.78-2.715.104l-1.246-1.067c-.635.34-1.299.616-1.985.824l-.125 1.634c-.079 1.035-.955 1.846-1.993 1.846zm-5.835-6.373c.848.53 1.768.912 2.734 1.135.426.099.739.462.772.898l.18 2.341 2.149-.001.18-2.34c.033-.437.347-.8.772-.898.967-.223 1.887-.604 2.734-1.135.371-.232.849-.197 1.181.089l1.784 1.529 1.52-1.52-1.529-1.784c-.285-.332-.321-.811-.089-1.181.53-.848.912-1.768 1.135-2.734.099-.426.462-.739.898-.772l2.341-.18h-.001v-2.148l-2.34-.18c-.437-.033-.8-.347-.898-.772-.223-.967-.604-1.887-1.135-2.734-.232-.37-.196-.849.089-1.181l1.529-1.784-1.52-1.52-1.784 1.529c-.332.286-.81.321-1.181.089-.848-.53-1.768-.912-2.734-1.135-.426-.099-.739-.462-.772-.898l-.18-2.341-2.148.001-.18 2.34c-.033.437-.347.8-.772.898-.967.223-1.887.604-2.734 1.135-.37.232-.849.197-1.181-.089l-1.785-1.529-1.52 1.52 1.529 1.784c.285.332.321.811.089 1.181-.53.848-.912 1.768-1.135 2.734-.099.426-.462.739-.898.772l-2.341.18.002 2.148 2.34.18c.437.033.8.347.898.772.223.967.604 1.887 1.135 2.734.232.37.196.849-.089 1.181l-1.529 1.784 1.52 1.52 1.784-1.529c.332-.287.813-.32 1.18-.089z"></path><path id="XMLID_1645_" fill="#7D8590" d="m16 23c-3.859 0-7-3.141-7-7s3.141-7 7-7 7 3.141 7 7-3.141 7-7 7zm0-12c-2.757 0-5 2.243-5 5s2.243 5 5 5 5-2.243 5-5-2.243-5-5-5z"></path></svg>
              장소등록
            </button>
            <button class="value">
              <svg viewBox="0 0 128 128" xmlns="http://www.w3.org/2000/svg"><path fill="#7D8590" d="m109.9 20.63a6.232 6.232 0 0 0 -8.588-.22l-57.463 51.843c-.012.011-.02.024-.031.035s-.023.017-.034.027l-4.721 4.722a1.749 1.749 0 0 0 0 2.475l.341.342-3.16 3.16a8 8 0 0 0 -1.424 1.967 11.382 11.382 0 0 0 -12.055 10.609c-.006.036-.011.074-.015.111a5.763 5.763 0 0 1 -4.928 5.41 1.75 1.75 0 0 0 -.844 3.14c4.844 3.619 9.4 4.915 13.338 4.915a17.14 17.14 0 0 0 11.738-4.545l.182-.167a11.354 11.354 0 0 0 3.348-8.081c0-.225-.02-.445-.032-.667a8.041 8.041 0 0 0 1.962-1.421l3.16-3.161.342.342a1.749 1.749 0 0 0 2.475 0l4.722-4.722c.011-.011.018-.025.029-.036s.023-.018.033-.029l51.844-57.46a6.236 6.236 0 0 0 -.219-8.589zm-70.1 81.311-.122.111c-.808.787-7.667 6.974-17.826 1.221a9.166 9.166 0 0 0 4.36-7.036 1.758 1.758 0 0 0 .036-.273 7.892 7.892 0 0 1 9.122-7.414c.017.005.031.014.048.019a1.717 1.717 0 0 0 .379.055 7.918 7.918 0 0 1 4 13.317zm5.239-10.131c-.093.093-.194.176-.293.26a11.459 11.459 0 0 0 -6.289-6.286c.084-.1.167-.2.261-.3l3.161-3.161 6.321 6.326zm7.214-4.057-9.479-9.479 2.247-2.247 9.479 9.479zm55.267-60.879-50.61 56.092-9.348-9.348 56.092-50.61a2.737 2.737 0 0 1 3.866 3.866z"></path></svg>
              추천일정
            </button>
            <button class="value" id="routecreatebtn">
              <svg id="svg8" viewBox="0 0 32 32" xmlns="http://www.w3.org/2000/svg"><g id="layer1" transform="translate(-33.022 -30.617)"><path fill="#7D8590" id="path26276" d="m49.021 31.617c-2.673 0-4.861 2.188-4.861 4.861 0 1.606.798 3.081 1.873 3.834h-7.896c-1.7 0-3.098 1.401-3.098 3.1s1.399 3.098 3.098 3.098h4.377l.223 2.641s-1.764 8.565-1.764 8.566c-.438 1.642.55 3.355 2.191 3.795s3.327-.494 3.799-2.191l2.059-5.189 2.059 5.189c.44 1.643 2.157 2.631 3.799 2.191s2.63-2.153 2.191-3.795l-1.764-8.566.223-2.641h4.377c1.699 0 3.098-1.399 3.098-3.098s-1.397-3.1-3.098-3.1h-7.928c1.102-.771 1.904-2.228 1.904-3.834 0-2.672-2.189-4.861-4.862-4.861zm0 2c1.592 0 2.861 1.27 2.861 2.861 0 1.169-.705 2.214-1.789 2.652-.501.203-.75.767-.563 1.273l.463 1.254c.145.393.519.654.938.654h8.975c.626 0 1.098.473 1.098 1.1s-.471 1.098-1.098 1.098h-5.297c-.52 0-.952.398-.996.916l-.311 3.701c-.008.096-.002.191.018.285 0 0 1.813 8.802 1.816 8.82.162.604-.173 1.186-.777 1.348s-1.184-.173-1.346-.777c-.01-.037-3.063-7.76-3.063-7.76-.334-.842-1.525-.842-1.859 0 0 0-3.052 7.723-3.063 7.76-.162.604-.741.939-1.346.777s-.939-.743-.777-1.348c.004-.019 1.816-8.82 1.816-8.82.02-.094.025-.189.018-.285l-.311-3.701c-.044-.518-.477-.916-.996-.916h-5.297c-.627 0-1.098-.471-1.098-1.098s.472-1.1 1.098-1.1h8.975c.419 0 .793-.262.938-.654l.463-1.254c.188-.507-.062-1.07-.563-1.273-1.084-.438-1.789-1.483-1.789-2.652.001-1.591 1.271-2.861 2.862-2.861z"></path></g></svg>
              일정생성
            </button>
            </div>
            </div>
      </div>

      <div id="sidebar-right">
        <div id="search-traveltitle">
        <div id="row1" class="row">
          <div class="form__group">
            <input type="text" class="form__field w-100"  id="searchtouirsinput" placeholder="관광지 검색">
            <label for="name" class="form__label"> 관광지 입력 </label>
            <button class="invite-btn1" type="button">
              <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6">
                <path fill-rule="evenodd" d="M10.5 3.75a6.75 6.75 0 100 13.5 6.75 6.75 0 000-13.5zM2.25 10.5a8.25 8.25 0 1114.59 5.28l4.69 4.69a.75.75 0 11-1.06 1.06l-4.69-4.69A8.25 8.25 0 012.25 10.5z" clip-rule="evenodd"></path>
              </svg>
            </button>
          </div>
        </div>
        <div id="search-travel">
          <label type="button" class="btn-search search-class1"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6">
            <path d="M11.584 2.376a.75.75 0 01.832 0l9 6a.75.75 0 11-.832 1.248L12 3.901 3.416 9.624a.75.75 0 01-.832-1.248l9-6z" />
            <path fill-rule="evenodd" d="M20.25 10.332v9.918H21a.75.75 0 010 1.5H3a.75.75 0 010-1.5h.75v-9.918a.75.75 0 01.634-.74A49.109 49.109 0 0112 9c2.59 0 5.134.202 7.616.592a.75.75 0 01.634.74zm-7.5 2.418a.75.75 0 00-1.5 0v6.75a.75.75 0 001.5 0v-6.75zm3-.75a.75.75 0 01.75.75v6.75a.75.75 0 01-1.5 0v-6.75a.75.75 0 01.75-.75zM9 12.75a.75.75 0 00-1.5 0v6.75a.75.75 0 001.5 0v-6.75z" clip-rule="evenodd" />
            <path d="M12 7.875a1.125 1.125 0 100-2.25 1.125 1.125 0 000 2.25z" />
          </svg>
          <span>관광지</span></label>
          <label type="button" class="btn-food food-class1"><svg xmlns="http://www.w3.org/2000/svg" class="ionicon" viewBox="0 0 512 512"><path d="M357.57 223.94a79.48 79.48 0 0056.58-23.44l77-76.95c6.09-6.09 6.65-16 .85-22.39a16 16 0 00-23.17-.56l-68.63 68.58a12.29 12.29 0 01-17.37 0c-4.79-4.78-4.53-12.86.25-17.64l68.33-68.33a16 16 0 00-.56-23.16A15.62 15.62 0 00440.27 56a16.71 16.71 0 00-11.81 4.9l-68.27 68.26a12.29 12.29 0 01-17.37 0c-4.78-4.78-4.53-12.86.25-17.64l68.33-68.31a16 16 0 00-.56-23.16A15.62 15.62 0 00400.26 16a16.73 16.73 0 00-11.81 4.9L311.5 97.85a79.49 79.49 0 00-23.44 56.59v8.23a16 16 0 01-4.69 11.33l-35.61 35.62a4 4 0 01-5.66 0L68.82 36.33a16 16 0 00-22.58-.06C31.09 51.28 23 72.47 23 97.54c-.1 41.4 21.66 89 56.79 124.08l85.45 85.45A64.79 64.79 0 00211 326a64 64 0 0016.21-2.08 16.24 16.24 0 014.07-.53 15.93 15.93 0 0110.83 4.25l11.39 10.52a16.12 16.12 0 014.6 11.23v5.54a47.73 47.73 0 0013.77 33.65l90.05 91.57.09.1a53.29 53.29 0 0075.36-75.37L302.39 269.9a4 4 0 010-5.66L338 228.63a16 16 0 0111.32-4.69z"/><path d="M211 358a97.32 97.32 0 01-68.36-28.25l-13.86-13.86a8 8 0 00-11.3 0l-85 84.56c-15.15 15.15-20.56 37.45-13.06 59.29a30.63 30.63 0 001.49 3.6C31 484 50.58 496 72 496a55.68 55.68 0 0039.64-16.44L225 365.66a4.69 4.69 0 001.32-3.72v-.26a4.63 4.63 0 00-5.15-4.27A97.09 97.09 0 01211 358z"/></svg>
          <span>맛집</span></label>
        </div>
      </div>
      <div id="travellist-area">
        <div id="travelplace"><b><span>추천장소</span></b></div>
        <ul class="listarea-right">

        <c:forEach var="t" items="${tourislist }">
          <li class="selectlist2">
            <div class="listbox">
              <div class="listboximg">
              <c:if test="${not empty t.tourismainImge}">
              <img src="${t.tourismainImge }" alt="">
              </c:if>
              <c:if test="${empty t.tourismainImge || t.tourismainImge == null }">
				<img
					src="http://skhdt.hagiang.gov.vn/assets/img/default.jpg"
					alt="">
			  </c:if>
              </div>
              <div class="listicon"><svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6" width="10" color="white">
                <path stroke-linecap="round" stroke-linejoin="round" d="M12 21v-8.25M15.75 21v-8.25M8.25 21v-8.25M3 9l9-6 9 6m-1.5 12V10.332A48.36 48.36 0 0012 9.75c-2.551 0-5.056.2-7.5.582V21M3 21h18M12 6.75h.008v.008H12V6.75z" />
              </svg>
              </div>
            </div>
            <div class="listtext">
              <h7 class="listtitle-text">${t.tourisName }</h7>
              <h7 class="listtitleaddr">주소 : <h7 class="listtitle-textaddr">${t.tourisAddress }</h7></h7>
            </div>
            <div class="listbtn">
              <div class="detail-travellist"><a><div class="tourisidhidden" style="display: none">${t.tourisId }</div><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6" width="20" height="20" color="#e0e0e0">
                <path fill-rule="evenodd" d="M2.25 12c0-5.385 4.365-9.75 9.75-9.75s9.75 4.365 9.75 9.75-4.365 9.75-9.75 9.75S2.25 17.385 2.25 12zm11.378-3.917c-.89-.777-2.366-.777-3.255 0a.75.75 0 01-.988-1.129c1.454-1.272 3.776-1.272 5.23 0 1.513 1.324 1.513 3.518 0 4.842a3.75 3.75 0 01-.837.552c-.676.328-1.028.774-1.028 1.152v.75a.75.75 0 01-1.5 0v-.75c0-1.279 1.06-2.107 1.875-2.502.182-.088.351-.199.503-.331.83-.727.83-1.857 0-2.584zM12 18a.75.75 0 100-1.5.75.75 0 000 1.5z" clip-rule="evenodd" />
              </svg>
              </a>
              </div>
              <div title="선택목록 장소에 추가" class="plus-travel"><a><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6" width="20" height="20" color="black">
                <path fill-rule="evenodd" d="M12 3.75a.75.75 0 01.75.75v6.75h6.75a.75.75 0 010 1.5h-6.75v6.75a.75.75 0 01-1.5 0v-6.75H4.5a.75.75 0 010-1.5h6.75V4.5a.75.75 0 01.75-.75z" clip-rule="evenodd" />
              </svg>              
              </a>
              </div>
            </div>
          </li>
          </c:forEach>
          <!--  -->
         
          </ul>
      </div>
      </div>
    <!-- 여기서 부터는 모달창 html -->
    <div class="totalmodaldiv">
      <div class="modaldiv">
        <div class="modaldivflex">
        <span class="modalspan"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6">
         <path fill-rule="evenodd" d="M5.47 5.47a.75.75 0 011.06 0L12 10.94l5.47-5.47a.75.75 0 111.06 1.06L13.06 12l5.47 5.47a.75.75 0 11-1.06 1.06L12 13.06l-5.47 5.47a.75.75 0 01-1.06-1.06L10.94 12 5.47 6.53a.75.75 0 010-1.06z" clip-rule="evenodd" />
       </svg>
       </span>

       <div class="modalflex">
         <img class="modalimage" src="" alt="placephoto" id="locationInfoImage" loading="lazy">
         <label class="ui-bookmark">
          <input type="checkbox">
          <div class="bookmark">
            <svg viewBox="0 0 32 32">
              <g>
                <path d="M27 4v27a1 1 0 0 1-1.625.781L16 24.281l-9.375 7.5A1 1 0 0 1 5 31V4a4 4 0 0 1 4-4h14a4 4 0 0 1 4 4z"></path>
              </g>
            </svg>
          </div>
        </label>
       </div>
         <div class="modalcontainer">
           <h3 class="modalplacetitle"></h3>
           <div class="modalhidden"></div>
           <div class="modalplacetitlebar">-</div>
             <div class="modaltable">
               <div class="madaltr">
                 <div class="modaltrarea">
                   <div class="modalthtime">영업시간 :  <div class="modaltdtime"><span style="cursor: pointer;">더보기</span></div></div>
                   <div class="modalthhomepage">홈페이지 : <div class="modaltdhomepage"></div></div>
                   <div class="modalthaddress">주소  :  <div class="modaltdaddress"></div></div>
                   <div class="modalthinfo">문의안내 :  <div class="modaltdinfo"></div></div>
                    <div class="modalthusetime">이용시기 및 이용시간 : <div class="modaltdusetime"></div></div>
                   <div class="modalthtel">전화번호 : <div class="modaltdtel"></div></div>
                 </div>
                <!--  <div class="modaltr2">
                   <div class="modaltdtime"><span style="cursor: pointer;">더보기</span></div>
                   
                   
                   <div class="modaltdaddress"></div>
                   <div class="modaltdinfo"></div>
                   <div class="modaltdusetime"></div>
                   <div class="modaltdtel"></div>
                 </div>
               </div> -->
              
             </div>
           <div class="modalbtncontainer">
             <label class="modallabel" id="" >
               리뷰보기<span class="">(0)</span>
             </label>
             <label class="modalabel2" id="" style="display: flex;">
               목록에 추가
             </label>
           </div>
          </div>
        </div>
     </div>
    </div>
<!-- 작은 모달창 -->
<!-- 장소등록 모달창 -->
<div class="regspot">
  <div class="smallmodal">
    <div class="header-text">
      <span class="header-delete">x</span>
      <h3 data-langnum="36" class="header-title">장 소 등 록</h3>
      <p class="header-titlep" style="margin-top: 0; margin: 0 0 8px 0">
        <h7 class="header-h7" style="color: #999" data-langnum="37">검색해도 나오지 않는 장소를 이곳에서 등록 후 다시 검색해보세요.</h7>
      </p>
    </div>
    <div class="body-text">
      <div class="body-p">
        <p>
          <h7 style="color: #999" data-langnum="40">추가하실 장소의 유형을 선택해주세요.</h7>
        </p>
      </div>
      <div class="body-select">
        <label type="button" class="btn-search search-class1"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6">
          <path d="M11.584 2.376a.75.75 0 01.832 0l9 6a.75.75 0 11-.832 1.248L12 3.901 3.416 9.624a.75.75 0 01-.832-1.248l9-6z"></path>
          <path fill-rule="evenodd" d="M20.25 10.332v9.918H21a.75.75 0 010 1.5H3a.75.75 0 010-1.5h.75v-9.918a.75.75 0 01.634-.74A49.109 49.109 0 0112 9c2.59 0 5.134.202 7.616.592a.75.75 0 01.634.74zm-7.5 2.418a.75.75 0 00-1.5 0v6.75a.75.75 0 001.5 0v-6.75zm3-.75a.75.75 0 01.75.75v6.75a.75.75 0 01-1.5 0v-6.75a.75.75 0 01.75-.75zM9 12.75a.75.75 0 00-1.5 0v6.75a.75.75 0 001.5 0v-6.75z" clip-rule="evenodd"></path>
          <path d="M12 7.875a1.125 1.125 0 100-2.25 1.125 1.125 0 000 2.25z"></path>
        </svg>
        <span>관광지</span></label>
        <label type="button" class="btn-food food-class1"><svg xmlns="http://www.w3.org/2000/svg" class="ionicon" viewBox="0 0 512 512"><path d="M357.57 223.94a79.48 79.48 0 0056.58-23.44l77-76.95c6.09-6.09 6.65-16 .85-22.39a16 16 0 00-23.17-.56l-68.63 68.58a12.29 12.29 0 01-17.37 0c-4.79-4.78-4.53-12.86.25-17.64l68.33-68.33a16 16 0 00-.56-23.16A15.62 15.62 0 00440.27 56a16.71 16.71 0 00-11.81 4.9l-68.27 68.26a12.29 12.29 0 01-17.37 0c-4.78-4.78-4.53-12.86.25-17.64l68.33-68.31a16 16 0 00-.56-23.16A15.62 15.62 0 00400.26 16a16.73 16.73 0 00-11.81 4.9L311.5 97.85a79.49 79.49 0 00-23.44 56.59v8.23a16 16 0 01-4.69 11.33l-35.61 35.62a4 4 0 01-5.66 0L68.82 36.33a16 16 0 00-22.58-.06C31.09 51.28 23 72.47 23 97.54c-.1 41.4 21.66 89 56.79 124.08l85.45 85.45A64.79 64.79 0 00211 326a64 64 0 0016.21-2.08 16.24 16.24 0 014.07-.53 15.93 15.93 0 0110.83 4.25l11.39 10.52a16.12 16.12 0 014.6 11.23v5.54a47.73 47.73 0 0013.77 33.65l90.05 91.57.09.1a53.29 53.29 0 0075.36-75.37L302.39 269.9a4 4 0 010-5.66L338 228.63a16 16 0 0111.32-4.69z"></path><path d="M211 358a97.32 97.32 0 01-68.36-28.25l-13.86-13.86a8 8 0 00-11.3 0l-85 84.56c-15.15 15.15-20.56 37.45-13.06 59.29a30.63 30.63 0 001.49 3.6C31 484 50.58 496 72 496a55.68 55.68 0 0039.64-16.44L225 365.66a4.69 4.69 0 001.32-3.72v-.26a4.63 4.63 0 00-5.15-4.27A97.09 97.09 0 01211 358z"></path></svg>
          <span>맛집</span></label>
      </div>
      <div class="body-input">
        <div class="bodyinput-container">
          <input type="text" class="bodyinputarea" required="">
          <label for="input" class="bodyinputlabel">검색할 장소를 입력하세요.</label>
          <div class="underline"></div>
        </div>
        
      </div>
    </div>
    <div class="footer-text"><a href="#" class="footera">검색</a></div>
    </div>
    
  </div>
</div>
<!-- 장소등록 검색리스트 모달창 -->
<div class="reqsearchlist">
  <div class="reqsearchlistmodalarea">
    <div class="black-text">
      <span onclick="reqSearchSpotRegClose()" class="black-delteicon">×</span>

      <h7 class="black-texth7">검 색 결 과</h7>
    </div>

    <div class="black-smalltextarea">
      <ul class="black-smalltextul">
        <!-- 검색개수표시 Div -->
        <li class="black-smalltextli">
          <h5 class="black-samlltexth5">
            <span data-langnum="46">총</span>&nbsp;<span id="searchInfoForRegSpotCnt">1</span><span data-langnum="47">건의 결과(최대 20개)</span>
          </h5>
        </li>

        <div id="reqsearchlistresult">
          <li class="reqsearchlistresultli">
                <div style="display: flex">
                    <!-- 검색장소정보이름/주소 Div -->
                    <div class="listresultstly">
                        <h7>제주특별자치도</h7><br>
                        <hs>대한민국 제주특별자치도</hs>
                    </div>
                    <!-- 검색장소버튼 Div -->
                    <div class="reqspotbtnarea">
                        <div title="선택장소등록" class="reqspotbtn" onclick="reqSpotReg(0)"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6">
                          <path fill-rule="evenodd" d="M12 5.25a.75.75 0 01.75.75v5.25H18a.75.75 0 010 1.5h-5.25V18a.75.75 0 01-1.5 0v-5.25H6a.75.75 0 010-1.5h5.25V6a.75.75 0 01.75-.75z" clip-rule="evenodd" />
                        </svg>
                        </div>
                        <div title="구글맵에서보기" class="reqspotbtn2" onclick="searchInWeb('google', '제주특별자치도', '33.3846216', '126.5534925')"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6">
                          <path fill-rule="evenodd" d="M10.5 3.75a6.75 6.75 0 100 13.5 6.75 6.75 0 000-13.5zM2.25 10.5a8.25 8.25 0 1114.59 5.28l4.69 4.69a.75.75 0 11-1.06 1.06l-4.69-4.69A8.25 8.25 0 012.25 10.5z" clip-rule="evenodd" />
                        </svg>
                        </div>
                    </div>
                </div>
            </li>
         </div>
      </ul>
    </div>

    <div>
      <button class="reqsearchdeltebtn" onclick="reqSearchSpotRegClose()">
        <h7 data-langnum="48">닫 기</h7>
      </button>
    </div>
  </div>
</div>
<!-- 경로생성 모달창 -->
<div class="routetotalmodal"> 
           
  <div class="routemainmodaldiv">
    <div class="routecenterbar">
      <div class="routepostioncenterbar">
          <h7><b>developers</b></h7>
          <span class="routespan">x</span>
      </div>
    </div>
    <!-- 여기서 부터 메인body -->
    <div class="routetotalbody">
      <!-- 일반비워두기 -->
      <div id="home">
        <div id="routemaparea">
          <div id="routemap">
          </div>
        </div>
      </div>
    </div>
    <!-- 왼쪽의 작은 사이드바 -->
    <div class="routesmallsidebar">
      <div class="routesmallsidebartimearea">
        <div class="routesamllsiderbarschedule">
          <h7>일정</h7>
        </div>
        <div class="routesamllsiderbartotalschedule">
          <div class="totalscheduletext">
            <h8>전체일정</h8>
          </div>
          <!-- 일자별리스트버튼 -->
          <div class="schedultlisttext">
            <div class="smallschedullisttextarea">
              <div class="smallschedullisttext">
                <h7>DAY1</h7>
              </div>
            </div>
          </div>
        </div>
        
      </div>
    </div>
    <!-- 왼쪽 큰 사이드바 -->
    <div class="routebigsidebar">
      <div class="bigsidebararea">
        <div class="bigsidbararea2">
          <!-- 우측확대버튼 -->
          <div class="sidebarexpansion"><svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
            <path stroke-linecap="round" stroke-linejoin="round" d="M3.75 3.75v4.5m0-4.5h4.5m-4.5 0L9 9M3.75 20.25v-4.5m0 4.5h4.5m-4.5 0L9 15M20.25 3.75h-4.5m4.5 0v4.5m0-4.5L15 9m5.25 11.25h-4.5m4.5 0v-4.5m0 4.5L15 15" />
          </svg>
          </div>
          <h7 class="bigsidebarcentertitle"><b><span>${titletouris.areaName}</span> 
          " : " 
          <span class="schedulecount"></span><span>일 여행</span></b></h7>
        </div>
      </div>
      <div calss="bigsidebartitleimgesarea">
        <div class="bigsidbarimgessize">
          <div class="bigsidebarimagesarea">
            <img src="https://png.pngtree.com/png-clipart/20210610/ourmid/pngtree-travel-with-suitcases-png-image_3428889.jpg" alt="" width="118" height="46">
          </div>
        </div>
      </div>
      <!-- 전체일정리스트 -->
      <div class="alldaydetailschedule">
        <div class="detailroutetour">
          <div class="alldayinputfield">
            <select id="dayselectbox">
             
            </select>
          </div>
          <div class="detailroutetext"><hs>일차를 누르면 일정 전체 변경이 가능합니다.</hs></div>
        
          <div class="dailyroutedetaillist">
            <div class="dailyroutecountarea">
              <div class="dailyroutetext">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                  <path stroke-linecap="round" stroke-linejoin="round" d="M12.75 3.03v.568c0 .334.148.65.405.864l1.068.89c.442.369.535 1.01.216 1.49l-.51.766a2.25 2.25 0 01-1.161.886l-.143.048a1.107 1.107 0 00-.57 1.664c.369.555.169 1.307-.427 1.605L9 13.125l.423 1.059a.956.956 0 01-1.652.928l-.679-.906a1.125 1.125 0 00-1.906.172L4.5 15.75l-.612.153M12.75 3.031a9 9 0 00-8.862 12.872M12.75 3.031a9 9 0 016.69 14.036m0 0l-.177-.529A2.25 2.25 0 0017.128 15H16.5l-.324-.324a1.453 1.453 0 00-2.328.377l-.036.073a1.586 1.586 0 01-.982.816l-.99.282c-.55.157-.894.702-.8 1.267l.073.438c.08.474.49.821.97.821.846 0 1.598.542 1.865 1.345l.215.643m5.276-3.67a9.012 9.012 0 01-5.276 3.67m0 0a9 9 0 01-10.275-4.835M15.75 9c0 .896-.393 1.7-1.016 2.25" />
                </svg>
                <div class="dailytext1">3</div> 
                <div class="dailytext2">장소</div>               
              </div>
            </div>
            <hr class="hr2"></hr>
            <div class="routedatailslists">
              <div class="selectroutedetailslist">
                <div class="arrowarea">
                  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" class="w-5 h-5">
                    <path fill-rule="evenodd" d="M10 3a.75.75 0 01.75.75v10.638l3.96-4.158a.75.75 0 111.08 1.04l-5.25 5.5a.75.75 0 01-1.08 0l-5.25-5.5a.75.75 0 111.08-1.04l3.96 4.158V3.75A.75.75 0 0110 3z" clip-rule="evenodd" />
                  </svg>                  
                </div>
                <div class="selectdetailsroutelist">
                  <div class="selectdetailscardarea">
                    <div class="selectdetailsimagesarea">
                      <div class="selectdetailsicon"><svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6" width="10" color="white">
                        <path stroke-linecap="round" stroke-linejoin="round" d="M12 21v-8.25M15.75 21v-8.25M8.25 21v-8.25M3 9l9-6 9 6m-1.5 12V10.332A48.36 48.36 0 0012 9.75c-2.551 0-5.056.2-7.5.582V21M3 21h18M12 6.75h.008v.008H12V6.75z"></path>
                      </svg></div>
                      <img class="selectdetailsimage" src="https://www.myro.co.kr/getSpotImage/jeju?no=3728" alt="Image" loading="lazy">
                    </div>
                    
                    <div class="selectrighttextarea">
                      <div class="selectdetailslisttitletextarea">
                        <div class="selectdetailslisttitletext">
                          <div class="selectdetailsmaxarea"></div>
                          <span class="selectdetailstext">교원스위트호텔</span>
                          <span class="selectdetailstextaddress">제주시 </span>
                        </div>
                        <div class="selectdetailstitleicon">
                          <label class="ui-bookmark">
                            <input type="checkbox">
                            <div class="bookmark">
                              <svg viewBox="0 0 32 32">
                                <g>
                                  <path d="M27 4v27a1 1 0 0 1-1.625.781L16 24.281l-9.375 7.5A1 1 0 0 1 5 31V4a4 4 0 0 1 4-4h14a4 4 0 0 1 4 4z"></path>
                                </g>
                              </svg>
                            </div>
                          </label>
                        </div>
                      </div>
                      <div class="selectdetailsdeletearea">
                        <div class="selectdetailsbtn">시간표</div>
                        <div class="selectdetailsbtn2">상세설명</div>
                        <div class="selectdetailsbtn3">삭제</div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              
              <!--여기서부터 하나씩 생성  -->
              <div class="selectroutedetailslist">
                <div class="arrowarea">
                  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" class="w-5 h-5">
                    <path fill-rule="evenodd" d="M10 3a.75.75 0 01.75.75v10.638l3.96-4.158a.75.75 0 111.08 1.04l-5.25 5.5a.75.75 0 01-1.08 0l-5.25-5.5a.75.75 0 111.08-1.04l3.96 4.158V3.75A.75.75 0 0110 3z" clip-rule="evenodd" />
                  </svg>                  
                </div>
                <div class="selectdetailsroutelist">
                  <div class="selectdetailscardarea">
                    <div class="selectdetailsimagesarea">
                      <div class="selectdetailsicon"><svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6" width="10" color="white">
                        <path stroke-linecap="round" stroke-linejoin="round" d="M12 21v-8.25M15.75 21v-8.25M8.25 21v-8.25M3 9l9-6 9 6m-1.5 12V10.332A48.36 48.36 0 0012 9.75c-2.551 0-5.056.2-7.5.582V21M3 21h18M12 6.75h.008v.008H12V6.75z"></path>
                      </svg></div>
                      <img class="selectdetailsimage" src="https://www.myro.co.kr/getSpotImage/jeju?no=3728" alt="Image" loading="lazy">
                    </div>
                    
                    <div class="selectrighttextarea">
                      <div class="selectdetailslisttitletextarea">
                        <div class="selectdetailslisttitletext">
                          <div class="selectdetailsmaxarea"></div>
                          <span class="selectdetailstext">교원스위트호텔</span>
                          <span class="selectdetailstextaddress">제주시 </span>
                        </div>
                        <div class="selectdetailstitleicon">
                          <label class="ui-bookmark">
                            <input type="checkbox">
                            <div class="bookmark">
                              <svg viewBox="0 0 32 32">
                                <g>
                                  <path d="M27 4v27a1 1 0 0 1-1.625.781L16 24.281l-9.375 7.5A1 1 0 0 1 5 31V4a4 4 0 0 1 4-4h14a4 4 0 0 1 4 4z"></path>
                                </g>
                              </svg>
                            </div>
                          </label>
                        </div>
                      </div>
                      <div class="selectdetailsdeletearea">
                        <div class="selectdetailsbtn">시간표</div>
                        <div class="selectdetailsbtn2">상세설명</div>
                        <div class="selectdetailsbtn3">삭제</div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="selectroutedetailslist">
              <div class="arrowarea">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" class="w-5 h-5">
                  <path fill-rule="evenodd" d="M10 3a.75.75 0 01.75.75v10.638l3.96-4.158a.75.75 0 111.08 1.04l-5.25 5.5a.75.75 0 01-1.08 0l-5.25-5.5a.75.75 0 111.08-1.04l3.96 4.158V3.75A.75.75 0 0110 3z" clip-rule="evenodd" />
                </svg>                  
              </div>
              <div class="selectdetailsroutelist">
                <div class="selectdetailscardarea">
                  <div class="selectdetailsimagesarea">
                    <div class="selectdetailsicon"><svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6" width="10" color="white">
                      <path stroke-linecap="round" stroke-linejoin="round" d="M12 21v-8.25M15.75 21v-8.25M8.25 21v-8.25M3 9l9-6 9 6m-1.5 12V10.332A48.36 48.36 0 0012 9.75c-2.551 0-5.056.2-7.5.582V21M3 21h18M12 6.75h.008v.008H12V6.75z"></path>
                    </svg></div>
                    <img class="selectdetailsimage" src="https://www.myro.co.kr/getSpotImage/jeju?no=3728" alt="Image" loading="lazy">
                  </div>
                  
                  <div class="selectrighttextarea">
                    <div class="selectdetailslisttitletextarea">
                      <div class="selectdetailslisttitletext">
                        <div class="selectdetailsmaxarea"></div>
                        <span class="selectdetailstext">교원스위트호텔</span>
                        <span class="selectdetailstextaddress">제주시 </span>
                      </div>
                      <div class="selectdetailstitleicon">
                        <label class="ui-bookmark">
                          <input type="checkbox">
                          <div class="bookmark">
                            <svg viewBox="0 0 32 32">
                              <g>
                                <path d="M27 4v27a1 1 0 0 1-1.625.781L16 24.281l-9.375 7.5A1 1 0 0 1 5 31V4a4 4 0 0 1 4-4h14a4 4 0 0 1 4 4z"></path>
                              </g>
                            </svg>
                          </div>
                        </label>
                      </div>
                    </div>
                    <div class="selectdetailsdeletearea">
                      <div class="selectdetailsbtn">시간표</div>
                      <div class="selectdetailsbtn2">상세설명</div>
                      <div class="selectdetailsbtn3">삭제</div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="selectroutedetailslist">
              <div class="arrowarea">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" class="w-5 h-5">
                  <path fill-rule="evenodd" d="M10 3a.75.75 0 01.75.75v10.638l3.96-4.158a.75.75 0 111.08 1.04l-5.25 5.5a.75.75 0 01-1.08 0l-5.25-5.5a.75.75 0 111.08-1.04l3.96 4.158V3.75A.75.75 0 0110 3z" clip-rule="evenodd" />
                </svg>                  
              </div>
              <div class="selectdetailsroutelist">
                <div class="selectdetailscardarea">
                  <div class="selectdetailsimagesarea">
                    <div class="selectdetailsicon"><svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6" width="10" color="white">
                      <path stroke-linecap="round" stroke-linejoin="round" d="M12 21v-8.25M15.75 21v-8.25M8.25 21v-8.25M3 9l9-6 9 6m-1.5 12V10.332A48.36 48.36 0 0012 9.75c-2.551 0-5.056.2-7.5.582V21M3 21h18M12 6.75h.008v.008H12V6.75z"></path>
                    </svg></div>
                    <img class="selectdetailsimage" src="https://www.myro.co.kr/getSpotImage/jeju?no=3728" alt="Image" loading="lazy">
                  </div>
                  
                  <div class="selectrighttextarea">
                    <div class="selectdetailslisttitletextarea">
                      <div class="selectdetailslisttitletext">
                        <div class="selectdetailsmaxarea"></div>
                        <span class="selectdetailstext">교원스위트호텔</span>
                        <span class="selectdetailstextaddress">제주시 </span>
                      </div>
                      <div class="selectdetailstitleicon">
                        <label class="ui-bookmark">
                          <input type="checkbox">
                          <div class="bookmark">
                            <svg viewBox="0 0 32 32">
                              <g>
                                <path d="M27 4v27a1 1 0 0 1-1.625.781L16 24.281l-9.375 7.5A1 1 0 0 1 5 31V4a4 4 0 0 1 4-4h14a4 4 0 0 1 4 4z"></path>
                              </g>
                            </svg>
                          </div>
                        </label>
                      </div>
                    </div>
                    <div class="selectdetailsdeletearea">
                      <div class="selectdetailsbtn">시간표</div>
                      <div class="selectdetailsbtn2">상세설명</div>
                      <div class="selectdetailsbtn3">삭제</div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!--  -->
            
            <!--  -->
          </div>
        </div>
      <!-- 각 일자별 일정 영역 -->
      

      
     
      <!-- 전체일정리스트 영역 여기까지 -->
      <!-- 전체일정리스트 영역 여기까지 -->
      </div>
      <div class="dailyroutearea">
       
      </div>
    </div>
    <div class="routesearcharea">
        <a href="#"class="routesearchmodalbtn" title="장소를 검색하여 일정에 추가">
          <span>
            <svg viewBox="0 0 24 24" height="17" width="17" xmlns="http://www.w3.org/2000/svg"><path d="M9.145 18.29c-5.042 0-9.145-4.102-9.145-9.145s4.103-9.145 9.145-9.145 9.145 4.103 9.145 9.145-4.102 9.145-9.145 9.145zm0-15.167c-3.321 0-6.022 2.702-6.022 6.022s2.702 6.022 6.022 6.022 6.023-2.702 6.023-6.022-2.702-6.022-6.023-6.022zm9.263 12.443c-.817 1.176-1.852 2.188-3.046 2.981l5.452 5.453 3.014-3.013-5.42-5.421z"></path></svg>
          </span>
        </a>
        <a href="#"class="routesearchmodalbtn" title="장소를 검색하여 일정에 추가">
          <span>
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" class="ionicon" viewBox="0 0 512 512"><circle cx="256" cy="192" r="32"/><path d="M256 32c-88.22 0-160 68.65-160 153 0 40.17 18.31 93.59 54.42 158.78 29 52.34 62.55 99.67 80 123.22a31.75 31.75 0 0051.22 0c17.42-23.55 51-70.88 80-123.22C397.69 278.61 416 225.19 416 185c0-84.35-71.78-153-160-153zm0 224a64 64 0 1164-64 64.07 64.07 0 01-64 64z"/></svg>          </span>
        </a>
    </div>
    <div class="dailyrightarea">
      <span class="dailyrightspan"></span>일차<br>
      <span><svg xmlns="http://www.w3.org/2000/svg" width="14" class="ionicon" viewBox="0 0 512 512"><path d="M256 32C167.67 32 96 96.51 96 176c0 128 160 304 160 304s160-176 160-304c0-79.49-71.67-144-160-144zm0 224a64 64 0 1164-64 64.07 64.07 0 01-64 64z"/></svg></span>
      <span class="dailyrightnumber"></span>
      <span data-langnum="57">개의 장소</span>
    </div>
    <div class="routesearchmodalarea">
      <div class="routesearchareastyle">
        <div class="routesearchareadeletestyle">x</div>
        <div class="routesearchinputarea">
          <div class="routesearchinput">
            <div class="input-container">
              <input class="searchinputtag2" required="" placeholder="장소를 입력해주세요" type="email">
              <button class="invite-btn" type="button">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6">
                  <path fill-rule="evenodd" d="M10.5 3.75a6.75 6.75 0 100 13.5 6.75 6.75 0 000-13.5zM2.25 10.5a8.25 8.25 0 1114.59 5.28l4.69 4.69a.75.75 0 11-1.06 1.06l-4.69-4.69A8.25 8.25 0 012.25 10.5z" clip-rule="evenodd"></path>
                </svg>
              </button>
            </div>
          </div>
        </div>
        <div class="makeroutesearcharea">
          <div class="makeroutesearchtext">
            <div class="makeroutesearchtextstyle">
              <hs style="color: #aaa" data-langnum="61">+ 버튼을 누르면 포함되지 않은 장소에 추가됩니다.</hs>
            </div>
          </div>
          <div class="makeroutesearchlistarea">
								<ul class="makeroutesearchlistul">
									<li class="defaulttext"><span>검색한 관광지가 없습니다.</span>
										<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
  <path stroke-linecap="round" stroke-linejoin="round" d="M12 9v3.75m9-.75a9 9 0 11-18 0 9 9 0 0118 0zm-9 3.75h.008v.008H12v-.008z" />
</svg>
										
									</li>
								</ul>
								<!--  -->
            
          </div>
        </div>
      </div>
    </div>
    <div class="userguidemenuarea">
      <a class="userguidemenu" href="#"><h7>이용방법</h7></a>
      <a class="userguidemenu" href="#"><h7>수정저장</h7></a>
      <a class="userguidemenu" href="#"><h7>수정삭제</h7></a>
      <a class="userguidemenu" href="#" onclick="saveRoute()"><h7>경로저장</h7></a>
    </div>
    <div class="fullscreen">
      <a href="#"class="routesearchmodalbtn2" title="전체화면">
        <span>
          <svg xmlns="http://www.w3.org/2000/svg" height="17" width="17" class="ionicon" viewBox="0 0 512 512"><path d="M336 448h56a56 56 0 0056-56v-56M448 176v-56a56 56 0 00-56-56h-56M176 448h-56a56 56 0 01-56-56v-56M64 176v-56a56 56 0 0156-56h56" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"/></svg>
        </span>
      </a>
    </div>
    <!--  -->
 </div>
</div>
<!-- 클릭했을때 조건에 대한 알려주는 태그 -->
<div class="center-wrapper">
  
</div>
    </section>
     <script src="${path }/js/touris/touris.js"></script>
    <script src="${path }/js/touris/defaulttouris.js"></script>
</body>
</html>