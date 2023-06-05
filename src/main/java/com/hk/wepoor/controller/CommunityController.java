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

import com.hk.wepoor.jwt.Jwt;
import com.hk.wepoor.jwt.JwtTokenDecoder;
import com.hk.wepoor.service.CommunityService;
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
    private String key;

    // 게시글 리스트 불러오기
    @GetMapping("/community")
    public String getCommunity(Model model) {
        List<CommunityVO> communityList = communityService.selectAll();

        // System.out.println("커뮤아이디" + communityList);
        // // 대댓글 리스트 가져오기
        for (CommunityVO community : communityList) {
            // int topCommuId = community.getTop_commu_id(); // 게시글의 부모글을 가져옴
            List<CommunityVO> replyList = communityService.selectAll();
            // community.setCommu_id(replyList);
            // community.setRList(replyList); // 대댓글 리스트를 게시글에 설정
            // System.out.println("커뮤아이디 찍혀?" + replyList);
            model.addAttribute("replyList", replyList);
        }

    
        model.addAttribute("communityList", communityList); // communityList값이 community html로 넘어감
        return "community/index";
    }

    // 게시글 생성하기
    @PostMapping("/community/create")
    public String createCommunity(CommunityVO communityVO) {
        
        communityService.create(communityVO);

        return "redirect:/community";
    }

    // 댓글 작성
    @PostMapping("/community/createReply")
    @ResponseBody
    public String createReply(@RequestParam("parentId") int top_commu_id, 
                            @RequestParam("commu_content") String commu_content,
                            @RequestParam("useNo") String useNo) {
             // JWT 토큰 값을 쿠키에서 가져오기
        // Cookie[] cookies = request.getCookies();
        // String jwtToken = null;
        // if (cookies != null) {
        //     for (Cookie cookie : cookies) {
        //         if (cookie.getName().equals("jwtToken")) {
        //             jwtToken = cookie.getValue();
        //             break;
        //         }
        //     }
        // }

        // System.out.println("여기부터 jwt");
        // // JWT 토큰 값을 사용하여 유저 정보 추출
        // if (jwtToken != null) {
        //     Jws<Claims> jws = Jwt.parseToken(jwtToken);
        //     Claims claims = jws.getBody();
        //     String userId = claims.get("userId", String.class);
        //     int userNo = claims.get("userNo", Integer.class);
        //     System.out.println("여기 되나?");
        //     // 유저 정보 및 게시물 정보를 사용하여 처리 로직을 작성
        //     // ...
       
        // System.out.println("상위 게시물 값입니다: " + top_commu_id);
        System.out.println("유저 아이디 값입니다: " + useNo);
        // System.out.println("유저 번호 값입니다: " + userNo);
        // System.out.println("컨텐트 값입니다: " + commu_content);
        
        // jwt 토큰값을 받아와서 암호를 해독,
        // 해독한 내용을 토대로 유저의 pk값을 도출
        // user의 user_id값을 통해 user의 넘버를 조회
        // System.out.println("상위 게시물 값입니다: " + top_commu_id);
        // // System.out.println("상위 게시물 값입니다: " + userId);

        // System.out.println("컨텐트 값입니다: " + commu_content);
        
        // // 부모 댓글 조회
        // CommunityVO parentComment = communityService.select(top_commu_id);
        // model.addAttribute("communityList", communityCommentList);
        
        // if (parentComment == null) {
        //     return "error"; // 부모 댓글이 존재하지 않으면 에러 처리
        // }
        System.out.println("user_id" + useNo);
        
        // 대댓글 생성
        CommunityVO reply = new CommunityVO();
        reply.setTop_commu_id(top_commu_id);

        reply.setCommu_content(commu_content);
    
        communityService.create(reply);


        return "redirect:/community";
        }
}






