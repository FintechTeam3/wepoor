package com.hk.wepoor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.wepoor.service.CommunityService;
import com.hk.wepoor.vo.CommunityVO;

@Controller
public class CommunityController {
	
    // CommunityService로 부터 비즈니스 로직을 끌고와서 컨트롤러로 전송
	@Autowired
    private CommunityService communityService;

    // // Test 나중에 수정하기
    // @GetMapping("/community/all")
    // @ResponseBody // 응답 값 끌고오기
    // public List<CommunityVO> getAllCommunity() {
    //     List<CommunityVO> communityList = communityService.selectAll();
    //     return communityList;
    // }

    // Test 나중에 수정하기
    @GetMapping("/community")
    public String getCommunity(Model model) {
        List<CommunityVO> communityList = communityService.selectAll();
        model.addAttribute("communityList", communityList); // communityList값이 community html로 넘어감
        return "community/index";
    }

    // @GetMapping("/community/create")
    // public String showCreateForm(Model model) {

    //     return "community/create";
    // }

    @PostMapping("/community/create")
    public String createCommunity(CommunityVO communityVO) {
        communityService.create(communityVO);

        return "redirect:/community";
    }
}
