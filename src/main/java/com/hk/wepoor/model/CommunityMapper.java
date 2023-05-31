package com.hk.wepoor.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hk.wepoor.vo.CommunityVO;

@Mapper
public interface CommunityMapper {
	
	List<CommunityVO> selectAll();
	CommunityVO select(int commu_id);
	int insert(CommunityVO communityVO);
	int delete(int commu_id);
	int update(CommunityVO communityVO);
	
}
