package com.hk.wepoor.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hk.wepoor.model.BankingFeign;
import com.hk.wepoor.model.UserMapper;
import com.hk.wepoor.vo.CardListVO;
import com.hk.wepoor.vo.CardCodeVO;
import com.hk.wepoor.vo.TokenVO;
import com.hk.wepoor.vo.UserVO;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private BankingFeign bankingFeign;

	JsonObject JsonuserToken;

	public String[] requesttoken(String code, String requesttoken) {

		// 사용자인증 & 토큰받기
		String userToken = bankingFeign.requestToken(code, "86dd1ec4-2394-4815-963f-0e5d2c28428a",
				"c3cb34d6-8b7d-4e3e-b2e7-aabf2f3d9f2d", "http://localhost/" + requesttoken, "authorization_code");

		JsonuserToken = JsonParser.parseString(userToken).getAsJsonObject();

		TokenVO tokenVO = new TokenVO();
		tokenVO.setUserSeqNo(JsonuserToken.get("user_seq_no").getAsString());
		tokenVO.setAccessToken(JsonuserToken.get("access_token").getAsString());
		tokenVO.setRefreshToken(JsonuserToken.get("refresh_token").getAsString());

		String[] tokens = new String[3];
		tokens[0] = tokenVO.getUserSeqNo();
		tokens[1] = tokenVO.getAccessToken();
		tokens[2] = tokenVO.getRefreshToken();

		return tokens;
	}

	public void requestuser() {
		// user_no, user_name, user_seq_no, access_token 을 UserVO에 담는다.
		List<UserVO> userVO = userMapper.getUserNo();
		// 가입된 회원수 만큼 반복문이 돌아간다.
		for (UserVO user : userVO) {
			// Access_token
			String toKen = "Bearer " + user.getAccessToken();
			// 사용자 일련번호
			String userSeqNo = user.getUserSeqNo();
			// bank_code_std,member_bank_code 를 cardCodeVO에 담는다.
			CardCodeVO cardCodeVO = bankingFeign.requestUserMe(toKen, userSeqNo);			
			// 가입된 회원수 만큼 반복문이 돌아간다.
			for (int num = 0; num < userVO.size(); num++) {
				// 카드사 대표코드
				String bankCodeStd = cardCodeVO.getInquiry_card_list().get(num).getBank_code_std();
				// 회원 금융회사 코드
				String memberBankCode = cardCodeVO.getInquiry_card_list().get(num).getMember_bank_code();
				// 5자리고정인 난수를 생성한다.
				int randomNumber = (int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000;
				// 거래고유번호(참가기관)
				String bankTranId = "M202201118" + "U" + randomNumber;
				// 카드거래내역을 cardListVO 담는다.
				CardListVO cardListVO = bankingFeign.requestCardBills(toKen, bankTranId, userSeqNo, bankCodeStd,
						memberBankCode, "202305", "0");
				// 카드거래내역 횟수
				int list_num = cardListVO.getBill_detail_list().size();
				// 커피마신 횟수
				int coffeeNum = 0;

				for (int i = 0; i < list_num; i++) {

					// 가맹점명
					String Merchant_name = cardListVO.getBill_detail_list().get(i).getMerchant_name_masked();
					
					// 가맹점명에 커피가 들어갔는지 확인
					if (Merchant_name.contains("커피")) {
						coffeeNum++;
					}
				}

				String userSuccess = "실패";

				if (coffeeNum < 5) {
					userSuccess = "성공";
				}

				System.out.println(cardCodeVO.getUser_name() + "님이 커피마신 횟수: " + coffeeNum + " " + userSuccess);

				break;
			}
			
		}
	}

	public void registerUser(UserVO userVO) {

		System.out.println(userVO.toString());
		userMapper.insertUser(userVO);
	}
}