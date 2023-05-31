package com.hk.wepoor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.wepoor.model.CommunityMapper;
import com.hk.wepoor.vo.CommunityVO;

@Service
public class CommunityService {
	
	@Autowired
	CommunityMapper community_mapper;
	
	public List<CommunityVO> selectAll() {
		List<CommunityVO> list = community_mapper.selectAll();
		return list;
	}
	
	public CommunityVO select(int commu_id) {
		CommunityVO communityVO = community_mapper.select(commu_id);
		return communityVO;
	}
	
	public int create(CommunityVO communityVO) {
		int affectRowCount = community_mapper.insert(communityVO);
		return affectRowCount;
	}
	
	public int delete(int commu_id) {
		int affectRowCount = community_mapper.delete(commu_id);
		return affectRowCount;
	}
	
	public int update(CommunityVO communityVO) {
		int affectRowCount = community_mapper.update(communityVO);
		return affectRowCount;
	}
	
}
