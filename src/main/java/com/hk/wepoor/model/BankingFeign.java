package com.hk.wepoor.model;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.hk.wepoor.vo.CardListVO;
import com.hk.wepoor.vo.CardCodeVO;


@FeignClient(name="feign", url="https://testapi.openbanking.or.kr")
public interface BankingFeign {
	
	@PostMapping(path = "/oauth/2.0/token", consumes = "application/x-www-form-urlencoded", produces = "application/json")
	public String requestToken(@RequestParam("code") String code ,
	                               		  @RequestParam("client_id") String client_id,
	                               		  @RequestParam("client_secret") String client_secret, 
	                               		  @RequestParam("redirect_uri") String redirect_uri, 
	                               		  @RequestParam("grant_type") String grant_type);
 
   @GetMapping("/v2.0/cards/bills/detail")
   public CardListVO requestCardBills(@RequestHeader("Authorization") String Authorization ,
                               		  		  @RequestParam("bank_tran_id") String bank_tran_id,
		                               		  @RequestParam("user_seq_no") String user_seq_no, 
		                               		  @RequestParam("bank_code_std") String bank_code_std, 
		                               		  @RequestParam("member_bank_code") String member_bank_code, 
											  @RequestParam("charge_month") String charge_month, 
											  @RequestParam("settlement_seq_no") String settlement_seq_no); 
   
   @GetMapping("/v2.0/user/me")
   public CardCodeVO requestUserMe(
		   									@RequestHeader("Authorization") String Authorization,
		   									@RequestParam("user_seq_no") String user_seq_no
		   									 
		   );
   
   
}