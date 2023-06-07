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
import com.hk.wepoor.model.UserMapper;
import com.hk.wepoor.service.CategoryService;
import com.hk.wepoor.service.CommunityService;
import com.hk.wepoor.vo.CategoryVO;
import com.hk.wepoor.vo.CommunityVO;
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

    // 게시글 리스트 불러오기
    @GetMapping("/community")
    public String getCommunity(Model model) {
        List<CommunityVO> communityList = communityService.selectAll();
        List<CommunityVO> replyList = communityService.selectAll();
        List<CategoryVO> categoryList = categoryService.selectAll();
        List<UserVO> userList = userMapper.getAllUsers();



        // for문을 통해 유저리스트의 넘버값을 조회
        for (CommunityVO community : communityList) {
            int userNo = community.getUser_no();
            
            // user_no를 기준으로 UserVO를 가져옴
            // userMapper에서 해당되는 userNo값을 UserVo user값에 할당
            UserVO user = userMapper.getUserByUserNo(userNo);
            
            if (user != null) {
                community.setUser_nickname(user.getUserNickname());
            } else {
                community.setUser_nickname("Unknown User");
            }
        }
        
        // for문을 통해 대댓글 리스트의 넘버값을 조회
        for (CommunityVO reply : replyList) {
            int userNo = reply.getUser_no();
            // user_no를 기준으로 UserVO를 가져옴
            UserVO user = userMapper.getUserByUserNo(userNo);
            
            if (user != null) {
                reply.setUser_nickname(user.getUserNickname());
            } else {
                reply.setUser_nickname("Unknown User");
            }
        }
        
        // 모든 유저를 검색
        model.addAttribute("users", userList);
        // System.out.println("userList는" + userList);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("replyList", replyList);
        model.addAttribute("communityList", communityList); // communityList값이 community html로 넘어감
        return "community/index";
    }

    // 게시글 생성하기
    @PostMapping("/community/create")
    public String createCommunity(@RequestParam("commu_content") String commu_content, HttpServletRequest request) {
        
        // 세션 기반
        HttpSession session = request.getSession(false);
        int userNo = (int) session.getAttribute("userNo");

        CommunityVO reply = new CommunityVO();
        reply.setUser_no(userNo);
        reply.setCommu_content(commu_content);

        communityService.create(reply);

        return "redirect:/community";
    }

    // 댓글 작성
    @PostMapping("/community/createReply")
    @ResponseBody
    public String createReply(@RequestParam("parentId") int top_commu_id, 
                            @RequestParam("commu_content") String commu_content,
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
    
        communityService.create(reply);

        return "redirect:/community";
    }

    // 댓글 수정
    @PostMapping("/community/update")
    @ResponseBody
    public String updatePost(@RequestParam("commu_content") String commu_content) {
        
        CommunityVO updatePost = new CommunityVO();
        updatePost.setCommu_content(commu_content);

        communityService.update(updatePost);

        return "redirect:/community";
    }

    // 댓글 삭제
    @PostMapping("/community/delete")
    public String delete(@RequestParam("delete") int commu_id,
                        @RequestParam("content") String content
                        ) {
        // 댓글 삭제 로직
        // 넘어온 commu_id값을 가져와 삭제 처리
        CommunityVO deletePost = new CommunityVO();
        deletePost.setCommu_id(commu_id);
        deletePost.setCommu_content(content);         
        communityService.update(deletePost);
        
        return "redirect:/community";
    }

    // 커뮤니티방 나가기
    @PostMapping("/community/leave")
    public String leave(@RequestParam("user_no") int userNo) {
        
        // int user_no = Integer.parseInt(userNo);
        UserVO user = userMapper.getUserByUserNo(userNo);
        user.setCateId(0);
        //
        userMapper.updateUser(user);

        System.out.println("로직 실행돼?" + userNo);
        return "redirect:/category";        
    }

}






