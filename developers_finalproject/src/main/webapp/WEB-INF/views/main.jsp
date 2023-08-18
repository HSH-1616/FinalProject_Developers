<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<c:set var="path" value="${pageContext.request.contextPath }" />
<link rel="stylesheet" href="${path }/css/main/main.css" />
 <script src="${path }js/main/main.js"></script>
<section>
      <div id="blurSection"></div>
      <div id="banner">
        <div id="bannerText">
          <div>
            <h1>여행은<br /><span>살아보는거야</span></h1>
          </div>
          <div>
            <h2>
              <span>DEVELOPERS</span><br />
              FINAL PROJECT
            </h2>
          </div>
        </div>
        <div id="bannerRight"></div>
        <div id="bannerImage">
          <div
            id="carouselExampleFade"
            class="carousel slide carousel-fade"
            data-bs-ride="carousel"
          >
            <div class="carousel-inner">
              <div class="carousel-item active" data-bs-interval="4000">
                <img
                  src="${path }/images/main/MainBanner1.jpg"
                  class="d-block w-100"
                  alt="..."
                />
              </div>
              <div class="carousel-item">
                <img
                  src="${path }/images/main/MainBanner2.jpg"
                  class="d-block w-100"
                  alt="..."
                />
              </div>
              <div class="carousel-item">
                <img
                  src="${path }/images/main/MainBanner3.jpg"
                  class="d-block w-100"
                  alt="..."
                />
              </div>
              <div class="carousel-item">
                <img
                  src="${path }/images/main/MainBanner4.jpg"
                  class="d-block w-100"
                  alt="..."
                />
              </div>
            </div>
          </div>
        </div>
      </div>
      <div id="community">
        <div id="communityTitle">
          <h4>여행갤러리</h4>
          <h1>소중한 기억을</h1>
          <h1>함께 공유해보세요</h1>
        </div>
        <div id="memoryImg">
          <div>
            <img src="${path }/images/main/memory1.jpg" />
          </div>
          <div>
            <img src="${path }/images/main/memory2.jpg" />
          </div>
          <div>
            <img src="${path }/images/main/memory3.jpg" />
          </div>
        </div>
        <div id="communityImage">
        
        
        <%--   <div class="card">
            <div class="card-image">
              <a><img src="${path }/images/main/travel.jpg" /></a>
            </div>
            <div class="category">#기차여행 #벛꽃</div>
            <div class="heading">
              기차여행
              <div class="author">
                By <span class="name">작성자</span> 2023-07-14
              </div>
            </div>
          </div>
          <div class="card">
            <div class="card-image">
              <a><img src="${path }/images/main/travel.jpg" /></a>
            </div>
            <div class="category">Illustration</div>
            <div class="heading">
              A heading that must span over two lines
              <div class="author">
                By <span class="name">Abi</span> 4 days ago
              </div>
            </div>
          </div>
          <div class="card">
            <div class="card-image">
              <a><img src="${path }/images/main/travel.jpg" /></a>
            </div>
            <div class="category">Illustration</div>
            <div class="heading">
              A heading that must span over two lines
              <div class="author">
                By <span class="name">Abi</span> 4 days ago
              </div>
            </div>
          </div>
          <div class="card">
            <div class="card-image">
              <a><img src="${path }/images/main/travel.jpg" /></a>
            </div>
            <div class="category">Illustration</div>
            <div class="heading">
              A heading that must span over two lines
              <div class="author">
                By <span class="name">Abi</span> 4 days ago
              </div>
            </div>
          </div> --%>
          
          
        </div>
      </div>

      <div id="bannerTitle">
        <h2>SERVICE</h2>
      </div>
      <div id="travelBanner">
        <div class="blurImage"></div>
        <h2
          data-aos="fade-right"
          data-aos-easing="ease-out-cubic"
          data-aos-duration="2000"
        >
          여행이 떠나고 싶을때<br />관광지,맛집 찾아보기 <br /><br />
        </h2>
        <button onclick="location.href='${path}/touris/tourismainpage'"
          class="travelBtn"
          data-aos="zoom-in"
          data-aos-easing="ease-out-cubic"
          data-aos-duration="2000"
        >
          바로가기
        </button>
      </div>
      <div id="hotelBanner">
        <div class="blurImage"></div>
        <button
          class="travelBtn"
          data-aos="zoom-in"
          data-aos-easing="ease-out-cubic"
          data-aos-duration="2000"
        >
          바로가기
        </button>
        <h2
          data-aos="fade-left"
          data-aos-easing="ease-out-cubic"
          data-aos-duration="2000"
        >
          누구나 마음편히 쉴수있게<br />숙박업체 찾아보기 <br /><br />
        </h2>
      </div>
      <div id="rootBanner">
        <div class="blurImage"></div>

        <h2
          data-aos="fade-right"
          data-aos-easing="ease-out-cubic"
          data-aos-duration="2000"
        >
          여행일정을 계획적으로<br />여행루트 만들어보기 <br /><br />
        </h2>
        <button
          class="travelBtn"
          data-aos="zoom-in"
          data-aos-easing="ease-out-cubic"
          data-aos-duration="2000"
        >
          바로가기
        </button>
      </div>
      <div id="travelMemory">
        <div
          id="memoryText"
          data-aos="zoom-in-left"
          data-aos-easing="ease-out-cubic"
          data-aos-duration="2000"
        >
          <h1>TO <span> TRAVEL</span> <br />TO <span> LIVE</span></h1>
          <div>
            <span>여행은 사람을 순수하게 그러나 강하게 만든다.</span>
            <br />
            <span>우리함께한 시간이 모두 여행이었음을...</span>
          </div>
        </div>
      </div>
    </section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
