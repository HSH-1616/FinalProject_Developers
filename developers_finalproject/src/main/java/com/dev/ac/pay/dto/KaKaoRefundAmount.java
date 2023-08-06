package com.dev.ac.pay.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KaKaoRefundAmount {
	private int total; // 이번 요청으로 취소된 전체 금액
	private int tax_free; // 이번 요청으로 취소된 비과세 금액
	private int vat; // 이번 요청으로 취소된 부가세 금액
	private int point; // 이번 요청으로 취소된 포인트 금액
	private int discount; // 이번 요청으로 취소된 할인 금액
	private int green_deposit; // 컵 보증금
}
