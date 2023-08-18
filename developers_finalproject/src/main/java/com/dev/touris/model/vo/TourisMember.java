package com.dev.touris.model.vo;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	private String registrationDate;
	List<TourisRoute> tourisroutes;
	List<Touris> tourisList;
	List<TourisArea> tourisArealist;
}
