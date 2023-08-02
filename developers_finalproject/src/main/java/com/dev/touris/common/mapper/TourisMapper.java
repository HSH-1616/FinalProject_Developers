package com.dev.touris.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.dev.touris.model.vo.Touris;

@Mapper
public interface TourisMapper {
	
	@Insert("INSERT INTO TOURIS(touris_id, touris_name, touris_address, touris_latitude, touris_longitude, touris_level, touris_mainimg, touris_phone, area_id) "
			+ "VALUES (#{tourisId}, #{tourisName}, #{tourisAddress}, #{tourislatitude}, #{tourisLongitude}, #{tourisLevel}, #{tourismainImge}, #{tourisPhone}, #{areaId})")
	void insertTouris(Touris touris);
	
	@Update("UPDATE TOURIS SET touris_name = #{tourisName}, touris_address = #{tourisAddress}, touris_content = #{tourisContent}, touris_latitude = #{tourislatitude}, touris_longitude = #{tourisLongitude}, touris_level = #{tourisLevel}, touris_mainimg = #{tourismainImge}, touris_page = #{tourispage}, touris_phone = #{tourisPhone}, area_id = #{areaId}"
            + " WHERE touris_id = #{tourisId}")
	void insertTourisCommonConent(Touris touris);
	
	@Update("INSERT INTO TOURIS(touris_id, touris_name, touris_address, touris_content, touris_latitude, touris_longitude, touris_level, touris_mainimg, touris_page, touris_phone, touris_experience, touris_tel, touris_usemonth, touris_usetime, touris_parking, touris_startday, touris_dayoff, area_id) "
			+ "VALUES (#{tourisId}, #{tourisName}, #{tourisAddress}, #{tourisContent}, #{tourislatitude}, #{tourisLongitude}, #{tourisLevel}, #{tourismainImge}, #{tourispage}, #{tourisPhone}, #{tourisExperience}, #{tourisTel}, #{tourisUsemonth}, #{tourisUsetime}, #{tourisParking}, #{tourisStartday}, #{tourisDayoff}, #{areaId})")
	void insertTourisDetaisConent(Touris touris);
	
	
}
