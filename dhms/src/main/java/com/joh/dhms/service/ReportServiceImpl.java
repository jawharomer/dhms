package com.joh.dhms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.dhms.dao.ReportDAO;
import com.joh.dhms.domain.model.NotificationD;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private ReportDAO reportDAO;

	@Override
	public List<NotificationD> findAdminNotifications(int doctorId) {
		return reportDAO.findAdminNotifications(doctorId);
	}

	@Override
	public List<String> findAllChronicDisease() {
		return reportDAO.findAllChronicDisease();
	}
	

	@Override
	public List<String> findAllExamination() {
		return reportDAO.findAllExamination();
	}

}
