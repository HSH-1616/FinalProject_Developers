<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<link rel="stylesheet" href="${path }/css/mypage/mypage.css" />
<!-- <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css" /> -->
<link rel="stylesheet" href="${path}/css/mypage/slick.css"/>
<link rel="stylesheet" href="${path}/css/mypage/slick-theme.css"/>
<script src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script> 
<section>
	<div class="mypageheader">
		<div class="mypageheaderarea">
			<div class="mypageprofile">
				<div class="profile">
					<span class="boxsize"> <img alt=""
						src="https://www.myro.co.kr/assets/images/user-avatar.png">
					</span>
				</div>
				<h2 class="nickname">user5168649250</h2>
				<div class="profilemangement">
					<a class="profileatag">프로필 관리</a>
					<svg xmlns="http://www.w3.org/2000/svg" fill="none"
						viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor"
						class="w-6 h-6">
  					<path stroke-linecap="round" stroke-linejoin="round"
							d="M9.594 3.94c.09-.542.56-.94 1.11-.94h2.593c.55 0 1.02.398 1.11.94l.213 1.281c.063.374.313.686.645.87.074.04.147.083.22.127.324.196.72.257 1.075.124l1.217-.456a1.125 1.125 0 011.37.49l1.296 2.247a1.125 1.125 0 01-.26 1.431l-1.003.827c-.293.24-.438.613-.431.992a6.759 6.759 0 010 .255c-.007.378.138.75.43.99l1.005.828c.424.35.534.954.26 1.43l-1.298 2.247a1.125 1.125 0 01-1.369.491l-1.217-.456c-.355-.133-.75-.072-1.076.124a6.57 6.57 0 01-.22.128c-.331.183-.581.495-.644.869l-.213 1.28c-.09.543-.56.941-1.11.941h-2.594c-.55 0-1.02-.398-1.11-.94l-.213-1.281c-.062-.374-.312-.686-.644-.87a6.52 6.52 0 01-.22-.127c-.325-.196-.72-.257-1.076-.124l-1.217.456a1.125 1.125 0 01-1.369-.49l-1.297-2.247a1.125 1.125 0 01.26-1.431l1.004-.827c.292-.24.437-.613.43-.992a6.932 6.932 0 010-.255c.007-.378-.138-.75-.43-.99l-1.004-.828a1.125 1.125 0 01-.26-1.43l1.297-2.247a1.125 1.125 0 011.37-.491l1.216.456c.356.133.751.072 1.076-.124.072-.044.146-.087.22-.128.332-.183.582-.495.644-.869l.214-1.281z" />
  					<path stroke-linecap="round" stroke-linejoin="round"
							d="M15 12a3 3 0 11-6 0 3 3 0 016 0z" />
				</svg>
				</div>
			</div>
			<div class="selectarea">
			<div class="selectcontent"></div>
			
			<div class="selectbtnarea">
				<div class="container">
  <div class="radio-tile-group">
    <div class="input-container">
      <input id="walk" class="radio-button" type="radio" name="radio">
      <div class="radio-tile">
        <div class="icon walk-icon">
          <svg fill="#000000" height="24" viewBox="0 0 24 24" width="24" xmlns="http://www.w3.org/2000/svg">
            <path d="M0 0h24v24H0z" fill="none"></path>
            <path d="M13.5 5.5c1.1 0 2-.9 2-2s-.9-2-2-2-2 .9-2 2 .9 2 2 2zM9.8 8.9L7 23h2.1l1.8-8 2.1 2v6h2v-7.5l-2.1-2 .6-3C14.8 12 16.8 13 19 13v-2c-1.9 0-3.5-1-4.3-2.4l-1-1.6c-.4-.6-1-1-1.7-1-.3 0-.5.1-.8.1L6 8.3V13h2V9.6l1.8-.7"></path>
          </svg>
        </div>
        <label for="walk" class="radio-tile-label">찜목록</label>
      </div>
    </div>

    <div class="input-container">
      <input id="bike" class="radio-button" type="radio" name="radio">
      <div class="radio-tile">
        <div class="icon bike-icon">
          <svg fill="#000000" height="24" viewBox="0 0 24 24" width="24" xmlns="http://www.w3.org/2000/svg">
            <path d="M0 0h24v24H0z" fill="none"></path>
            <path d="M15.5 5.5c1.1 0 2-.9 2-2s-.9-2-2-2-2 .9-2 2 .9 2 2 2zM5 12c-2.8 0-5 2.2-5 5s2.2 5 5 5 5-2.2 5-5-2.2-5-5-5zm0 8.5c-1.9 0-3.5-1.6-3.5-3.5s1.6-3.5 3.5-3.5 3.5 1.6 3.5 3.5-1.6 3.5-3.5 3.5zm5.8-10l2.4-2.4.8.8c1.3 1.3 3 2.1 5.1 2.1V9c-1.5 0-2.7-.6-3.6-1.5l-1.9-1.9c-.5-.4-1-.6-1.6-.6s-1.1.2-1.4.6L7.8 8.4c-.4.4-.6.9-.6 1.4 0 .6.2 1.1.6 1.4L11 14v5h2v-6.2l-2.2-2.3zM19 12c-2.8 0-5 2.2-5 5s2.2 5 5 5 5-2.2 5-5-2.2-5-5-5zm0 8.5c-1.9 0-3.5-1.6-3.5-3.5s1.6-3.5 3.5-3.5 3.5 1.6 3.5 3.5-1.6 3.5-3.5 3.5z"></path>
          </svg>
        </div>
        <label for="bike" class="radio-tile-label">여행일정</label>
      </div>
    </div>
<div class="input-container">
      <input id="walk" class="radio-button" type="radio" name="radio">
      <div class="radio-tile">
        <div class="icon walk-icon">
          <svg fill="#000000" height="24" viewBox="0 0 24 24" width="24" xmlns="http://www.w3.org/2000/svg">
            <path d="M0 0h24v24H0z" fill="none"></path>
            <path d="M13.5 5.5c1.1 0 2-.9 2-2s-.9-2-2-2-2 .9-2 2 .9 2 2 2zM9.8 8.9L7 23h2.1l1.8-8 2.1 2v6h2v-7.5l-2.1-2 .6-3C14.8 12 16.8 13 19 13v-2c-1.9 0-3.5-1-4.3-2.4l-1-1.6c-.4-.6-1-1-1.7-1-.3 0-.5.1-.8.1L6 8.3V13h2V9.6l1.8-.7"></path>
          </svg>
        </div>
        <label for="walk" class="radio-tile-label">여행일정</label>
      </div>
    </div>
    <div class="input-container">
      <input id="drive" class="radio-button" type="radio" name="radio">
      <div class="radio-tile">
        <div class="icon car-icon">
          <svg fill="#000000" height="24" viewBox="0 0 24 24" width="24" xmlns="http://www.w3.org/2000/svg">
            <path d="M18.92 6.01C18.72 5.42 18.16 5 17.5 5h-11c-.66 0-1.21.42-1.42 1.01L3 12v8c0 .55.45 1 1 1h1c.55 0 1-.45 1-1v-1h12v1c0 .55.45 1 1 1h1c.55 0 1-.45 1-1v-8l-2.08-5.99zM6.5 16c-.83 0-1.5-.67-1.5-1.5S5.67 13 6.5 13s1.5.67 1.5 1.5S7.33 16 6.5 16zm11 0c-.83 0-1.5-.67-1.5-1.5s.67-1.5 1.5-1.5 1.5.67 1.5 1.5-.67 1.5-1.5 1.5zM5 11l1.5-4.5h11L19 11H5z"></path>
            <path d="M0 0h24v24H0z" fill="none"></path>
          </svg>
        </div>
        <label for="drive" class="radio-tile-label">숙박예약</label>
      </div>
    </div>
  </div>
</div>
				
			</div>
			</div>
		</div>
	</div>
	<div class="selectentire">
			<!-- 나의 맛집 목록 -->
			<div class="lovefoodlistarea">
				<div class="lovefoodtitle">
					<h2>나의 찜 목록</h2>
				</div>
			<div class="input">
				<button class="value">
					<svg viewBox="0 0 16 16" xmlns="http://www.w3.org/2000/svg"
						data-name="Layer 2">
						<path fill="#7D8590"
							d="m1.5 13v1a.5.5 0 0 0 .3379.4731 18.9718 18.9718 0 0 0 6.1621 1.0269 18.9629 18.9629 0 0 0 6.1621-1.0269.5.5 0 0 0 .3379-.4731v-1a6.5083 6.5083 0 0 0 -4.461-6.1676 3.5 3.5 0 1 0 -4.078 0 6.5083 6.5083 0 0 0 -4.461 6.1676zm4-9a2.5 2.5 0 1 1 2.5 2.5 2.5026 2.5026 0 0 1 -2.5-2.5zm2.5 3.5a5.5066 5.5066 0 0 1 5.5 5.5v.6392a18.08 18.08 0 0 1 -11 0v-.6392a5.5066 5.5066 0 0 1 5.5-5.5z"></path></svg>
					관심맛집
				</button>
				<button class="value">
					<svg id="Line" viewBox="0 0 32 32"
						xmlns="http://www.w3.org/2000/svg">
						<path fill="#7D8590" id="XMLID_1646_"
							d="m17.074 30h-2.148c-1.038 0-1.914-.811-1.994-1.846l-.125-1.635c-.687-.208-1.351-.484-1.985-.824l-1.246 1.067c-.788.677-1.98.631-2.715-.104l-1.52-1.52c-.734-.734-.78-1.927-.104-2.715l1.067-1.246c-.34-.635-.616-1.299-.824-1.985l-1.634-.125c-1.035-.079-1.846-.955-1.846-1.993v-2.148c0-1.038.811-1.914 1.846-1.994l1.635-.125c.208-.687.484-1.351.824-1.985l-1.068-1.247c-.676-.788-.631-1.98.104-2.715l1.52-1.52c.734-.734 1.927-.779 2.715-.104l1.246 1.067c.635-.34 1.299-.616 1.985-.824l.125-1.634c.08-1.034.956-1.845 1.994-1.845h2.148c1.038 0 1.914.811 1.994 1.846l.125 1.635c.687.208 1.351.484 1.985.824l1.246-1.067c.787-.676 1.98-.631 2.715.104l1.52 1.52c.734.734.78 1.927.104 2.715l-1.067 1.246c.34.635.616 1.299.824 1.985l1.634.125c1.035.079 1.846.955 1.846 1.993v2.148c0 1.038-.811 1.914-1.846 1.994l-1.635.125c-.208.687-.484 1.351-.824 1.985l1.067 1.246c.677.788.631 1.98-.104 2.715l-1.52 1.52c-.734.734-1.928.78-2.715.104l-1.246-1.067c-.635.34-1.299.616-1.985.824l-.125 1.634c-.079 1.035-.955 1.846-1.993 1.846zm-5.835-6.373c.848.53 1.768.912 2.734 1.135.426.099.739.462.772.898l.18 2.341 2.149-.001.18-2.34c.033-.437.347-.8.772-.898.967-.223 1.887-.604 2.734-1.135.371-.232.849-.197 1.181.089l1.784 1.529 1.52-1.52-1.529-1.784c-.285-.332-.321-.811-.089-1.181.53-.848.912-1.768 1.135-2.734.099-.426.462-.739.898-.772l2.341-.18h-.001v-2.148l-2.34-.18c-.437-.033-.8-.347-.898-.772-.223-.967-.604-1.887-1.135-2.734-.232-.37-.196-.849.089-1.181l1.529-1.784-1.52-1.52-1.784 1.529c-.332.286-.81.321-1.181.089-.848-.53-1.768-.912-2.734-1.135-.426-.099-.739-.462-.772-.898l-.18-2.341-2.148.001-.18 2.34c-.033.437-.347.8-.772.898-.967.223-1.887.604-2.734 1.135-.37.232-.849.197-1.181-.089l-1.785-1.529-1.52 1.52 1.529 1.784c.285.332.321.811.089 1.181-.53.848-.912 1.768-1.135 2.734-.099.426-.462.739-.898.772l-2.341.18.002 2.148 2.34.18c.437.033.8.347.898.772.223.967.604 1.887 1.135 2.734.232.37.196.849-.089 1.181l-1.529 1.784 1.52 1.52 1.784-1.529c.332-.287.813-.32 1.18-.089z"></path>
						<path id="XMLID_1645_" fill="#7D8590"
							d="m16 23c-3.859 0-7-3.141-7-7s3.141-7 7-7 7 3.141 7 7-3.141 7-7 7zm0-12c-2.757 0-5 2.243-5 5s2.243 5 5 5 5-2.243 5-5-2.243-5-5-5z"></path></svg>
					관심관광지
				</button>
				<button class="value">
					<svg viewBox="0 0 128 128" xmlns="http://www.w3.org/2000/svg">
						<path fill="#7D8590"
							d="m109.9 20.63a6.232 6.232 0 0 0 -8.588-.22l-57.463 51.843c-.012.011-.02.024-.031.035s-.023.017-.034.027l-4.721 4.722a1.749 1.749 0 0 0 0 2.475l.341.342-3.16 3.16a8 8 0 0 0 -1.424 1.967 11.382 11.382 0 0 0 -12.055 10.609c-.006.036-.011.074-.015.111a5.763 5.763 0 0 1 -4.928 5.41 1.75 1.75 0 0 0 -.844 3.14c4.844 3.619 9.4 4.915 13.338 4.915a17.14 17.14 0 0 0 11.738-4.545l.182-.167a11.354 11.354 0 0 0 3.348-8.081c0-.225-.02-.445-.032-.667a8.041 8.041 0 0 0 1.962-1.421l3.16-3.161.342.342a1.749 1.749 0 0 0 2.475 0l4.722-4.722c.011-.011.018-.025.029-.036s.023-.018.033-.029l51.844-57.46a6.236 6.236 0 0 0 -.219-8.589zm-70.1 81.311-.122.111c-.808.787-7.667 6.974-17.826 1.221a9.166 9.166 0 0 0 4.36-7.036 1.758 1.758 0 0 0 .036-.273 7.892 7.892 0 0 1 9.122-7.414c.017.005.031.014.048.019a1.717 1.717 0 0 0 .379.055 7.918 7.918 0 0 1 4 13.317zm5.239-10.131c-.093.093-.194.176-.293.26a11.459 11.459 0 0 0 -6.289-6.286c.084-.1.167-.2.261-.3l3.161-3.161 6.321 6.326zm7.214-4.057-9.479-9.479 2.247-2.247 9.479 9.479zm55.267-60.879-50.61 56.092-9.348-9.348 56.092-50.61a2.737 2.737 0 0 1 3.866 3.866z"></path></svg>
					관심숙박업소
				</button>
			</div>
			<div class="favoritselect">
				<div class="favoriteselectarea">
					<div class="favorite">
						<div class="cardarea">
								<div class="cardimgarea">
									<div class="cardimgstyle">
										<span class="cardspanimg">
											<img alt="" src="https://cdn.myro.co.kr/prod/image/city/Seoul.jpg">
										</span>
									</div>
								</div>
								<div class="cardtextarea">
									<div class="noneid"></div>
									<h2 class="cardtexteng">SEOUL</h2>
									<h2 class="cardtextkr">서울</h2>
								</div>
							</div>
							<!--  -->
							<div class="cardarea">
								<div class="cardimgarea">
									<div class="cardimgstyle">
										<span class="cardspanimg">
											<img alt="" src="https://cdn.myro.co.kr/prod/image/city/Seoul.jpg">
										</span>
									</div>
								</div>
								<div class="cardtextarea">
									<div class="noneid"></div>
									<h2 class="cardtexteng">SEOUL</h2>
									<h2 class="cardtextkr">서울</h2>
								</div>
							</div>
							<div class="cardarea">
								<div class="cardimgarea">
									<div class="cardimgstyle">
										<span class="cardspanimg">
											<img alt="" src="https://cdn.myro.co.kr/prod/image/city/Seoul.jpg">
										</span>
									</div>
								</div>
								<div class="cardtextarea">
									<div class="noneid"></div>
									<h2 class="cardtexteng">SEOUL</h2>
									<h2 class="cardtextkr">서울</h2>
								</div>
							</div>
							<div class="cardarea">
								<div class="cardimgarea">
									<div class="cardimgstyle">
										<span class="cardspanimg">
											<img alt="" src="https://cdn.myro.co.kr/prod/image/city/Seoul.jpg">
										</span>
									</div>
								</div>
								<div class="cardtextarea">
									<div class="noneid"></div>
									<h2 class="cardtexteng">SEOUL</h2>
									<h2 class="cardtextkr">서울</h2>
								</div>
							</div>
							<div class="cardarea">
								<div class="cardimgarea">
									<div class="cardimgstyle">
										<span class="cardspanimg">
											<img alt="" src="https://cdn.myro.co.kr/prod/image/city/Seoul.jpg">
										</span>
									</div>
								</div>
								<div class="cardtextarea">
									<div class="noneid"></div>
									<h2 class="cardtexteng">SEOUL</h2>
									<h2 class="cardtextkr">서울</h2>
								</div>
							</div>
							<div class="cardarea">
								<div class="cardimgarea">
									<div class="cardimgstyle">
										<span class="cardspanimg">
											<img alt="" src="https://cdn.myro.co.kr/prod/image/city/Seoul.jpg">
										</span>
									</div>
								</div>
								<div class="cardtextarea">
									<div class="noneid"></div>
									<h2 class="cardtexteng">SEOUL</h2>
									<h2 class="cardtextkr">서울</h2>
								</div>
							</div>
							<div class="cardarea">
								<div class="cardimgarea">
									<div class="cardimgstyle">
										<span class="cardspanimg">
											<img alt="" src="https://cdn.myro.co.kr/prod/image/city/Seoul.jpg">
										</span>
									</div>
								</div>
								<div class="cardtextarea">
									<div class="noneid"></div>
									<h2 class="cardtexteng">SEOUL</h2>
									<h2 class="cardtextkr">서울</h2>
								</div>
							</div>
					</div>
				</div>
			</div>
		</div>
			<!-- 나의 일정 -->
		<div class="myschedulearea">
			<h2 class="myschedultext">나의 여행 일정</h2>
			<div class="myschedulcardarea">
				<div class="myschedulcard">
					<div class="myschedulcontent">
						<div class="myschedulcontentarea">
							<div class="imgbox">
								<div class="imgwidth">
									<span> <img alt=""
										src="https://cdn.myro.co.kr/prod/image/city/Jeju.jpg">
									</span>
								</div>
							</div>
							<div class="titlecontentarea">
								<div class="titlecontentstyle">
									<div class="contenttitle">
										<div class="selecttitle">
											<div class="circleday">
												<p class="cirledaytext">D+12</p>
											</div>
											<div class="cirletitle">제주</div>
										</div>
									</div>
									<div class="inputarea">
										<h1 class="inputh1"></h1>
										<div class="inputjustify-center ">
											<button class="inputbtn">
												<div class="inputtext">여행 이름 입력</div>
											</button>
										</div>
									</div>
									<div class="daycontentarea">
										<div class="daycontenth2area">
											<h2 class="daytext">2023-07-29~2023-08-07</h2>
											<h2 class="ml-1 text-xs md:ml-0 md:text-sm font-Montserrat md:mt-1"></h2>
										</div>
										<h2 class="updateday">최근수정일 2023-08-10</h2>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 나의 관광지 목록 -->
			<div class=""></div>
			<!-- 숙박예약내역 -->
			<div class=""></div>
			<!--  -->
		
	</div>
	<script src="${path }/js/mypage/mypage.js"></script>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>