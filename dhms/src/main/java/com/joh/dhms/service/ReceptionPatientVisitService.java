package com.joh.dhms.service;

import java.util.Date;
import java.util.List;

import com.joh.dhms.model.ReceptionPatientVisit;

public interface ReceptionPatientVisitService {

	List<ReceptionPatientVisit> findAllDoctorPatientVisit(int id, Date from, Date to);

}
