package com.joh.dhms.service;

import java.util.Date;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.dhms.dao.PatientVisitDAO;
import com.joh.dhms.model.PatientVisit;

@Service
public class PatientVisitServiceImpl implements PatientVisitService {

	@Autowired
	private PatientVisitDAO patientVisitDAO;

	@Override
	public PatientVisit save(PatientVisit patientVisit) {
		return patientVisitDAO.save(patientVisit);
	}

	@Override
	public PatientVisit findOne(int id) {
		return patientVisitDAO.findOne(id);
	}

	@Override
	public PatientVisit update(PatientVisit patientVisit) {
		if (patientVisitDAO.findOne(patientVisit.getId()) == null) {
			throw new EntityNotFoundException();
		}
		return patientVisitDAO.save(patientVisit);
	}

	@Override
	public Iterable<PatientVisit> findAllByTimeBetween(Date from, Date to) {
		return patientVisitDAO.findAllByTimeBetween(from, to);
	}

}
