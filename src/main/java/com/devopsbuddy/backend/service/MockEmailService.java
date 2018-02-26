package com.devopsbuddy.backend.service;

import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService {
	/** The application logger */
	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(MockEmailService.class);
	
	@Override
	public void sendGenericEmailMessage(SimpleMailMessage simpleMailMessage) {
		LOG.debug("Simulating an email service...");
		LOG.info(simpleMailMessage.toString());
		LOG.debug("Email sent");
	}
}
