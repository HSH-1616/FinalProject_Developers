<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<link rel="stylesheet" href="${path }/css/noticeAndCommunity/coStyle.css" />
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<section>
   
   
    <div class="text-end"><button class="w-btn w-btn-red" onclick="location.href='${path }/community/communityWritePage.do'">글쓰기</button></div>
    
    <div class="row row-cols-1 row-cols-md-3 mx-5 g-4 p-2" >
    
        <div class="col">
        
          <div class="card" onclick="location.assign('${path}/community/communityView.do')">
            <div class="d-flex">
              <a><img src="${path }/images/community/free-icon-profile-6522516.png" style="height: 30px;"></a>&nbsp;
              <p>닉네임</p>
             
          </div>
            <img src="${path }/images/community/bicycle-g3fc35f2a5_1280.jpg" class="card-img-top" alt="...">
            <div class="card-body">
              <h5 class="card-title">Card title</h5>
              <div class="text-end ">
                
                <div>
                  <img src="${path }/images/community/heart.svg" style="height: 20px; width:auto">
                10
                </div>
                
                </div>
              <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
            </div>
          </div>
        </div>
        
        
        
        
        <div class="col">
          <div class="card">
            <div class="d-flex">
              <a><img src="${path }/images/community/free-icon-profile-6522516.png" style="height: 30px;"></a>&nbsp;
              <p>닉네임</p>
             
          </div>
            <img src="${path }/images/community/buildings-g1fe0fbef3_1280.jpg" class="card-img-top" alt="...">
            <div class="card-body">
              <h5 class="card-title">Card title</h5>
              <div class="text-end ">
                
                <div>
                  <img src="${path }/images/community/heart.svg" style="height: 20px; width:auto">
                10
                </div>
                
                </div>
              <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
            </div>
            <div>
            
            </div>
          </div>
        </div>
        <div class="col">
          <div class="card">
            <div class="d-flex">
              <a><img src="${path }/images/community/free-icon-profile-6522516.png" style="height: 30px;"></a>&nbsp;
              <p>닉네임</p>
             
          </div>
            <img src="${path }/images/community/santorini-g5acd9dd2e_1280.jpg" class="card-img-top" alt="...">
            <div class="card-body">
              <h5 class="card-title">Card title</h5>
              <div class="text-end ">
                
                <div>
                  <img src="${path }/images/community/heart.svg" style="height: 20px; width:auto">
                10
                </div>
                
                </div>
              <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content.</p>
            </div>
          </div>
        </div>
        <div class="col">
          <div class="card">
            <div class="d-flex">
              <a><img src="${path }/images/community/free-icon-profile-6522516.png" style="height: 30px;"></a>&nbsp;
              <p>닉네임</p>
             
          </div>
            <img src="${path }/images/community/city-ged7162395_1280.jpg" class="card-img-top" alt="...">
            <div class="card-body">
              <h5 class="card-title">Card title</h5>
              <div class="text-end ">
                
                <div>
                  <img src="${path }/images/community/heart.svg" style="height: 20px; width:auto">
                10
                </div>
                
                </div>
              <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
            </div>
          </div>
        </div>
      </div>
      
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>