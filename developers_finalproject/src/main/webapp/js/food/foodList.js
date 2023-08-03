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

