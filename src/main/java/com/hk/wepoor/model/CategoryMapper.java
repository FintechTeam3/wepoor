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
	List<CategoryVO> selectAllWeekend();
	List<CategoryVO> selectAllRoomList(int cate_weekend);
	List<String> roomCheck(int user_no);
	String selectCategory(int cate_id);
}