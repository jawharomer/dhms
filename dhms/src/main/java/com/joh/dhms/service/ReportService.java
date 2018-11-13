package com.joh.dhms.service;

import java.util.List;

import com.joh.dhms.domain.model.NotificationD;

public interface ReportService {

	List<String> findAllChronicDisease();

	List<String> findAllExamination();

	List<NotificationD> findAdminNotifications(int doctorId);

}
