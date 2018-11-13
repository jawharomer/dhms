package com.joh.dhms.service;

import java.util.Date;

import com.joh.dhms.model.Patient;

public interface PatientService {

	Patient findOne(int id);

	Patient update(Patient patient);

}
