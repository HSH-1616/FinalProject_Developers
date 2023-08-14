document.addEventListener("DOMContentLoaded", function() {
      const withdrawButton = document.getElementById("withdrawButton");

      withdrawButton.addEventListener("click", function() {
        const confirmWithdraw = confirm("정말로 회원 탈퇴 하시겠습니까?");
        if (confirmWithdraw) {
          // 여기에 실제 회원 탈퇴 로직을 추가하세요.
          alert("회원 탈퇴되었습니다."); 
        } else {
          alert("회원 탈퇴가 취소되었습니다."); 
        }
      });
    });