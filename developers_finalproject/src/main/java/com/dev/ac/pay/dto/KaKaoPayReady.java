package com.dev.ac.pay.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KaKaoPayReady {
	// 결제 정보를 보내고 요청이 성공적으로 이루어지면 카카오페이에서 응답정보를 보내준다.
	// KakaoPayReadyDto는 응답을 받는 객체

	private String tid; // 결제 고유 번호
	private String next_redirect_pc_url; // pc 웹일 경우 받는 결제 페이지
	private String created_at;
}
