package com.geekyants.mailengine.service;

import java.util.Date;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
@Service
public class EmailServiceImpl implements EmailService{

	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Override
	public void sendMailForCustomerCreation(String recipientName, String recipientEmail, Locale locale) throws MessagingException {
		
		 final Context ctx = new Context(locale);
		    ctx.setVariable("name", recipientName);
		    ctx.setVariable("subscriptionDate", new Date());
		    final MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8"); 
		    message.setSubject("Customer created");
		    message.setFrom("thymeleaf@example.com");
		    message.setTo(recipientEmail);
		    final String htmlContent = templateEngine.process("template.html", ctx);
		    message.setText(htmlContent, true);
		    javaMailSender.send(mimeMessage);
	}
}
