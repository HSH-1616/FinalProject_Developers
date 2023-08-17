
function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/', hostIndex));
};



const communityList = (cPage, numPerpage) => {
	let result;
	$.ajax({
		url: getContextPath()+"/community/communityListEnd.do",
		type: "post",
		async: false,
		data: { cPage: cPage, numPerpage: numPerpage },
		success: (data) => {

			for (const c of data) {
				let str = "<div class='col'>";
				str += "<div class='card community-cardList' onclick='communityView(" + c.communityNo + ")'>";
				str += "<div class='d-flex'>";
				str += "<a><img src='" + c.memberId.memberImage + "' style='height: 30px; border-radius:50%; margin:5px;'></a>&nbsp";
				str += "<div style='margin-top:10px;'>" + c.memberId.memberNickname + "</div>";
				str += "</div>";
				str += "<img src='"+getContextPath()+"/upload/community/" + c.thumbnail + "' class='card-img-top' >";
				str += "<div class='card-body'>";
				str += "<h5 class='card-title'>" + c.communityTitle + "</h5>";
				str += "<div class='text-end '>";
				str += "<div><img src='<c:out value='"+getContextPath()+"/images/community/fillheart.svg' style='height: 20px; width:auto; margin-right:3px;'>" + c.likeCount + "</div>";
				str += "</div>";
				/*str += "<p class='card-text'>" + c.communityContent + "</p>";*/
				str += "</div>";
				str += "</div>";
				str += "</div>";
				$('#community-cards').append(str);
				result = str;
			}

		}
	});
	return result;
};



const communityView = (no, id) => {
	location.assign(getContextPath()+"/community/communityView.do?no=" + no + "&id=" + id);
};

//무한스크롤 페이징
const target = document.querySelector('#footer');
let cPage = 0;

let callback = (entries, observer) => {
	entries.forEach(entry => {
		if (entry.isIntersecting) {
			cPage++;
			const list = communityList(cPage);
			if (list == undefined) {
				observer.unobserve(target);
				return;
			}
		}
	})
}

const observer = new IntersectionObserver(callback, { threshold: 0.7 });
observer.observe(target);


$(".like-review")
	.click(
		function(e) {
			if ($(".like-review").val() == "false") {
				$(e.target)
					.html(
						'<i class="fa fa-heart" aria-hidden="true"></i> You liked this');
				$(e.target).children('.fa-heart').addClass(
					'animate-like');
				$(".like-review").val("true");
				fn_like();
				$(".like-content>img").attr("src", getContextPath()+"/images/community/fillheart.svg");
				let count = $(".like-content>span").text();
				$(".like-content>span").text(Number(count) + 1);
			} else {
				$(e.target)
					.html(
						'<i class="fa fa-heart" aria-hidden="true"></i>Like');
				$(e.target).children('.fa-heart').addClass(
					'animate-like');
				$(".like-review").val("false");
				fn_like();
				$(".like-content>img").attr("src", getContextPath()+"/images/community/heart.svg");
				let count = $(".like-content>span").text();
				$(".like-content>span").text(Number(count) - 1);
			}
		});

$(".like-content").ready(() => {
	if ($('#like_memberId').length > 0 && $("#like_memberId").val() != "") {
		$.ajax({
			url: getContextPath()+"/community/communityLikeCheck.do",
			data: { "memberId": $("#like_memberId").val(), "communityNo": $("#like_comuNo").val() },
			type: "post",
			success: (data) => {
				if (data == 1) {
					$(".like-review").html('<i class="fa fa-heart" aria-hidden="true"></i> You liked this');
					$(".like-review").children('.fa-heart').addClass(
						'animate-like');
					$(".like-review").val("true");
					$(".like-content>img").attr("src", getContextPath()+"/images/community/fillheart.svg");
				}
			}
		});
	}
});

const fn_like = () => {
	const no = $("#like_comuNo").val();
	const memberId = $("#like_memberId").val();
	$.ajax({
		url: getContextPath()+"/community/communityLike.do",
		type: "post",
		data: { memberId: memberId, communityNo: no },
		success: () => {
			
		}
	});

}
$(document).on("click", ".insertReplies", function() {
	insertReply($(this).prev().val(), $(this).prev().prev().val());
})

const insertReply = (ref, con) => {
	let content;
	if (con == "" || con == null) {
		content = $("#comment_input").val();
	} else {
		content = con;
	}
	const communityNo = $("#like_comuNo").val();
	const memberId = $("#like_memberId").val();

	$.ajax({
		url: getContextPath()+"/community/insertReply.do",
		type: "post",
		data: { memberId: memberId, communityNo: communityNo, replyContent: content, replyRef: ref },
		success: () => {
			$("#comment_input") == null;
			replyList();
		}
	});
}


$(document).ready(() => {
	if ($(".reply-list").length > 0) {
		replyList();
	}
})
const replyList = () => {
	const communityNo = $("#like_comuNo").val();
	const memberId = $("#like_memberId").val();
	const loginAdmin = $("#adminCheck").val();
	$.ajax({
		url: getContextPath()+"/community/replyList.do",
		type: "get",
		data: { communityNo: communityNo },
		success: (data) => {
			$(".reply-list").empty();
			for (const i of data) {
				if (i.replyLevel == 0) {
					let str = "<div class='comments p-2 border rounded'>";
					str += "<div class='comment'>";
					str += "<div class='comment_detail'>";
					str += "<div class='comment_user'>";
					str += "<span>" + i.memberId.memberNickname + "</span>";
					str += "</div>";
					str += "<div class='comment_body'>";
					str += "<span class='" + i.replyNo + "'>" + i.replyContent + "</span>";
					str += "</div>";
					str += "</div>";
					str += "</div>";

					str += "<div class='text-end mb-3 p-2'>";
					if (memberId == i.memberId.memberId) {
						str += "<button class='reply-update s-btn m-2' data-name='" + i.replyNo + "' >수정</button>";
					}
					if (memberId == i.memberId.memberId || loginAdmin != "") {
						str += "<button class='reply-delete s-btn mx-1' onclick='deleteReply(" + i.replyLevel + "," + i.replyNo + ")'>삭제</button>";
					}
					if (memberId != "") {
						str += "<a class='w-btn-outline w-btn-blue-outline' data-title='대댓글달기' data-bs-toggle='collapse' href='#inputReplies" + i.replyNo + "' role='button'"
							+ "aria-expanded='false' aria-controls='collapseComment'> 댓글쓰기 </a>";
					}
					str += "</div>";
					str += "</div>";
					str += "<div class='collapse mt-3' id='inputReplies" + i.replyNo + "'>";
					str += "<div class='card card-body'>";
					str += "<input type='text' id='comment_input'>";
					str += "<input type='hidden' value=" + i.replyNo + ">";
					str += "<button class='w-btn w-btn-blue insertReplies'>등록</button>";
					str += "</div>";
					str += "</div>";
					str += "</div>";
					str += "</div>";

					$(".reply-list").append(str);

				} else {
					let str = "<div class='comments mx-3 p-2 border rounded'>";
					str += "<div class='comment'>";
					str += "<div class='comment_detail'>";
					str += "<div class='comment_user'>";
					str += "<span>re: " + i.memberId.memberNickname + "</span>";
					str += "</div>";
					str += "<div class='comment_body'>";
					str += "<span class='" + i.replyNo + "'>" + i.replyContent + "</span>";
					str += "</div>";
					str += "</div>";
					str += "</div>";
					str += "<div class='text-end mb-3 p-2'>";
					if (memberId == i.memberId.memberId) {
						str += "<button class='reply-update s-btn m-2' data-name='" + i.replyNo + "'>수정</button>";
					}
					if (memberId == i.memberId.memberId || loginAdmin != "") {
						str += "<button class='reply-delete s-btn' onclick='deleteReply(" + i.replyLevel + "," + i.replyNo + ")'>삭제</button>";
					}
					str += "</div>";
					str += "</div>";
					str += "</div>";

					str += "</div>";
					$(".reply-list").append(str);
				}
			}

		}
	});
}

$(".reply-list").on("click", ".reply-update", function() {
	let target = $(this);
	const no = target.data("name");

	let updateForm = "<span><input type='text' id='editReply" + no + "' value='" + $("." + no).html() + "'></span>";
	updateForm += "<button class='s-btn m-1' onclick='updateReply(" + no + ");'>수정</button>"
	updateForm += "<button class='s-btn m-1' onclick='replyList()'>취소</button>"

	$("." + no).parent().replaceWith(updateForm);
})


const updateReply = (no) => {

	const reContent = $("#editReply" + no + "").val();

	$.ajax({
		url: getContextPath()+"/community/updateReply.do",
		type: "post",
		data: { replyNo: no, replyContent: reContent },
		success: () => {
			replyList();
		}, error: () => {
			alert("댓글 수정 실패");
		}
	})

}

const deleteReply = (level, no) => {
	if (confirm("댓글을 삭제하시겠습니까?") == true) {
		$.ajax({
			url: getContextPath()+"/community/deleteReply.do",
			type: "post",
			data: { replyNo: no, replyLevel: level },
			success: () => {
				replyList();
			}, error: () => {
				alert("댓글 삭제 실패");
			}
		})
	}
}

deleteCommunity = (no) => {

	if (confirm("게시글을 삭제하시겠습니까?") == true) {
		$.ajax({
			url: getContextPath()+"/deleteCommunity.do",
			type: "post",
			data: { communityNo: no },
			success: () => {
				alert("삭제성공");
				location.replace(getContextPath()+"/community/communityList.do");
			}, error: () => {
				alert("삭제실패");
				location.replace(getContextPath()+"/community/communityList.do");
			}
		})
	} else {
		return false;
	}


}

$(".file-del").click(function(e) {

	let target = $(this);
	const fileName = target.data("name");
	const targetLi = e.target.closest("li");
	console.log("파일삭제");
	$.ajax({
		url: getContextPath()+"/ncCommon/removeCommunityFile.do",
		data: { fileName: fileName },
		type: "post",
		success: (data) => {
			if (data > 0) {
				targetLi.remove();

			}
			else {
				alert("삭제실패");
			}

		}
	});
})


const community_cancel = () => {
	window.history.back();
}


