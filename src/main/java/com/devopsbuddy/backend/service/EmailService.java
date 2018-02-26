package com.devopsbuddy.backend.service;

import org.springframework.mail.SimpleMailMessage;

import com.devopsbuddy.web.domain.frontend.FeedbackPojo;

public interface EmailService {

	/**
	 * Sends email with the content of feedback pojo
	 * @param feedbackPojo
	 */
	public void sendFeedbackEmail(FeedbackPojo feedbackPojo);
	
	/**
	 * Sends email with the content of this Simple Mail Message object
	 * @param simpleMailMessage
	 */
	public void sendGenericEmailMessage(SimpleMailMessage simpleMailMessage);
}
