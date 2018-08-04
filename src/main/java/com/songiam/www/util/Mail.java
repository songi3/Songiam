package com.songiam.www.util;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import com.songiam.www.controller.HomeController;

@EnableAsync
public class Mail {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	JavaMailSender mailSender;
	
	public Mail(JavaMailSender mailSender) {
		super();
		this.mailSender = mailSender;
	}

	// 메일 발송
	@Async
	public void sendMail(String name, String email, String subject, String content) {
		logger.info("sendMail");
		String setfrom = "songihomesmtp@gmail.com";
		String tomail = "wks4j1004@gmail.com";// 받는 사람 이메일
		String title = "songi home Message arrvied!"; // 제목
		String contentMessage = "name : " + name + "\n email : " + email + "\n subject : " + subject + "\n content : "
				+ content;// ?궡?슜

		try {
			logger.info("sendMail :: message");
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

			messageHelper.setFrom(setfrom); // 보내는사람 생략하거나 하면 정상작동을 안함
			messageHelper.setTo(tomail); // 받는사람 이메일
			messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
			messageHelper.setText(contentMessage); // 메일 내용

			logger.info("sendMail :: message " + message);
			mailSender.send(message);
		} catch (Exception e) {
			System.out.println("sendMail error :: " + e.getMessage());
		}
	}
}
