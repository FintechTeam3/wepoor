package com.hk.wepoor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.wepoor.model.PayMapper;
import com.hk.wepoor.vo.PayVO;

@Service
public class PayService {
	
	@Autowired
	PayMapper pay_mapper;
	
	public List<PayVO> selectAll() {
		List<PayVO> list = pay_mapper.selectAll();
		return list;
	}
	
	public PayVO select(int pay_id) {
		PayVO payVO = pay_mapper.select(pay_id);
		return payVO;
	}
	
	public int create(PayVO payVO) {
		int affectRowCount = pay_mapper.insert(payVO);
		return affectRowCount;
	}
	
	public int delete(int pay_id) {
		int affectRowCount = pay_mapper.delete(pay_id);
		return affectRowCount;
	}
	
	public int update(PayVO payVO) {
		int affectRowCount = pay_mapper.update(payVO);
		return affectRowCount;
	}

	public int updateLeave(PayVO payVO) {
		int success = pay_mapper.updateLeave(payVO);
		return success;
	}
	
	
}
