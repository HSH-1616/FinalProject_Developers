const nList = (no, numPerpage) => {
	$.ajax({
		url: "/notice/noticeListEnd.do",
		data: { cPage: no, numPerpage: numPerpage },
		type: "get",
		success: (data) => {

			let tb;

			$(".nolist-table>tbody").empty();
			if (data.noticeList.length > 0) {

				for (const n of data.noticeList) {
					tb = "<tr>";
					tb += "<td>" + n.noticeNo + "</td>";
					tb += "<td><a href='/notice/noticeView.do?no=" + n.noticeNo + "'>" + n.noticeTitle + "</a></td>";
					tb += "<td>관리자</td>";
					tb += "<td>" + n.noticeViews + "</td>";
					tb += "<td>" + n.writeDate + "</td>";
					tb += "</tr>";

					$(".nolist-table>tbody").append(tb);
				};
				
				$(".board-pasing").html(data.pageBar);

			} else {
				tb = "<tr>";
				tb += "<td colspan='5'>조회된 공지사항이 없습니다.</td>";
				tb += "</tr>";
				$(".nolist-table>tbody").html(tb);

			}
		}

	});
};
$(document).ready(()=>{
	nList()
	});

const noticeWrite = () => {
	//console.log($("input[name=noticeTitle").val());
	if ($("input[name=noticeTitle").val() == "") {
		alert("제목을 입력해주세요");
	} else {
		$(".removeBtn").css("display", "none");
		$("textarea[name=noticeContent]").val($("#contentArea").html());

		$(".notice-form").submit();
	}




};



const cancelNotice = () => {
	$(".removeBtn").trigger("click");
	window.history.back();
};

const cancelUpdateNotice = () => {

	window.history.back();
};



$("#contentArea").on("click", ".removeBtn", function(e) {
	let target = $(this);
	const fileName = target.data("name");
	const targetDiv = e.target.closest("div");


	$.ajax({
		url: "/ncCommon/removeFile.do",
		data: { fileName: fileName },
		type: "post",
		success: (data) => {
			if (data == "true") {
				targetDiv.remove();
			}
			else {
				alert("삭제실패");
			}

		},error:()=>{
			alert("서버오류");
		}
	})
});


const deleteNotice = (no) => {

	$(".removeBtn").click(function() {
		let target = $(this);
		const fileName = target.data("name");
		$.ajax({
			url: "/ncCommon/removeFile.do",
			data: { fileName: fileName },
			type: "post",
			success: (data) => {

				if (data == "true") {
					console.log("파일삭제");
				}
				else {
					alert("삭제실패");
				}

			}
		});

	})
	location.href = "/notice/deleteNotice.do?no=" + no;
};

const searchNotice = (no, numPerpage) => {

	let type = $("select[name=type]").val();
	let keyword = $("input[name=keyword]").val();
	$.ajax({
		url: "/notice/searchNotice.do",
		data: { type: type, keyword: keyword, cPage: no, numPerpage: numPerpage },
		type: "get",
		success: (data) => {

			let tb;

			$(".nolist-table>tbody").empty();
			if (data.noticeList.length > 0) {

				for (const n of data.noticeList) {
					tb = "<tr>";
					tb += "<td>" + n.noticeNo + "</td>";
					tb += "<td><a href='/notice/noticeView.do?no=" + n.noticeNo + "'>" + n.noticeTitle + "</a></td>";
					tb += "<td>관리자</td>";
					tb += "<td>" + n.noticeViews + "</td>";
					tb += "<td>" + n.writeDate + "</td>";
					tb += "</tr>";

					$(".nolist-table>tbody").append(tb);
				};
				
				$(".board-pasing").html(data.pageBar);

			} else {
				tb = "<tr>";
				tb += "<td colspan='5'>조회된 공지사항이 없습니다.</td>";
				tb += "</tr>";
				$(".nolist-table>tbody").html(tb);

			}
		}

	});
}




Kakao.Channel.createChatButton({
  container: '#test-kakao-chanel-button',
  channelPublicId: '_bMrEG'
});




