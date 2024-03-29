package com.hk.wepoor.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hk.wepoor.model.BankingFeign;
import com.hk.wepoor.model.FailMapper;
import com.hk.wepoor.model.PointMapper;
import com.hk.wepoor.model.UserMapper;
import com.hk.wepoor.vo.CardListVO;
import com.hk.wepoor.vo.FailVO;
import com.hk.wepoor.vo.CardCodeVO;
import com.hk.wepoor.vo.TokenVO;
import com.hk.wepoor.vo.UserVO;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private FailMapper failMapper;
	 
	@Autowired
	private PointMapper pointMapper;

	@Autowired
	private BankingFeign bankingFeign;

	JsonObject JsonuserToken;
	
	@Value("${Client-ID}")
	private String clientId;
	
	@Value("${Client-Secret}")
	private String clientSecret;

	public TokenVO requesttoken(String code, String requesttoken) {

		// 오픈뱅킹api 호출해서 사용자인증 하고 token을 json형식의 문자열로 가지고온다.
		String userToken = bankingFeign.requestToken(code, 
													clientId,
													clientSecret, 
													"http://localhost/" + requesttoken, "authorization_code");
		// 문자열을 json 형식으로 파싱한다.
		JsonuserToken = JsonParser.parseString(userToken).getAsJsonObject();
		
		// TokenVO에 담는다.
		TokenVO tokenVO = new TokenVO();
		tokenVO.setUserSeqNo(JsonuserToken.get("user_seq_no").getAsString());
		tokenVO.setAccessToken(JsonuserToken.get("access_token").getAsString());
		tokenVO.setRefreshToken(JsonuserToken.get("refresh_token").getAsString());
				
//		String[] tokens = new String[3];
//		tokens[0] = tokenVO.getUserSeqNo();
//		tokens[1] = tokenVO.getAccessToken();
//		tokens[2] = tokenVO.getRefreshToken();
//		
//		System.out.println("@@@@@@@@@@@@@@@@@@@@2"+tokens.toString());

		return tokenVO;
	}

	public void requestuser() {
		// user_no, user_name, user_seq_no, access_token 을 UserVO에 담는다.
		List<UserVO> userVO = userMapper.getCoffee();
		// 가입된 회원수 만큼 반복문이 돌아간다.
		for (UserVO user : userVO) {

			// Access_token
			String toKen = "Bearer " + user.getAccessToken();

			// 사용자 일련번호
			String userSeqNo = user.getUserSeqNo();


			// bank_code_std,member_bank_code 를 cardCodeVO에 담는다.
			CardCodeVO cardCodeVO = bankingFeign.requestUserMe(toKen, userSeqNo);

			// 가입된 회원수 만큼 반복문이 돌아간다.
			for (int num = 0; num < userVO.size();num++) {
				

				// 카드사 대표코드
				String bankCodeStd = cardCodeVO.getInquiry_card_list().get(num).getBank_code_std();

				// 회원 금융회사 코드
				String memberBankCode = cardCodeVO.getInquiry_card_list().get(num).getMember_bank_code();

				// 5자리고정인 난수를 생성한다.
				int randomNumber = (int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000;

				// 거래고유번호(참가기관)
				String bankTranId = "M202201118" + "U" + randomNumber;

				// 오늘기준 날짜추출 "yyyyMM" 형식으로
				LocalDate currentDate = LocalDate.now();

				DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyyMM");

				String formattedDate = currentDate.format(d);


				// 카드거래내역을 cardListVO 담는다.
				CardListVO cardListVO = bankingFeign.requestCardBills(toKen, bankTranId, userSeqNo, bankCodeStd,
						memberBankCode, formattedDate, "0");
				
				System.out.println("@@@@@@@@@@@@@@@@@@33333333333"+cardListVO.toString());
				// 카드거래내역 횟수
				int list_num = cardListVO.getBill_detail_list().size();

				
				// 커피마신 횟수
				int coffeeNum = 0;
				
				for (int i = 0; i < list_num; i++) {
					
					// 가맹점명
					String MerchaName = cardListVO.getBill_detail_list().get(i).getMerchant_name_masked();
					String paidDate = cardListVO.getBill_detail_list().get(i).getPaid_date();
					
					int ss = user.getUserNo();
					
					String periodString = userMapper.getCateCost(ss);
					
					String dateString = paidDate;
					// Parsing period dates
					String[] periodDates = periodString.split("~");

					String startDateString = periodDates[0].trim();

					String endDateString = periodDates[1].trim();

					// Parsing start and end dates
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");

					LocalDate startDate = LocalDate.parse(startDateString + "/" + dateString.substring(4), formatter);

					LocalDate endDate = LocalDate.parse(endDateString + "/" + dateString.substring(4), formatter);

					// Parsing target date
					LocalDate targetDate = LocalDate.parse(dateString.substring(0, 8),
							DateTimeFormatter.ofPattern("yyyyMMdd"));
					// Checking if target date is within the specified period
					if (targetDate.isAfter(startDate.minusDays(1)) && targetDate.isBefore(endDate.plusDays(1))
							|| MerchaName.contains("커피")) {
						coffeeNum++;
					}
				}

				int userSuccess;
				if (coffeeNum <= 2) {
					userSuccess = 1;

				} else {
					userSuccess = 0;
				}
				System.out.println(
						"@@@@@@@@@@@" + cardCodeVO.getUser_name() + "님이 커피마신 횟수: " + coffeeNum + " " + userSuccess);
				FailVO failVO = new FailVO();

				failVO.setUserNo(user.getUserNo());
				failVO.setFailNum(coffeeNum);

				failMapper.insert(failVO);

				UserVO userVO1 = new UserVO();
				userVO1.setUserSeqNo(userSeqNo);
				userVO1.setUserSuccess(userSuccess);

				userMapper.updateUserSuccess(userVO1);

				break;
			}

		}
		pointMapper.createTemporaryTable();
        pointMapper.insertPointsFromTemporaryTable();
        pointMapper.dropTemporaryTable();
		
	}

	public void registerUser(UserVO userVO) {

		System.out.println(userVO.toString());
		userMapper.insertUser(userVO);
	}

	// selectAll, update, delete, selectbyid,selectbyno

	public List<UserVO> selectAll() {
		return userMapper.getAllUsers();
	}

	public void updateUser(UserVO uservo) {
		userMapper.updateUser(uservo);
	}

	public void deleteUser(int userNo) {
		userMapper.deleteUser(userNo);
	}

	public UserVO selectByUserId(String userId) {
		return userMapper.getUserByUserId(userId);
	}

	public UserVO selectByUserNo(int userNo) {
		return userMapper.getUserByUserNo(userNo);
	}

}