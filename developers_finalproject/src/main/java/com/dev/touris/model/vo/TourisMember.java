package com.dev.touris.model.vo;

import java.sql.Date;
import java.util.List;

import com.dev.member.model.dto.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TourisMember {
	private int tuId;
	private int loginmemberid;
	private Date tustartDate;
	private Date tuendDate;
	List<TourisRoute> tourisroutes;
}
