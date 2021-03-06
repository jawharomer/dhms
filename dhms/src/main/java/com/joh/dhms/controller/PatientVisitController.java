package com.joh.dhms.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joh.dhms.domain.model.JsonResponse;
import com.joh.dhms.exception.CusDataIntegrityViolationException;
import com.joh.dhms.model.Patient;
import com.joh.dhms.model.PatientVisit;
import com.joh.dhms.service.AuthenticationFacadeService;
import com.joh.dhms.service.PatientService;
import com.joh.dhms.service.PatientVisitService;
import com.joh.dhms.service.ReportService;
import com.joh.dhms.validator.PatientVisitValidation;

@Controller()
@RequestMapping(path = "/patientVisits")
public class PatientVisitController {

	private static final Logger logger = Logger.getLogger(PatientVisitController.class);

	@Autowired
	private PatientVisitService patientVisitService;

	@Autowired
	private PatientService patientService;

	@Autowired
	private ReportService reportService;

	@GetMapping()
	public String getAllPatientVisit(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to, Model model) {
		logger.info("getAllPatientVisit->fired");

		logger.info("from=" + from);
		logger.info("to=" + to);

		Iterable<PatientVisit> patientVisits = patientVisitService.findAllByTimeBetween(from, to);

		logger.info("patientVisits=" + patientVisits);

		model.addAttribute("patientVisits", patientVisits);
		model.addAttribute("from", from);
		model.addAttribute("to", to);

		return "patientVisits";
	}

	@GetMapping(path = "/add/patient/{id}")
	public String getAddingPatientVisit(@PathVariable int id, Model model) throws JsonProcessingException {
		logger.info("getAddingPatientVisit->fired");
		logger.info("patientId=" + id);

		ObjectMapper mapper = new ObjectMapper();

		Patient patient = patientService.findOne(id);

		logger.info("patient=" + patient);

		PatientVisit patientVisit = new PatientVisit();
		patientVisit.setPatient(patient);

		List<String> examinations = reportService.findAllExamination();
		logger.info("examinations=" + examinations);
		model.addAttribute("jsonExaminations", mapper.writeValueAsString(examinations));

		model.addAttribute("jsonPatientVisit", mapper.writeValueAsString(patientVisit));

		return "addPatientVisit";
	}

	@PostMapping(path = "/add")
	@ResponseBody
	public JsonResponse addPatientVisit(
			@RequestBody @Validated(PatientVisitValidation.Insert.class) PatientVisit patientVisit,
			BindingResult result, Model model) {
		logger.info("addPatientVisit->fired");
		logger.info("patientVisit=" + patientVisit);

		if (result.hasErrors()) {
			throw new CusDataIntegrityViolationException("bad input is entered");
		} else {
			patientVisit = patientVisitService.save(patientVisit);

			JsonResponse jsonResponse = new JsonResponse();
			jsonResponse.setStatus(200);
			jsonResponse.setMessage("success");
			jsonResponse.setEtc("" + patientVisit.getId());

			return jsonResponse;
		}

	}

	@GetMapping(path = "/edit/{id}")
	public String getEditingPatientVisit(@PathVariable int id, Model model) throws JsonProcessingException {
		logger.info("getEditingPatientVisit->fired");
		logger.info("id=" + id);

		ObjectMapper mapper = new ObjectMapper();

		PatientVisit patientVisit = patientVisitService.findOne(id);
		logger.info("patientVisit=" + patientVisit);

		List<String> examinations = reportService.findAllExamination();
		logger.info("examinations=" + examinations);
		model.addAttribute("jsonExaminations", mapper.writeValueAsString(examinations));

		model.addAttribute("jsonPatientVisit", mapper.writeValueAsString(patientVisit));

		return "editPatientVisit";
	}

	@PostMapping(path = "/update")
	public String updatePatientVisit(@RequestBody @Valid PatientVisit patientVisit, BindingResult result) {
		logger.info("updatePatientVisit->fired");
		logger.info("patientVisit=" + patientVisit);

		logger.info("erorrs=" + result.getAllErrors());

		if (result.hasErrors()) {
			throw new CusDataIntegrityViolationException("bad input is entered");
		} else {

			patientVisit = patientVisitService.update(patientVisit);

			return "success";
		}

	}

	@GetMapping(path = "/attends/{id}")
	public String getPatientVisitAttends(@PathVariable int id, Model model) {
		logger.info("getPatientVisitAttends->fired");
		logger.info("id=" + id);

		PatientVisit patientVisit = patientVisitService.findOne(id);
		logger.info("patientVisit=" + patientVisit);

		model.addAttribute("patientVisit", patientVisit);

		return "patientVisitAttends";
	}
}
