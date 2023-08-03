



const noticeWrite = () => {
	console.log($("input[name=noticeTitle").val());
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

		}
	})
});


const deleteNotice = (no) => {

	$(".removeBtn").click(function(e) {
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

/*const searchNotice=()=>{
	let type=$("select[name=type]").val();
	let keyword=$("input[name=keyword]").val();
	$.ajax({
		url: "/notice/searchNotice.do",
		data: {type: type,keyword: keyword},
		type: "get",
		success: (data)=>{
			if(data!=null){
				console.log(data);
				$(".nolist-table").replaceWith(data);
				let str=""
				<tr><td colspan="5">조회된 공지사항이 없습니다.</td></tr>
			}
		}
	});
}*/
	








