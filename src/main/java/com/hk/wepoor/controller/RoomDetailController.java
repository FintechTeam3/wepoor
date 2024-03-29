package com.hk.wepoor.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.hk.wepoor.model.UserMapper;
import com.hk.wepoor.service.RoomDetailService;
import com.hk.wepoor.vo.RoomDetailVO;
import com.hk.wepoor.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class RoomDetailController {

	@Autowired
	RoomDetailService svc;
	
	@Autowired
	UserMapper userMapper;

	@GetMapping("/roomdetail")
	public String roomdetail(HttpServletRequest req) {
		
		HttpSession session = req.getSession(false);
		
		int userNo = (int) session.getAttribute("userNo"); 
		
		String userSuccess = svc.userSuccess(userNo);
		
		List<RoomDetailVO> myList = svc.selectAll(userNo);
		
		Date dateNow = new Date();

//		req.setAttribute("list", list);
		for (RoomDetailVO v : myList) {
			
			if (dateNow.before(v.getStart_date())) {
				v.setStatus("시작전");

			} else {
				if (dateNow.before(v.getEnd_date())) {
					v.setStatus("진행중");
				} else {
					v.setStatus("종료");
				}
			}

		}
		System.out.println("myList 입니다." + myList);
		req.setAttribute("myList", myList);
		req.setAttribute("userSuccess", userSuccess);

		// 햄버거바 닉네임
		UserVO userVO = userMapper.getUserByUserNo(userNo);
		req.setAttribute("userNickname", userVO.getUserNickname());

		return "roomDetail";
	}

}
