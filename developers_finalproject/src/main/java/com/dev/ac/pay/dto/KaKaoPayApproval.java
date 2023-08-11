package com.dev.ac.pay.dto;

import java.util.Date;

import lombok.Data;

@Data
public class KaKaoPayApproval {
	private String aid, tid, cid, sid;
    private String partner_order_id, partner_user_id, payment_method_type;
    private KaKaoAmount amount;
    private String item_name, item_code, payload;
    private Integer quantity;
    private Date created_at, approved_at;
}


