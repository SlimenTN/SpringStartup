package com.devopsbuddy.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.devopsbuddy.web.domain.frontend.FeedbackPojo;

public abstract class AbstractEmailService implements EmailService {
	
	/**
	 * At runtime spring will parse application.properties file and inject the value of this attribute
	 */
	@Value("${default.to.address}")
	private String defaultToAddress;
	
	/**
	 * Creates a Simple Mail Message from a Feedback Pojo
	 * @param feedbackPojo
	 * @return
	 */
	protected SimpleMailMessage preparedSimpleMailMessage(FeedbackPojo feedbackPojo) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(defaultToAddress);
		message.setFrom(feedbackPojo.getEmail());
		message.setSubject("[DevOps Buddy]: Feedback received from " + feedbackPojo.getFirstName() + " " + feedbackPojo.getLastName());
		message.setText(feedbackPojo.getFeedback());
		return message;
	}
	
	@Override
	public void sendFeedbackEmail(FeedbackPojo feedbackPojo) {
		sendGenericEmailMessage(preparedSimpleMailMessage(feedbackPojo));
	}
}
