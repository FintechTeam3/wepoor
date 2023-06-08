package com.hk.wepoor.model;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.hk.wepoor.vo.AccountVO;
import com.hk.wepoor.vo.UserVO;

@Repository
@Mapper
public interface UserMapper {

	void insertUser(UserVO uservo);

	UserVO getUserByUserId(String userId);

	UserVO getUserByUserNo(int userNo);

	List<UserVO> getAllUsers();

	void updateUser(UserVO uservo);
	
	void updateMy(UserVO uservo);

	void deleteUser(int userId);

	String HashedPwd(String userPwd);

	List<HashMap<String, String>> getAllUserId();
	
	List<UserVO> getUserNo();
	
	// 사용자 nickname 으로 조회하기 (중복조회 위함)
	UserVO getUserNick(String user_nickname);

}
