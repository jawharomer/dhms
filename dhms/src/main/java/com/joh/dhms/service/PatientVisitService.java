package com.joh.dhms.service;

import java.util.Date;

import com.joh.dhms.model.PatientVisit;

public interface PatientVisitService {

	PatientVisit save(PatientVisit patientVisit);

	PatientVisit findOne(int id);

	PatientVisit update(PatientVisit patientVisit);

	Iterable<PatientVisit> findAllByTimeBetween(Date from, Date to);
}
