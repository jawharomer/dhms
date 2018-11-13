package com.joh.dhms.dao;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import com.joh.dhms.model.Patient;

public interface PatientDAO extends CrudRepository<Patient, Integer> {
}
