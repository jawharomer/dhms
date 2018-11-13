package com.joh.dhms.dao;

import org.springframework.data.repository.CrudRepository;

import com.joh.dhms.model.Doctor;

public interface DoctorDAO extends CrudRepository<Doctor, Integer> {

}
