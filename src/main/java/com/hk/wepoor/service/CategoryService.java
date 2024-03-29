package com.hk.wepoor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.wepoor.model.CategoryMapper;
import com.hk.wepoor.vo.CategoryVO;

@Service
public class CategoryService {

	@Autowired
	CategoryMapper categorymapper;

	// <전체조회>
	public List<CategoryVO> selectAll() {
		List<CategoryVO> list = categorymapper.selectAll();
		return list;
	};

	// <하나조회>
	public CategoryVO select(int Id) {

		CategoryVO cateId = categorymapper.select(Id);

		return cateId;
	};

	// <등록>
	public int insert(String cateName) {
		int affectRowCount = categorymapper.insert(cateName);
		return affectRowCount;
	};

	// <삭제>
	public int delete(int cateName) {
		int affectRowCount = categorymapper.delte(cateName);
		return affectRowCount;
	};

	// <수정>
	public int update(CategoryVO categoryVO) {
		int affectRowCount = categorymapper.update(categoryVO);
		return affectRowCount;
	};

	// 주차별 기간 선택 카테고리 조회
	public List<CategoryVO> selectAllWeekend() {
		List<CategoryVO> list = categorymapper.selectAllWeekend();
		return list;
	};
	
	// N주차 방목록(8개 카테고리)
	public List<CategoryVO> selectAllRoomList(int cate_weekend){
		List<CategoryVO> list = categorymapper.selectAllRoomList(cate_weekend);
		return list;
	};
	
	public List<String> roomCheck(int user_no){
		return categorymapper.roomCheck(user_no);
	}

	public String select_category(int cate_id) {
		String category = categorymapper.selectCategory(cate_id);
		return category;
	}
	
	
}
