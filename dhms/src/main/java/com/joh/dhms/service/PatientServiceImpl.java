package com.joh.dhms.service;

import java.util.Date;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.dhms.dao.PatientDAO;
import com.joh.dhms.model.Patient;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientDAO patientDAO;
	
	@Override
	public Patient findOne(int id) {
		return patientDAO.findOne(id);
	}

	@Override
	public Patient update(Patient patient) {
		if (patientDAO.findOne(patient.getId()) == null) {
			throw new EntityNotFoundException();
		}
		return patientDAO.save(patient);
	}

}
