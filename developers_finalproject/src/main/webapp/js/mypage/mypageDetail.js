/*document.addEventListener("DOMContentLoaded", function() {
      const withdrawButton = document.getElementById("withdrawButton");

      withdrawButton.addEventListener("click", function() {
        const confirmWithdraw = confirm("정말로 회원 탈퇴 하시겠습니까?");
        if (confirmWithdraw) {
          alert("회원 탈퇴되었습니다."); 
        } else {
          alert("회원 탈퇴가 취소되었습니다."); 
        }
      });
    });
    
    function removeMember() {
    // 실제로는 서버로 데이터를 보내는 방식을 사용하는 것이 좋습니다.
    // 여기서는 페이지 전환을 통해 데모를 진행합니다.
    window.location.href = "http://localhost:8888/"; // 적절한 URL로 변경
}*/
function removeMember() {
        if (confirm("정말로 회원을 탈퇴하시겠습니까?")) {
			let memberId=$("#deleteMember").val();
            $.ajax({
                type: "GET",
                url: path+"/member/deleteMember",
                data: {memberId : memberId},
                success: function(response) {
                    if (response === "success") {
                        alert("회원 탈퇴가 완료되었습니다.");
                        window.location.href = "http://localhost:8888/WEB-INF/views/main"; // 또는 다른 페이지로 이동
                    } else {
                        alert("회원 탈퇴에 실패했습니다. 다시 시도해주세요.");
                    }
                },
                error: function(xhr, status, error) {
                    console.error(error);
                }
            });
        }
    }