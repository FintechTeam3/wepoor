package com.hk.wepoor.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hk.wepoor.vo.CategoryVO;

@Mapper
public interface CategoryMapper {
	List<CategoryVO> selectAll();
	CategoryVO select(int cateId);
	int insert(String cateName);
	int delte(int cateName);
	int update(CategoryVO categoryVO);
	
	// 주차 방 만들기 위한 select - 혜정
	List<CategoryVO> selectAllWeekend();
}