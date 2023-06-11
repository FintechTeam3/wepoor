var userId;
(function ($) {
    "use strict";


    /*==================================================================
    [ Focus Contact2 ]*/
    $('.input100').each(function(){
        $(this).on('blur', function(){
            if($(this).val().trim() != "") {
                $(this).addClass('has-val');
            }
            else {
                $(this).removeClass('has-val');
            }
        })    
    })
  
  
    /*==================================================================
    [ Validate ]*/
    var input = $('.validate-input .input100');

    $('.validate-form').on('submit',function(){
        var check = true;

        for(var i=0; i<input.length; i++) {
            if(validate(input[i]) == false){
                showValidate(input[i]);
                check=false;
            }
        }

        return check;
    });


    $('.validate-form .input100').each(function(){
        $(this).focus(function(){
           hideValidate(this);
        });
    });

    function validate (input) {
        if($(input).attr('type') == 'email' || $(input).attr('name') == 'email') {
            if($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
                return false;
            }
        }
        else {
            if($(input).val().trim() == ''){
                return false;
            }
        }
    }

    function showValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).addClass('alert-validate');
    }

    function hideValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-validate');
    }
    
    
    //kakao_login
   
   
  
   
   // 페이지가 로드될 때 저장된 아이디를 가져와서 입력 필드에 설정합니다.
      var storedUsername = localStorage.getItem("username");
      if (storedUsername) {
        $("#username").val(storedUsername);
        $("#ckb1").prop("checked", true);
        $(".input100").addClass('has-val');
      }

      // 아이디 기억하기 체크박스의 변경 이벤트를 처리합니다.
      $("#ckb1").change(function() {
        if ($(this).is(":checked")) {
          // 아이디 기억하기가 체크되면 사용자 이름을 로컬 스토리지에 저장합니다.
          var username = $("#username").val();
          localStorage.setItem("username", username);
        } else {
          // 아이디 기억하기가 체크 해제되면 로컬 스토리지에서 사용자 이름을 제거합니다.
          localStorage.removeItem("username");
        }
      });
   
   	
   	$("#modal_link").on("click", function(){
		$("#findPassword").modal();
	});
   
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
   
   
   $("#modalSubmit").on("click", function(){
		userId = $("#userId").val();
		var phone = $("#phone").val();
		console.log(userId);
		if(userId == ""){
			alert("아이디를 입력해주세요.")
			$('#userId').focus();
			return false;
		}
		
	/*	//핸드폰 인증
		if(phone_check == false){
			
			alert("핸드폰 인증을 완료해주세요.")
			$('#phone').focus();
			return false;
			}*/
		
		
		$.ajax({
			   url: '/findPassword',
			   data: {'userId': userId, "phone": phone},
			   type: "POST",
			   success: function(result) {
				   
				   if(result == true) {
					   $('#findPassword').modal('hide')
					   $("#resetPassword").modal();
				   } else {
					   alert("등록된 개인정보가 맞지 않습니다.");
				   }
			   }
		   });
		
	});
   	
   	
   	$("#resetbtn").on("click", function(){
		  var pwd_check = $("#divid").text();
		  var password = $("#userPwd").val();
		  
		  
		  if(pwd_check !== "="){
			alert("비밀번호를 확인해주세요.");
			$('#userPwd').focus();
			return false;
		}
		   
		 $.ajax({
			   url: '/resetPassword',
			   data: {'userId': userId, "password": password},
			   type: "POST",
			   success: function(result) {
				   alert("비밀번호가 성공적으로 바뀌었습니다.")
				   $('#resetPassword').modal('hide')
				   
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
	
	
	
	
   
})(jQuery);
phone_check = false;