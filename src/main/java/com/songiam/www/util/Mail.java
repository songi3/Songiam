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

	// ���� �߼�
	@Async
	public void sendMail(String name, String email, String subject, String content) {
		logger.info("sendMail");
		String setfrom = "songihomesmtp@gmail.com";
		String tomail = "wks4j1004@gmail.com";// �޴� ��� �̸���
		String title = "songi home Message arrvied!"; // ����
		String contentMessage = "name : " + name + "\n email : " + email + "\n subject : " + subject + "\n content : "
				+ content;// ?��?��

		try {
			logger.info("sendMail :: message");
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

			messageHelper.setFrom(setfrom); // �����»�� �����ϰų� �ϸ� �����۵��� ����
			messageHelper.setTo(tomail); // �޴»�� �̸���
			messageHelper.setSubject(title); // ���������� ������ �����ϴ�
			messageHelper.setText(contentMessage); // ���� ����

			logger.info("sendMail :: message " + message);
			mailSender.send(message);
		} catch (Exception e) {
			System.out.println("sendMail error :: " + e.getMessage());
		}
	}
}
