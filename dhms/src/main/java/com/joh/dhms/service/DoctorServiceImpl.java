package com.joh.dhms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.dhms.dao.DoctorDAO;
import com.joh.dhms.model.Doctor;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private DoctorDAO doctorDAO;

	@Override
	public Doctor findOne(int id) {
		return doctorDAO.findOne(id);
	}
}
