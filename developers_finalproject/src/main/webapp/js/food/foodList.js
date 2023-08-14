const sortFoodByTitle = (no,numPerpage)=> {

    $.ajax({
        url: path + "/food/sortByTitle.do", 
        data: { sortType: sortType,
        		cPage: no, numPerpage: numPerpage },
        type: "GET",
        success: function(data) {
            // Update the food_main_list section with the sorted data
            $(".food_main_list").html(data);
        },
        error: function(xhr, status, error) {
            console.log("Error:", error);
        }
    });
}





//좋아요
$(document).ready(function () {
    $(".con-like").click(function () {
        var heartCountElement = $(this).siblings(".countDiv").find(".heart_count");
        var currentHeartCount = parseInt(heartCountElement.text(), 10);
        
        console.log(heartCountElement);
        console.log(currentHeartCount);
        $.ajax({
				url:"/food/toggleHeart",
				type:"post",
				data:{heartCountElement : heartCountElement, currentHeartCount : currentHeartCount},
				success:(data)=>{
					console.log(data);
				}
				
			});
        if ($(this).prop("checked")) {
            heartCountElement.text(currentHeartCount + 1);
            
        } else {
			
            if (currentHeartCount > 0) {
                heartCountElement.text(currentHeartCount - 1);
            }
        }
    });
});


function sortFoodList(sortType) {
    // 서버 요청을 보낼 URL 설정 (적절한 URL로 변경해야 함)
    var url = "${path}/food/sortFoodList.do";
    
    // AJAX를 이용한 POST 요청
    var xhr = new XMLHttpRequest();
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                // 성공적으로 응답을 받았을 때, 응답 데이터를 이용해 목록을 업데이트
                var response = JSON.parse(xhr.responseText);
                updateFoodList(response.foods);
            } else {
                console.error("Error:", xhr.statusText);
            }
        }
    };
    xhr.send("sortType=" + sortType);
}

// 음식 목록 업데이트 함수
function updateFoodList(foods) {
    var foodListContainer = document.querySelector(".food_main_list");
    foodListContainer.innerHTML = ""; // 목록 비우기
    
    for (var i = 0; i < foods.length; i++) {
        // 음식 정보를 이용하여 각 목록 아이템 생성
        var f = foods[i];
        var foodItem = `
            <div class="food_list">
                <!-- ... 음식 정보 표시 ... -->
            </div>
        `;
        foodListContainer.innerHTML += foodItem;
    }
}




function updateFoodList(sortFilter) {
    // Make an AJAX request to the server with the chosen sortFilter
    const xhr = new XMLHttpRequest();
    xhr.open("GET", `/foods?sortFilter=${sortFilter}`, true);

    xhr.onload = function () {
        if (xhr.status === 200) {
            const newFoodList = xhr.responseText;
            const foodListContainer = document.querySelector(".food_main_list");
            foodListContainer.innerHTML = newFoodList;
        }
    };

    xhr.send();
}



function searchFood(page) {

	const form = document.getElementById('searchForm');

	const queryParams = {
		page: (page) ? page : 1,
		recordSize: 5,
		pageSize: 5,
		searchType: $("select[name=searchType]").val(),
		keyword: $("input[name=keyword]").val()
	}
	console.log(queryParams);

	//location.href = location.pathname + "/food/searchFood.do?type=" + queryParams.searchType + "&keyword=" + queryParams.keyword;
	
	const queryString = new URLSearchParams(queryParams).toString();
        const redirectURL = "/food/searchFood.do?" + queryString;
        location.href = redirectURL;

}

//aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
// 페이지 로드 후 실행
window.onload = function() {
	const likeCheckbox = document.querySelector('.like');
	const heartCount = document.querySelector('.heart_count');

	// like 체크박스를 클릭했을 때 이벤트 처리
	likeCheckbox.addEventListener('click', function() {
		// 현재 하트 카운트 값 가져오기
		let count = parseInt(heartCount.textContent, 10);

		// 체크박스가 체크되어 있는지 확인
		if (this.checked) {
			// 체크되어 있다면 하트 카운트 증가
			count++;
		} else {
			// 체크되어 있지 않다면 하트 카운트 감소 (최소값 0으로 제한)
			count = Math.max(count - 1, 0);
		}

		// 변경된 하트 카운트 값을 업데이트
		heartCount.textContent = count;
	});
};

 function updateFoodList(sortFilter) {
    $.ajax({
      type: "GET",
      url: "/food/list", // 서버에서 맛집 리스트를 가져올 URL
      data: { sortFilter: sortFilter }, // 클릭한 <li> 태그의 id 값을 전달
      success: function (data) {
        // 서버로부터 받은 데이터를 이용하여 맛집 리스트를 업데이트
        $(".food_main_list").html(data);
      },
      error: function (error) {
        console.error("Error occurred while fetching data:", error);
      }
    });
  }

  // <li> 태그 클릭 이벤트 핸들러 등록
  $(document).ready(function () {
    $("#all").click(function (e) {
      updateFoodList("all"); // "제목순" 클릭 시
      e.propagationStop();
    });

    $("#popular").click(function (e) {
      updateFoodList("popular"); // "좋아요순" 클릭 시
      e.propagationStop();
    });

    $("#review").click(function (e) {
      updateFoodList("review"); // "리뷰순" 클릭 시
      e.propagationStop();
    });
  });


	/*ajax*/
    function updateFoodList(sortFilter) {
        $.ajax({
            type: "GET",
            url: "/food/list",
            data: { sortFilter: sortFilter },
            success: function(data) {
                $(".food_main_list").html(data);
            },
            error: function(error) {
                console.error("111", error);
            }
        });
    }

    $(document).ready(function() {
        $("#all").click(function() {
            updateFoodList("all");
        });

        $("#popular").click(function() {
            updateFoodList("popular");
        });

        $("#review").click(function() {
            updateFoodList("review");
        });
    });
    

// 제목순 메뉴 클릭 시 처리
  document.getElementById("all").addEventListener("click", function () {
    resetActiveMenu(); // 모든 메뉴 스타일 초기화
    this.querySelector(".title").classList.add("active"); // 현재 메뉴에 active 클래스 추가
    // 추가적으로 제목순에 해당하는 동작 처리 (원하는 동작이 있을 경우 여기에 추가)
  });

  // 조회순 메뉴 클릭 시 처리
  document.getElementById("popular").addEventListener("click", function () {
    resetActiveMenu(); // 모든 메뉴 스타일 초기화
    this.querySelector(".title").classList.add("active"); // 현재 메뉴에 active 클래스 추가
    // 추가적으로 조회순에 해당하는 동작 처리 (원하는 동작이 있을 경우 여기에 추가)
  });

  // 리뷰순 메뉴 클릭 시 처리
  document.getElementById("review").addEventListener("click", function () {
    resetActiveMenu(); // 모든 메뉴 스타일 초기화
    this.querySelector(".title").classList.add("active"); // 현재 메뉴에 active 클래스 추가
    // 추가적으로 리뷰순에 해당하는 동작 처리 (원하는 동작이 있을 경우 여기에 추가)
  });

  // 모든 메뉴 스타일을 초기화하는 함수
  function resetActiveMenu() {
    const menuItems = document.querySelectorAll(".menu_style .title");
    menuItems.forEach((item) => {
      item.classList.remove("active");
    });
  }
  
  
  
  // 페이지가 로드되었을 때 실행되는 함수
document.addEventListener("DOMContentLoaded", function() {
    // 모든 좋아요 버튼에 이벤트 리스너 등록
    var likeButtons = document.querySelectorAll(".like");
    likeButtons.forEach(function(button) {
        button.addEventListener("change", function() {
            // 선택된 버튼의 음식 아이디 가져오기
            var foodId = button.getAttribute("data-food-id");
            if (button.checked) {
                incrementLike(foodId);
            } else {
                decrementLike(foodId);
            }
        });
    });
});



// 숫자 업데이트 함수
function updateLikeCount(foodId, change) {
    var likeCountElement = document.getElementById("likeCount_" + foodId);
    var currentCount = parseInt(likeCountElement.textContent);
    var newCount = currentCount + change;
    likeCountElement.textContent = newCount;
}

  