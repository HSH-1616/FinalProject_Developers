package com.dev.ac.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Accommodation {
	private int acId;
	private String acTitle;
	private String acAddress;
	private int acPrice;
	private String acContent;
	private String acType;
	private int acPeople;
	private int acRoom;
	private int acBed;
	private int acBathRoom;
	private Date acDate;
	private double reviewGrade;
	private int reviewCount;
	private List<AcHeart> acHearts;
	private List<AcReview> acReviews;
	private List<AcFile> acFiles=new ArrayList();
	private List<AcReservation> arv;	
	private AcFacilities afa;	
}
