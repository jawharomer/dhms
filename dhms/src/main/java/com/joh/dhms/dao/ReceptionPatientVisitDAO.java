package com.joh.dhms.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.joh.dhms.model.ReceptionPatientVisit;

public interface ReceptionPatientVisitDAO extends CrudRepository<ReceptionPatientVisit, Integer> {
	List<ReceptionPatientVisit> findAllByDoctorIdAndVisitDateBetween(int id, Date from, Date to);

}
