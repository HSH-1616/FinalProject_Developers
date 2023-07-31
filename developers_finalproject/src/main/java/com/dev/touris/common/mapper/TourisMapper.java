package com.dev.touris.common.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.dev.touris.model.vo.Touris;

@Mapper
public interface TourisMapper {
	
	@Insert("INSERT INTO TOURIS(touris_id, touris_name, touris_address, touris_content, touris_latitude, touris_longitude, touris_level, touris_mainimg, touris_page, touris_phone, touris_experience, touris_tel, touris_usemonth, touris_usetime, touris_parking, touris_startday, touris_dayoff, area_id) "
			+ "VALUES (#{tourisId}, #{tourisName}, #{tourisAddress}, #{tourisContent}, #{tourislatitude}, #{tourisLongitude}, #{tourisLevel}, #{tourismainImge}, #{tourispage}, #{tourisPhone}, #{tourisExperience}, #{tourisTel}, #{tourisUsemonth}, #{tourisUsetime}, #{tourisParking}, #{tourisStartday}, #{tourisDayoff}, #{areaId})")
	void insertTouris(Touris touris);
	
	
	
}
