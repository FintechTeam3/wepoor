package com.hk.wepoor.controller;

import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hk.wepoor.jwt.Jwt;
import com.hk.wepoor.jwt.JwtTokenDecoder;
import com.hk.wepoor.model.PayMapper;
import com.hk.wepoor.model.UserMapper;
import com.hk.wepoor.service.CategoryService;
import com.hk.wepoor.service.CommunityService;
import com.hk.wepoor.service.PayService;
import com.hk.wepoor.vo.CategoryVO;
import com.hk.wepoor.vo.CommunityVO;
import com.hk.wepoor.vo.PayVO;
import com.hk.wepoor.vo.UserVO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.IOException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CommunityController {
	
    // CommunityService로 부터 비즈니스 로직을 끌고와서 컨트롤러로 전송
	@Autowired
    private CommunityService communityService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PayService payService;

    // 게시글 리스트 불러오기
    @GetMapping("/community")
    public String getCommunity(Model model,
                        @RequestParam("cate_id") int cate_id,
                        @RequestParam("userNo") int userNo,
                        HttpServletRequest request) {
        // List<CommunityVO> communityList = communityService.selectAll();

        List<CommunityVO> communityList = communityService.getUserNickName(userNo);
        List<CommunityVO> replyList = communityService.getUserNickName(userNo);
        List<CategoryVO> categoryList = categoryService.selectAll();
        

        UserVO userVO = userMapper.getUserByUserNo(userNo);
        request.setAttribute("userNickName", userVO.getUserNickname());

    
        String categoryName = categoryService.select_category(cate_id);
        
        model.addAttribute("categoryName", categoryName);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("replyList", replyList);
        model.addAttribute("communityList", communityList); // communityList값이 community html로 넘어감
        model.addAttribute("cate_id", cate_id);

        // model.addAttribute("real_user", real_user);
        return "community/index";
    }

    // 게시글 생성하기
    @PostMapping("/community/create")
    public String createCommunity(@RequestParam("commu_content") String commu_content,
                                @RequestParam("cate_id") int cate_id,
                                HttpServletRequest request) {
        
        // System.out.println("들어와?" + request);
        // System.out.println("들어와?" + cate_id);
        // System.out.println("들어와?" + commu_content);
        // 세션 기반
        HttpSession session = request.getSession(false);
        int userNo = (int) session.getAttribute("userNo");
        
        // System.out.println("userNo야" + userNo);
        CommunityVO reply = new CommunityVO();
        reply.setUser_no(userNo);
        reply.setCommu_content(commu_content);
        reply.setCate_id(cate_id);
                                    
        communityService.create(reply);

        return "redirect:/community?userNo="+userNo+"&cate_id="+cate_id;
    }

    // 댓글 작성
    @PostMapping("/community/createReply")
    public String createReply(@RequestParam("parentId") int top_commu_id, 
                            @RequestParam("commu_content") String commu_content,
                            @RequestParam("cate_id") int cate_id,
                            HttpServletRequest request) {
        
        // 세션 기반
        HttpSession session = request.getSession(false);
        int userNo = (int) session.getAttribute("userNo");

        System.out.println("유저 넘버는" + userNo);
        
        // 대댓글 생성
        CommunityVO reply = new CommunityVO();
        reply.setTop_commu_id(top_commu_id);
        reply.setUser_no(userNo);
        reply.setCommu_content(commu_content);
        // cate id 값도 넣기
        reply.setCate_id(cate_id);

        
    
        communityService.create(reply);

        return "redirect:/community?userNo="+userNo+"&cate_id="+cate_id;
    }

    // 댓글 수정
    @PostMapping("/community/update")
    public String updatePost(@RequestParam("commu_content") String commu_content,
                            @RequestParam("commu_id") int commu_id,
                            @RequestParam("cate_id") int cate_id,
                            HttpServletRequest request) {
        
        HttpSession session = request.getSession(false);
        int userNo = (int) session.getAttribute("userNo");

        CommunityVO updatePost = new CommunityVO();
        updatePost.setCommu_id(commu_id);
        updatePost.setCommu_content(commu_content);
        updatePost.setCate_id(cate_id);

        communityService.update(updatePost);

        return "redirect:/community?userNo="+userNo+"&cate_id="+cate_id;
    }

    // 댓글 삭제
    @PostMapping("/community/delete")
    public String delete(@RequestParam("delete") int commu_id,
                        @RequestParam("content") String content,
                        @RequestParam("cate_id") String cate_id,
                        HttpServletRequest request
                        ) {
        // 댓글 삭제 로직
        // 넘어온 commu_id값을 가져와 삭제 처리

        HttpSession session = request.getSession(false);
        int userNo = (int) session.getAttribute("userNo");

        CommunityVO deletePost = new CommunityVO();
        deletePost.setCommu_id(commu_id);
        deletePost.setCommu_content(content);         
        communityService.update(deletePost);
        
        return "redirect:/community?userNo="+userNo+"&cate_id="+cate_id;
    }

    // 커뮤니티방 나가기
    @PostMapping("/community/leave")
    public String leave(@RequestParam("user_no") int userNo,
                        @RequestParam("cate_id") int cate_id
                        ) {
        
        PayVO payVO = new PayVO();
        payVO.setCate_id(0);
        int good = payService.updateLeave(payVO);
        // if (good == 1) {
        //     System.out.println(good);
        // }
        System.out.println(good + "good입니다");
        
        return "redirect:/roomdetail";        
    }

    // 카테고리에 대한 입장 가능 여부 확인
    private boolean checkCategoryAccess(int userNo,
                                        int cateId) {
        // 여기에 해당 사용자(userNo)가 해당 카테고리(cateId)에 대한 입장 가능 여부를 판단하는 로직을 작성
        
        // 1. request로 넘어온 유저를 기반으로 현재 유저값 받아오기
        // 2. 넘어온 cateId값으로 현재 유저가 가지고 있는 cateId값과 비교해서 true false 반환
    
        // // 세션 기반
        // HttpSession session = request.getSession(false);
        // int userNo = (int) session.getAttribute("userNo");
        
        // 해당 유저를 찾아야함
        int validCateId = userMapper.getUserCateId(userNo);

        System.out.println(validCateId + "validCateId입니다");
        // 넘어온 cateId와 user가 가지고 있는 cateId가 일치해야함
        if (validCateId == cateId) {
            return true;
        } else {
            return false;
        }
    }
}






