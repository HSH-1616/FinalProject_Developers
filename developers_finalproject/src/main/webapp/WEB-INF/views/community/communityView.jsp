<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<link rel="stylesheet"
	href="${path }/css/noticeAndCommunity/coStyle.css" />

<section class="communityView-container ">

	<div class="card-box">
		<div class="card" style="width: 40rem;">
			<div class="d-flex">
				<c:choose>
					<c:when test="${not empty comuView.memberId.memberImage }">
						<a><img src="${comuView.memberId.memberImage}"
							style="height: 30px;"></a>&nbsp;
                 </c:when>
					<c:otherwise>
						<a><img
							src="${path }/images/community/free-icon-profile-6522516.png"
							style="height: 30px;"></a>&nbsp;
                 </c:otherwise>
				</c:choose>
				<p>${comuView.memberId.memberNickname }</p>
			</div>
			<div id="carouselExampleIndicators" class="carousel slide"
				data-bs-ride="true">
				<div class="carousel-indicators">
					<c:forEach var="list" items="${comuView.files }" varStatus="status">
						<c:if test="${status.index >0 }">
							
								<button type="button"
									data-bs-target="#carouselExampleIndicators"
									data-bs-slide-to="${status.index }" ></button>
							</c:if>
							<c:if test="${status.index==0}">
								<button type="button"
									data-bs-target="#carouselExampleIndicators"
									data-bs-slide-to="${status.index }" class="active" aria-current="true"
									></button>
							
						</c:if>

						<!-- 
                      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button> -->
					</c:forEach>
				</div>

				<div class="carousel-inner">
					<c:forEach var="f" items="${comuView.files }" varStatus="status">
						<c:if test="${status.index == 0 }">
								<div class="carousel-item active">
									<img src="${path }/upload/community/${f.fileName}"
										class="d-block w-100" alt="..." style="height: auto">
								</div>
						</c:if>
							<c:if test="${status.index >0 }">
								<div class="carousel-item">
									<img src="${path }/upload/community/${f.fileName}"
										class="d-block w-100" alt="...">
								</div>
								</c:if>
								
							
								
							
					</c:forEach>
				</div>
				<button class="carousel-control-prev" type="button"
					data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Previous</span>
				</button>
				<button class="carousel-control-next" type="button"
					data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Next</span>
				</button>
			</div>
			<div class="card-body">
				<h5 class="card-title">${comuView.communityTitle }</h5>
				<p class="card-text">${comuView.communityContent }</p>
			</div>


			<div class="like-content">

				<img src="${path }/images/community/heart.svg"> <span>${comuView.likeCount }</span>
				<button class="btn-secondary like-review" value="false">
					<i class="fa fa-heart" aria-hidden="true"></i> Like
				</button>

			</div>
			<div class="card-body comment-body">

				<a class="w-btn-outline w-btn-blue-outline" data-title="댓글달기"
					data-bs-toggle="collapse" href="#inputComment" role="button"
					aria-expanded="false" aria-controls="collapseComment"> 댓글쓰기 </a>



			</div>
		</div>
		<div class="collapse" id="inputComment">
			<div class="card card-body">
				<input type="text" id="comment_input">
				<button class="w-btn w-btn-blue">등록</button>
			</div>
		</div>

		<div class="comments  p-2 border rounded">
			<div class="comment">
				<div class="comment_detail">
					<div class="comment_user">
						<span>user01</span>
					</div>
					<div class="comment_body">
						<span>좋아요</span>
					</div>

				</div>
			</div>

		</div>
	</div>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
<script src="${path }/js/community/community.js"></script>
