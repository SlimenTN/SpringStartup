package com.devopsbuddy.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devopsbuddy.backend.service.EmailService;
import com.devopsbuddy.web.domain.frontend.FeedbackPojo;

@Controller
public class ContactController {
	
	/** The application logger */
	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(ContactController.class);
	
	/**
	 * The key which indentifies the feedback payload in the Model
	 */
	public static final String FEEDBACK_MODEL_VIEW = "feedback";
	
	/**
	 * The Contact Us view name
	 */
	public static final String CONTACT_US_VIEW_NAME = "contact/contact";
	
	@Autowired
	private EmailService emailService;
	
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contactGet(ModelMap model) {
		FeedbackPojo feedbackPojo = new FeedbackPojo();
		model.addAttribute(FEEDBACK_MODEL_VIEW, feedbackPojo);
		return CONTACT_US_VIEW_NAME;
	}
	
	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public String contactPost(@ModelAttribute(FEEDBACK_MODEL_VIEW) FeedbackPojo feedback) {
		LOG.debug("Feedback pojo content {}", feedback);
		emailService.sendFeedbackEmail(feedback);
		return CONTACT_US_VIEW_NAME;
	}
}
