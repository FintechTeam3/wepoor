(function($) {

	$('#memberPhoneCheck').click(function() {

		var to = $('input[name="userPhone"]').val();
		$.ajax({
			url: "/send-one",
			type: "POST",
			data: "to=" + to,
			dataType: "text",
			success: function(data) {
				const checkNum = data;
	
				alert('checkNum:' + checkNum);

				//인증하기 버튼 클릭 이벤트
				$('#certifyCheck').click(function() {
					const userNum = $('input[name="memberPhoneCertify"]').val();
					if (checkNum == userNum) {
						alert('인증 성공하였습니다.');
						phone_check = true;
					} else {
						alert('인증 실패하였습니다. 다시 입력해주세요.');
					}
				});

			},
			error: function() {
				alert("숫자 01012341234 형식으로 입력해주세요.")
			}
		});
	});

	$("#mailCheck").on("click", function() {
		var reg_id = /^[A-za-z0-9]{3,20}$/;
		var id = $('#userId').val(); //id값이 "id"인 입력란의 값을 저장
		if(!reg_id.test(id)){
			alert("잘못된 아이디형식입니다.(영어,숫자 조합만 가능)");
			$("#userId").focus();
			return false;
		}
		$.ajax({
			url: '/id_check', //Controller에서 요청 받을 주소
			type: 'post', //POST 방식으로 전달
			data: { "userId": id },
			success: function(cnt) { //컨트롤러에서 넘어온 cnt값을 받는다 
				if (cnt == 0) { //cnt가 0이면 사용 가능한 아이디 
					alert("사용 가능합니다.");
					current_id = id;
					
				} else { // cnt가 1일 경우 -> 이미 존재하는 아이디
					alert("중복입니다.");
					$('#id').val('');
				}
			},
			error: function() {
				alert("에러입니다");
			}
		});

	});

	$("#alert-success").hide();
	$("#alert-danger").hide();

	$("#userPwd, #pwdCheck").keyup(function() {
		var pwd1 = $("#userPwd").val();
		var pwd2 = $("#pwdCheck").val();
		if (pwd1 != "" || pwd2 != "") {
			if (pwd1 == pwd2) {
				$("#alert-success").show();
				$("#alert-danger").hide();
				$("#divid").text("=");
			} else {
				$("#alert-success").hide();
				$("#alert-danger").show();
				$("#divid").text("≠");

			}
		} else {
			$("#alert-success").hide();
			$("#alert-danger").hide();
			$("#divid").text("/");
		}
	});


	
	$("#submit").on("click", function(){
		var reg_name =  /^[가-힣]{2,6}$/;
		var reg_nick =  /^(?=.*[가-힣a-zA-Z0-9])[가-힣a-zA-Z0-9]{2,20}$/;
		
		var input_name = $("#userName").val();
		var input_nickname = $("#userNickname").val();
		var input_id = $('#userId').val();
		var pwd_check = $("#divid").text();
		
		// 이름
		if(!reg_name.test(input_name)){
			alert("잘못된 이름형식입니다.");
			$("#userName").focus();
			return false;
		}
		//닉네임
		if(!reg_nick.test(input_nickname)){
			alert("잘못된 닉네임 형식입니다.(특수문자,자음만 구성,공백 제외)");
			$("#userNickname").focus();
			return false;
		}
		
		//아이디
		if(current_id == "" || input_id !== current_id){
			alert("아이디 중복여부를 확인해주세요.");
			$('#userId').focus();
			return false;
		}
		
		//비밀번호
		if(pwd_check !== "="){
			alert("비밀번호를 확인해주세요.");
			$('#userPwd').focus();
			return false;
		}
		
		
		//핸드폰 인증
	/*	if(phone_check == false){
			
			alert("핸드폰 인증을 완료해주세요.")
			$('#phone').focus();
			return false;
		}
		return true;*/
		
	});
	
	
})(jQuery);
current_id = "";
phone_check = false;