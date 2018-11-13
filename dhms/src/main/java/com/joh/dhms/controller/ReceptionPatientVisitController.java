package com.joh.dhms.controller;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.joh.dhms.model.Doctor;
import com.joh.dhms.model.ReceptionPatientVisit;
import com.joh.dhms.service.AuthenticationFacadeService;
import com.joh.dhms.service.DoctorService;
import com.joh.dhms.service.ReceptionPatientVisitService;

@Controller()
@RequestMapping(path = "/receptionPatientVisits")
public class ReceptionPatientVisitController {

	private static final Logger logger = Logger.getLogger(ReceptionPatientVisitController.class);

	@Autowired
	private AuthenticationFacadeService authenticationFacadeService;

	@Autowired
	private ReceptionPatientVisitService receptionPatientVisitService;

	@Autowired
	private DoctorService doctorService;

	@GetMapping()
	public String getAllReceptionPatientVisits(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to, Model model) {
		logger.info("getAllReceptionPatientVisits->fired");
		logger.info("receptionDoctorId=" + authenticationFacadeService.getAppUserDetail().getReference());

		Doctor doctor = doctorService.findOne(authenticationFacadeService.getAppUserDetail().getReference());
		logger.info("doctor=" + doctor);

		logger.info("from=" + from);
		logger.info("to=" + to);

		List<ReceptionPatientVisit> receptionPatientVisits = receptionPatientVisitService
				.findAllDoctorPatientVisit(authenticationFacadeService.getAppUserDetail().getReference(), from, to);

		logger.info("receptionPatientVisits=" + receptionPatientVisits);

		model.addAttribute("receptionPatientVisits", receptionPatientVisits);
		model.addAttribute("from", from);
		model.addAttribute("to", to);

		model.addAttribute("doctor", doctor);

		return "receptionPatientVisits";
	}

}
