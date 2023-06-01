(function($) {
	'use strict';
	/*==================================================================
		[ Daterangepicker ]*/
	try {
		$('.js-datepicker').daterangepicker({
			"singleDatePicker": true,
			"showDropdowns": true,
			"autoUpdateInput": false,
			locale: {
				format: 'DD/MM/YYYY'
			},
		});

		var myCalendar = $('.js-datepicker');
		var isClick = 0;

		$(window).on('click', function() {
			isClick = 0;
		});

		$(myCalendar).on('apply.daterangepicker', function(ev, picker) {
			isClick = 0;
			$(this).val(picker.startDate.format('DD/MM/YYYY'));

		});

		$('.js-btn-calendar').on('click', function(e) {
			e.stopPropagation();

			if (isClick === 1) isClick = 0;
			else if (isClick === 0) isClick = 1;

			if (isClick === 1) {
				myCalendar.focus();
			}
		});

		$(myCalendar).on('click', function(e) {
			e.stopPropagation();
			isClick = 1;
		});

		$('.daterangepicker').on('click', function(e) {
			e.stopPropagation();
		});


	} catch (er) { console.log(er); }
	/*[ Select 2 Config ]
		===========================================================*/

	try {
		var selectSimple = $('.js-select-simple');

		selectSimple.each(function() {
			var that = $(this);
			var selectBox = that.find('select');
			var selectDropdown = that.find('.select-dropdown');
			selectBox.select2({
				dropdownParent: selectDropdown
			});
		});

	} catch (err) {
		console.log(err);
	}

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
					} else {
						alert('인증 실패하였습니다. 다시 입력해주세요.');
					}
				});

			},
			error: function() {
				alert("에러")
			}
		});
	});

	$("#mailCheck").on("click", function() {

		var id = $('#userId').val(); //id값이 "id"인 입력란의 값을 저장
		console.log(id)
		$.ajax({
			url: '/id_check', //Controller에서 요청 받을 주소
			type: 'post', //POST 방식으로 전달
			data: { "userId": id },
			success: function(cnt) { //컨트롤러에서 넘어온 cnt값을 받는다 
				if (cnt == 0) { //cnt가 0이면 사용 가능한 아이디 
					alert("사용 가능합니다.");
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

	$(".input--style-5").keyup(function() {
		var pwd1 = $("#userPwd").val();
		var pwd2 = $("#pwdCheck").val();
		if (pwd1 != "" || pwd2 != "") {
			if (pwd1 == pwd2) {
				$("#alert-success").show();
				$("#alert-danger").hide();
				$("#submit").removeAttr("disabled");
			} else {
				$("#alert-success").hide();
				$("#alert-danger").show();
				$("#submit").attr("disabled", "disabled");

			}
		} else {
			$("#alert-success").hide();
			$("#alert-danger").hide();
			$("#submit").attr("disabled", "disabled");
		}
	});



})(jQuery);