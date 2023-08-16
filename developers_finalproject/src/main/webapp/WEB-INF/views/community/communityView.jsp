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
		<div class="card reply-append" style="width: 40rem;">
			<div class="d-flex">
				<c:choose>
					<c:when test="${not empty comuView.memberId.memberImage }">
						<a><img src="${comuView.memberId.memberImage}"
							style="height: 30px;border-radius:50%; margin:5px;"></a>&nbsp;
                 </c:when>
					<c:otherwise>
						<a><img
							src="${path }/images/community/free-icon-profile-6522516.png"
							style="height: 30px;"></a>&nbsp;
                 </c:otherwise>
				</c:choose>
				<div style='margin-top:10px;'>${comuView.memberId.memberNickname }</div>
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
				<input type="hidden" value="${comuView.communityNo }" id="like_comuNo">
				<input type="hidden" value="${loginMember.memberId }" id="like_memberId">
				<input type="hidden" value="${likeCheck }" id="like_check">
				<input type="hidden" value="${loginAdmin }" id="adminCheck"/>
				
				<h5 class="card-title">${comuView.communityTitle }</h5>
				<p class="card-text">${comuView.communityContent }</p>
			</div>


			<div class="like-content">

				<img src="${path }/images/community/heart.svg"> <span>${comuView.likeCount }</span>
				<c:if test="${loginMember!=null || loginAdmin!=null}">
					<button class="btn-secondary like-review" value="false">
						<i class="fa fa-heart" aria-hidden="true"></i> Like
					</button>
				</c:if>

			</div>
			<div class="card-body comment-body">
			<c:if test="${loginMember!=null || loginAdmin!=null}">
				<a class="w-btn-outline w-btn-blue-outline" data-title="댓글달기"
					data-bs-toggle="collapse" href="#inputComment" role="button"
					aria-expanded="false" aria-controls="collapseComment"> 댓글쓰기 </a>
			</c:if>
			<c:if test="${loginMember.memberId == comuView.memberId.memberId}">
				<button class="s-btn ms-2" onclick="location.href='${path}/community/updateCommunity.do?no=${comuView.communityNo }'">수정</button>
			</c:if>	
				<c:if test="${loginMember.memberId == comuView.memberId.memberId||loginAdmin!=null }">
				<button class="s-btn ms-2" onclick="deleteCommunity(${comuView.communityNo });">삭제</button>
				</c:if>
			
				<button class="s-btn ms-2" onclick="location.href='${path}/community/communityList.do'" >목록</button>
			</div>
		</div>
		<div class="collapse" id="inputComment">
			<div class="card card-body">
				<input type="text" id="comment_input">
				<button class="w-btn w-btn-blue" onclick="insertReply();">등록</button>
			</div>
		</div>

		<div class="p-2 reply-list"></div>

		
		
	</div>
	
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
<script src="${path }/js/community/community.js"></script>
