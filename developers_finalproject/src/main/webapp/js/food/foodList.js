// 초기 상태 설정
    let liked = false;

    // 좋아요 버튼 클릭 이벤트 처리
    const likeButton = document.querySelector('.like');
    likeButton.addEventListener('change', function () {
        liked = this.checked;
        updateIcon();
    });

    // 아이콘 업데이트 함수
    function updateIcon() {
        const checkmark = document.querySelector('.checkmark');
        const outlineIcon = checkmark.querySelector('.outline');
        const filledIcon = checkmark.querySelector('.filled');
        const celebrateIcon = checkmark.querySelector('.celebrate');

        if (liked) {
            outlineIcon.style.display = 'none';
            filledIcon.style.display = 'block';
            celebrateIcon.style.display = 'block';
        } else {
            outlineIcon.style.display = 'block';
            filledIcon.style.display = 'none';
            celebrateIcon.style.display = 'none';
        }
    }

    // 초기 아이콘 상태 설정
    updateIcon();



//로그인 시 좋아요 이용가능
$(document).ready(function() {
	$(".like").on("click", function() {
		var isLoggedIn = false;
		var memberId = $("#memberL").val();

		if (memberId == "") {
			isLoggedIn = true;
		}

		if (memberId == "") {
			alert("로그인 후 이용 가능합니다!");
			isLoggedIn = false;
		}

	});
});


if($("#memberL").val()!=""){
	$(".like").on("click", function() {
		var foodNo = $(this).val();
		console.log(foodNo);
		
		if ($(this).is(":checked")) {
			$.ajax({
				url: path+"/food/insertHeart",
				data: {
					foodNo: foodNo
				},
				success: function(result) {
					if (result > 0) {

					}
				}
			})
		} else {
			$.ajax({
				url: path+"/food/deleteHeart",
				data: {
					foodNo: foodNo
				},
				success: function(result) {
					if (result > 0) {

					}
				}
			})
		}

	})
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

	xhr.onload = function() {
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
		url: path+"/food/list", // 서버에서 맛집 리스트를 가져올 URL
		data: { sortFilter: sortFilter }, // 클릭한 <li> 태그의 id 값을 전달
		success: function(data) {
			// 서버로부터 받은 데이터를 이용하여 맛집 리스트를 업데이트
			$(".food_main_list").html(data);
		},
		error: function(error) {
			console.error("Error occurred while fetching data:", error);
		}
	});
}

// <li> 태그 클릭 이벤트 핸들러 등록
$(document).ready(function() {
	$("#all").click(function(e) {
		updateFoodList("all"); // "제목순" 클릭 시
		e.propagationStop();
	});

	$("#popular").click(function(e) {
		updateFoodList("popular"); // "좋아요순" 클릭 시
		e.propagationStop();
	});

	$("#review").click(function(e) {
		updateFoodList("review"); // "리뷰순" 클릭 시
		e.propagationStop();
	});
});


/*ajax*/
function updateFoodList(sortFilter) {
	$.ajax({
		type: "GET",
		url: path+"/food/list",
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
document.getElementById("all").addEventListener("click", function() {
	resetActiveMenu(); // 모든 메뉴 스타일 초기화
	this.querySelector(".title").classList.add("active"); // 현재 메뉴에 active 클래스 추가
	// 추가적으로 제목순에 해당하는 동작 처리 (원하는 동작이 있을 경우 여기에 추가)
});

// 조회순 메뉴 클릭 시 처리
document.getElementById("popular").addEventListener("click", function() {
	resetActiveMenu(); // 모든 메뉴 스타일 초기화
	this.querySelector(".title").classList.add("active"); // 현재 메뉴에 active 클래스 추가
	// 추가적으로 조회순에 해당하는 동작 처리 (원하는 동작이 있을 경우 여기에 추가)
});

// 리뷰순 메뉴 클릭 시 처리
document.getElementById("review").addEventListener("click", function() {
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

