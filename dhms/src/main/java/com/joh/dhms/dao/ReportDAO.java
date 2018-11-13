package com.joh.dhms.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.joh.dhms.domain.model.NotificationD;
import com.joh.dhms.domain.model.NotificationD.NotificationType;

@Component
public class ReportDAO {
	@PersistenceContext
	private EntityManager em;

	public List<NotificationD> findAdminNotifications(int doctorId) {

		List<NotificationD> notificationDs = new ArrayList<>();

		// Notification-1

		Query query = em.createNativeQuery(
				"SELECT COUNT(*)  FROM PATIENT_VISITS WHERE I_DOCTOR=?1 AND VISIT_DATE BETWEEN CURDATE() AND CURDATE()+INTERVAL 1 DAY;");
		query.setParameter(1, doctorId);
		Object totalPatientVisitResult = query.getSingleResult();

		int totalPatientVisit = 0;
		if (totalPatientVisitResult != null)
			totalPatientVisit = Integer.parseInt("" + totalPatientVisitResult);

		//
		NotificationD not1 = new NotificationD();
		not1.setTitle("Total Today Patient Visit");
		not1.setEtc("" + totalPatientVisit);
		not1.setMessage("Total Number of patients visit in today at reception");

		not1.setNotificationType(NotificationType.INFO);

		notificationDs.add(not1);

		// Notification-2

		query = em.createNativeQuery(
				"SELECT SUM((CASE WHEN DISCOUNT_AMOUNT IS NULL THEN TOTAL_PRICE ELSE TOTAL_PRICE - DISCOUNT_AMOUNT * TOTAL_PRICE END)) AS TOTAL_PAYMENT\n"
						+ "FROM PATIENT_VISITS\n"
						+ "WHERE I_DOCTOR = ?1 AND VISIT_DATE BETWEEN CURDATE() AND CURDATE() + INTERVAL 1 DAY;");

		query.setParameter(1, doctorId);

		Object totalTodayPaymentResult = query.getSingleResult();

		double totalTodayPayment = 0;
		if (totalTodayPaymentResult != null)
			totalTodayPayment = Double.parseDouble("" + totalTodayPaymentResult);

		//
		NotificationD not2 = new NotificationD();
		not2.setTitle("Total today Payment");
		not2.setEtc("" + totalTodayPayment);
		not2.setMessage("Total today payment at reception");

		not2.setNotificationType(NotificationType.INFO);

		notificationDs.add(not2);

		return notificationDs;

	}

	public List<String> findAllChronicDisease() {
		List<String> chronicDiseases = new ArrayList<>();

		Query query = em.createNativeQuery("SELECT DISTINCT DISEASE_NAME FROM CHRONIC_DISEASES ORDER BY DISEASE_NAME;");

		List<String> totalChronicDisease = query.getResultList();
		chronicDiseases.addAll(totalChronicDisease);
		return chronicDiseases;
	}

	public List<String> findAllExamination() {
		List<String> examinations = new ArrayList<>();

		Query query = em.createNativeQuery("SELECT  DISTINCT EXAMINATION_NAME FROM EXAMINATIONS;");

		List<String> totalExaminations = query.getResultList();
		examinations.addAll(totalExaminations);
		return totalExaminations;
	}

}
