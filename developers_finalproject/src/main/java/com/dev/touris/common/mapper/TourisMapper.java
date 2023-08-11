package com.dev.touris.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.dev.touris.model.vo.Touris;
import com.dev.touris.model.vo.TourisArea;

@Mapper
public interface TourisMapper {
	
	@Insert("INSERT INTO TOURIS(touris_id, touris_name, touris_address, touris_latitude, touris_longitude, touris_level, touris_mainimg, touris_phone, touris_area_id) "
			+ "VALUES (#{tourisId}, #{tourisName}, #{tourisAddress}, #{tourislatitude}, #{tourisLongitude}, #{tourisLevel}, #{tourismainImge}, #{tourisPhone}, #{tourisAreaid})")
	void insertTouris(Touris touris);
	
	@Results(id="tourismap",value= {
			@Result(property = "tourisId", column ="TOURIS_ID"),
			@Result(property = "tourisName", column ="TOURIS_NAME"),
			@Result(property = "tourisAddress", column ="TOURIS_ADDRESS"),
			@Result(property = "tourisContent", column ="TOURIS_CONTENT"),
			@Result(property = "tourislatitude", column ="TOURIS_LATITUDE"),
			@Result(property = "tourisLongitude", column ="TOURIS_LONGITUDE"),
			@Result(property = "tourisLevel", column ="TOURIS_LEVEL"),
			@Result(property = "tourismainImge", column ="TOURIS_MAINIMG"),
			@Result(property = "tourispage", column ="TOURIS_PAGE"),
			@Result(property = "tourisPhone", column ="TOURIS_PHONE"),
			@Result(property = "tourisExperience", column ="TOURIS_EXPERIENCE"),
			@Result(property = "tourisTel", column ="TOURIS_TEL"),
			@Result(property = "tourisUsemonth", column ="TOURIS_USEMONTH"),
			@Result(property = "tourisUsetime", column ="TOURIS_USETIME"),
			@Result(property = "tourisParking", column ="TOURIS_PARKING"),
			@Result(property = "tourisStartday", column ="TOURIS_STARTDAY"),
			@Result(property = "tourisDayoff", column ="TOURIS_DAYOFF"),
			@Result(property = "tourisarea", one = @One(resultMap = "tourisAreamap"))
	})
	void tourisMap();
	
////	메인페이지에서 관광 map지도 선택하	는 페이지
	@Results(id = "tourisAreamap", value={
		@Result(property = "areaId", column = "area_id"),
		@Result(property = "areaName", column = "area_name"),
		@Result(property = "areaLatitude", column = "area_latitude"),
		@Result(property = "areaLongitude", column = "arealongitude"),
		@Result(property = "areaEng", column = "area_eng"),
		@Result(property = "areaImg", column = "area_img"),
		@Result(property = "areaContent", column = "area_content"),
		@Result(property = "tourises", many=@Many(resultMap = "tourismap"))
	})
	@Select("SELECT * FROM TITLE_AREA")
	List<TourisArea> tourisMainSelectMapPage();
	
////	관광MAP에 처음에 뿌려주는 SQL문
//	@Select("SELECT * FROM TOURIS WHERE AREA_ID = #{areaId}")
//	List<Touris> selecttourislist(String areaId);
	
	
	
	
//	@Update("UPDATE TOURIS SET touris_name = #{tourisName}, touris_address = #{tourisAddress}, touris_content = #{tourisContent}, touris_latitude = #{tourislatitude}, touris_longitude = #{tourisLongitude}, touris_level = #{tourisLevel}, touris_mainimg = #{tourismainImge}, touris_page = #{tourispage}, touris_phone = #{tourisPhone}, area_id = #{areaId}"
//            + " WHERE touris_id = #{tourisId}")
//	void insertTourisCommonConent(Touris touris);
//	
//	@Update("INSERT INTO TOURIS(touris_id, touris_name, touris_address, touris_content, touris_latitude, touris_longitude, touris_level, touris_mainimg, touris_page, touris_phone, touris_experience, touris_tel, touris_usemonth, touris_usetime, touris_parking, touris_startday, touris_dayoff, area_id) "
//			+ "VALUES (#{tourisId}, #{tourisName}, #{tourisAddress}, #{tourisContent}, #{tourislatitude}, #{tourisLongitude}, #{tourisLevel}, #{tourismainImge}, #{tourispage}, #{tourisPhone}, #{tourisExperience}, #{tourisTel}, #{tourisUsemonth}, #{tourisUsetime}, #{tourisParking}, #{tourisStartday}, #{tourisDayoff}, #{areaId})")
//	void insertTourisDetaisConent(Touris touris);
//	
	
}
