<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<link rel="stylesheet" href="${path }/css/noticeAndCommunity/coStyle.css" />

    <section class="container ">
        
        <div class="card-box">
            <div class="card" style="width: 40rem;">
                <div class="d-flex">
                    <a><img src="${path }/images/community/free-icon-profile-6522516.png" style="height: 30px;"></a>&nbsp;
                    <p>닉네임</p>
                </div>
                <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="true">
                    <div class="carousel-indicators">
                      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
                      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
                    </div>
                    <div class="carousel-inner">
                      <div class="carousel-item active">
                        <img src="${path }/images/community/city-ged7162395_1280.jpg" class="d-block w-100" alt="..." style="height:auto">
                      </div>
                      <div class="carousel-item">
                        <img src="${path }/images/community/bicycle-g3fc35f2a5_1280.jpg" class="d-block w-100" alt="...">
                      </div>
                      <div class="carousel-item">
                        <img src="${path }/images/community/santorini-g5acd9dd2e_1280.jpg" class="d-block w-100" alt="...">
                      </div>
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
                <div class="card-body">
                    <h5 class="card-title">추천 관광지</h5>
                    <p class="card-text">세심하고 유기적인 접근을 통해 현대 사회에서 건축이 어떻게 진화할 수 있는지에 대한 선례를 만들어왔다는 점을 주목해 신사옥 건축 설계자로 헤르조그&드 뫼롱을 선정했으며, 
                        그들의 국내 첫 프로젝트이자 동시대 미술을 조명하는 문화의 중심지로 서울의 새롭고 중요한 랜드마크로 자리매김 한다. </p>
                </div>


                <div class="like-content">
                    
                    <img src="${path }/images/community/heart.svg">
                    <span>1</span>
                    <button class="btn-secondary like-review" value="false">
                      <i class="fa fa-heart" aria-hidden="true"></i> Like
                    </button>
                    
                  </div>
                <div class="card-body comment-body">
                    
                    <a class="w-btn-outline w-btn-blue-outline" data-title="댓글달기" data-bs-toggle="collapse" href="#inputComment" role="button" aria-expanded="false" aria-controls="collapseComment">
                        댓글쓰기
                    </a>

                      
                    
                </div>
            </div>
            <div class="collapse" id="inputComment">
                <div class="card card-body">
                  <input type="text" id="comment_input"><button class="w-btn w-btn-blue">등록</button>
                </div>
              </div>
       
        <div class="comments  p-2 border rounded">
            <div class="comment">
                <div class="comment_detail">
                    <div class="comment_user"><span>user01</span></div>
                    <div class="comment_body">
                        <span>좋아요</span>
                    </div>
                    
                </div>
            </div>

        </div>
        </div>
    </section>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>


<script>

    
    $(".like-review").click( function(e) {
        if($(".like-review").val()=="false"){
            $(e.target).html('<i class="fa fa-heart" aria-hidden="true"></i> You liked this');
            $(e.target).children('.fa-heart').addClass('animate-like');
            $(".like-review").val("true");
        }else{
            $(e.target).html('<i class="fa fa-heart" aria-hidden="true"></i>Like');
            $(e.target).children('.fa-heart').addClass('animate-like');
            $(".like-review").val("false");
        }
    });

</script>
</html>