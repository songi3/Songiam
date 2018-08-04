/*
	Epilogue by TEMPLATED
	templated.co @templatedco
	Released for free under the Creative Commons Attribution 3.0 license (templated.co/license)
*/

	skel.breakpoints({
		xlarge: '(max-width: 1680px)',
		large: '(max-width: 1280px)',
		medium: '(max-width: 980px)',
		small: '(max-width: 736px)',
		xsmall: '(max-width: 480px)',
		xxsmall: '(max-width: 360px)'
	});

	$(function() {

		var	$window = $(window),
			$body = $('body');

		// Disable animations/transitions until the page has loaded.
			$body.addClass('is-loading');

			$window.on('load', function() {
				window.setTimeout(function() {
					$body.removeClass('is-loading');
				}, 100);
			});

		// Fix: Placeholder polyfill.
			$('form').placeholder();

		// Prioritize "important" elements on medium.
			skel.on('+medium -medium', function() {
				$.prioritize(
					'.important\\28 medium\\29',
					skel.breakpoint('medium').active
				);
			});

		// Items.
			$('.item').each(function() {

				var $this = $(this),
					$header = $this.find('header'),
					$a = $header.find('a'),
					$img = $header.find('img');

				// Set background.
					$a.css('background-image', 'url(' + $img.attr('src') + ')');

				// Remove original image.
					$img.remove();

			});

	});
	
	$(document).ready(function() {
		checkSession();
		 setTextAnimation();
	});
	
	/***************************************************************************
	 * 함수
	 **************************************************************************/
	
	// 애니메이션 해당 div 이동
	function animateMoveDiv(divName, duration) {
		var div = $(divName);
		var divOffsetTop = div.offset().top;
		$('html, body').animate({
			scrollTop : divOffsetTop
		}, duration); // 400
	}
	
	// 컨트롤러 호출
	function callController(url) {
		$(location).attr('href', url);
	}
	
	// 프로젝트, 커멘트 디테일 페이지 이동
	function commentDetailEvent(e){
		var commentIndex = e.id;
		callController('/songihome/commentDetail?index=' + commentIndex);
	}
	
	function checkSession() {
		$.ajax({

			url : "/songihome/sessionCheck",
			type : "get",
			success : function(sessionCheck) {
				if (!sessionCheck) { // 로그아웃 중
					setLogin();

				} else { // 로그인 중
					setLogout();
				}
			}
		});
	}
	
	function setLogout() {
		$('.login-btn').addClass("on");
		$('.logout-btn').addClass("on");
		$('#comment-edit-btn').addClass("on");
		$('.project-btn').addClass("on");
		
	}

	function setLogin(){
		$('.login-btn').removeClass("on");
		$('.logout-btn').removeClass("on");
		$('#comment-edit-btn').removeClass("on");
		$('.project-btn').removeClass("on");
	}
	
	
	/***************************************************************************
	 * 클릭 이벤트 각종 버튼, 리소스 클릭 시 
	 **************************************************************************/
	
	// TOP 버튼
	$(function() {
		$(window).scroll(function() {
			if ($(this).scrollTop() > 500) {
				$('.topMoveBtn').fadeIn();
			} else {
				$('.topMoveBtn').fadeOut();
			}
		});

		$(".topMoveBtn").click(function() {
			topButtonClickEvent();
		});
		return false;
	});
	
	// send message 클릭
	function sendMessageButtonClickEvent(){
		var name = $("#name").val();
		var email = $("#email").val();
		var subject = $("#subject").val();
		var content = $("#content").val();

		$.ajax({

			url : "/songihome/sendContactMessage",
			type : "post",
			data : {
				"name" : name,
				"email" : email,
				"subject" : subject,
				"content" : content
			},
			success : function(success) {

				alertify.alert("알림", "전송완료", function() {
					alertify.success('메세지가 전송되었습니다.');
				});
			},
			beforeSend : function() { // 이미지 보여주기 처리
				$(".loading-image").addClass("on"); // 로딩 이미지 보이게
				$(".mask").addClass("on"); //마스크 보이게
				$(".mask").fadeTo("slow", 0.4); // 배경 흐리게 효과
			},
			complete : function() { // 이미지 감추기 처리
				$(".loading-image").removeClass("on");
				$(".mask").fadeTo("slow", 0); // 마스크 없애기
				$(".mask").removeClass("on");
			}

		});
		
		$("#name").val("");
		$("#email").val("");
		$("#subject").val("");
		$("#content").val("");

	}
		
	// 로그인 버튼 클릭
	function loginCheckButtonClickEvent(){
		
		var id = $('#id').val();
		var password = $('#password').val();
		
		// 로그인 버튼 클릭시	
		$.ajax({
			url : "/songihome/loginCheck",
			type : "post",
			data : {
						"id" : id,
						"password" : password
					},
			success : 
				function(userMap) {
					if (!userMap) {
						alertify.alert("알림", "로그인 정보가 없습니다.");
					}
					
					else {
						var user = userMap.user;	
						callController('/songihome/?id=' + user.id +"&password=" + user.password);
					}
			}
		});
	}
	
	//이력 더보기 버튼 클릭
	function historyButtonClickEvent(){
		callController('/songihome/showHistory');
	}
	
	//프로젝트 버튼 클릭 (Comment 생성)
	function projectButtonClickEvent(){
		callController('/songihome/createComment');
	}
	
	function topButtonClickEvent(){
		animateMoveDiv('body', 400);
	}
	
	function homeButtonClickEvent(){
		callController('/songihome/');
	}
	
	function loginButtonClickEvent(){
		callController('/songihome/login');
	}
	
	function logoutButtonClickEvent(){
		callController('/songihome/logout');
	}
	
	function commentEditButtonClickEvent(){
		var index = $('.comment-index').text();
		callController('/songihome/commentEdit?index=' + index);	
	}

	
	/***************************************************************************
	 * 애니메이션
	 **************************************************************************/
	
	function bounceAnimation(item){
		$(item).jAnimate('bounce');
	}
	
	function fadeInUpAnimation(item){
		$(item).jAnimate('fadeInUp');
	}
	
	function pulseAnimation(item){
		$(item).jAnimate('pulse');
	}
	
	function setTextAnimation(){
		$(".main").mouseenter(function(){
			bounceAnimation(".main h2");
		  });
		$("#header h1").mouseenter(function(){
			pulseAnimation("#header h1");
		  });
	}
