package com.joh.dhms.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joh.dhms.domain.model.NotificationD;
import com.joh.dhms.service.AuthenticationFacadeService;
import com.joh.dhms.service.ReportService;

@Controller()
@RequestMapping(path = "/admin")
public class AdminController {

	private static final Logger logger = Logger.getLogger(AdminController.class);

	@Autowired
	private AuthenticationFacadeService authenticationFacadeService;

	@Autowired
	private ReportService reportService;

	@GetMapping()
	public String getAllNotification(Model model) {
		logger.info("getAllNotification->fired");

		List<NotificationD> notificationDs = reportService
				.findAdminNotifications(authenticationFacadeService.getAppUserDetail().getReference());
		model.addAttribute("notificationDs", notificationDs);

		return "notifications";
	}

}
