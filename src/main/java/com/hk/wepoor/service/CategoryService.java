package com.hk.wepoor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.wepoor.model.CategoryMapper;
import com.hk.wepoor.vo.CategoryVO;

@Service
public class CategoryService {

	@Autowired
	CategoryMapper category_mapper;

	// <전체조회>
	public List<CategoryVO> selectAll() {
		List<CategoryVO> list = category_mapper.selectAll();
		return list;
	};

	// <하나조회>
	public CategoryVO select(int Id) {

		CategoryVO cateId = category_mapper.select(Id);

		return cateId;
	};

	// <등록>
	public int insert(String cateName) {
		int affectRowCount = category_mapper.insert(cateName);
		return affectRowCount;
	};

	// <삭제>
	public int delte(int cateName) {
		int affectRowCount = category_mapper.delte(cateName);
		return affectRowCount;
	};

	// <수정>
	public int update(CategoryVO categoryVO) {
		int affectRowCount = category_mapper.update(categoryVO);
		return affectRowCount;
	};

	// 주차별 기간 선택 카테고리 조회
	public List<CategoryVO> selectAllWeekend() {
		List<CategoryVO> list = category_mapper.selectAllWeekend();
		return list;
	};
	
	// N주차 방목록(8개 카테고리)
	public List<CategoryVO> selectAllRoomList(int cate_weekend){
		List<CategoryVO> list = category_mapper.selectAllRoomList(cate_weekend);
		return list;
	};
}
