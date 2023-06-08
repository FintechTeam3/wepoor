package com.hk.wepoor.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.hk.wepoor.service.RoomDetailService;
import com.hk.wepoor.vo.RoomDetailVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class RoomDetailController {

	@Autowired
	RoomDetailService svc;

	@GetMapping("/roomdetail")
	public String roomdetail(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		int userNo = (int) session.getAttribute("userNo");
		List<RoomDetailVO> list = svc.selectAll(userNo);

		Date dateNow = new Date();

//		req.setAttribute("list", list);
		for (RoomDetailVO v : list) {

			if (dateNow.before(v.getStart_date())) {
				v.setStatus("진행전");

			} else {
				if (dateNow.before(v.getEnd_date())) {
					v.setStatus("진행중");
				} else {
					v.setStatus("끝");
				}
			}
			System.out.println(dateNow);
			System.out.println(v.getCate_date());
			System.out.println(v.getStatus());
		}
		req.setAttribute("list", list);
		return "test";
	}

}
