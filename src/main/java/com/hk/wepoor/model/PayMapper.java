package com.hk.wepoor.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hk.wepoor.vo.PayVO;

@Mapper
public interface PayMapper {
	
	List<PayVO> selectAll();
	PayVO select(int pay_id);
	int insert(PayVO payVO);
	int delete(int pay_id);
	int update(PayVO payVO);
	int updateLeave(PayVO payVO);
	
	// 구매 완료 화면
	PayVO selectPayComplete(int pay_id);
	
}
