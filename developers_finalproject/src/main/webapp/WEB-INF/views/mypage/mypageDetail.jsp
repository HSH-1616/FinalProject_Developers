<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link rel="stylesheet" href="${path }/css/mypage/mypageDetail.css" />
<head>

<script src="${path }/js/mypage/mypageDetail.js"/>
</head>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<section>

	<div class="mypageheader">
		<div class="mypageheaderarea">
			<div class="mypageprofile">
				<div class="profile">
					<span class="boxsize"> 
					<img alt="" src="https://www.myro.co.kr/assets/images/user-avatar.png">
					</span>
				</div>
				<h2 class="nickname"></h2>
			</div>
			<div class="flex flex-col w-full my-4 lg:max-w-md">
				<div class="w-full my-4">
					<form>
						<div class="mt-6">
							<div class="mt-2">
								<label class="block text-xs">닉네임</label> <input type="text"
									class="flex-1 w-full p-2 my-2 text-black placeholder-gray-700 bg-gray-800 rounded-sm focus:outline-none focus:border-transparent"
									name="memberNewNickname">
							</div>
							<div class="mt-2">
								<label class="block text-xs">이메일</label><input type="email"
									class="flex-1 w-full p-2 my-2 text-black placeholder-gray-700 bg-gray-800 rounded-sm focus:outline-none focus:border-transparent"
									readonly="" value="">
							</div>
							<div class="mt-4">
								<p class="text-xs font-light text-left text-gray-500">
									<button id="withdrawButton" class="text-gray-500 hover:underline">회원탈퇴</button>
								</p>
							</div>
							<div class="flex items-center mt-4">
								<button
									class="flex items-center justify-center w-full p-2 mr-2 text-sm bg-[#fafafa] rounded-md">
									<a class="w-full h-full" href="/my-info">돌아가기</a>
								</button>
								<button
									class="flex items-center justify-center w-full p-2 ml-2 text-sm text-white rounded-md disabled:opacity-50 disabled:bg-gray-500 bg-lightScheme-primary-focus"
									disabled="">저장</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>

	</div>

</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />