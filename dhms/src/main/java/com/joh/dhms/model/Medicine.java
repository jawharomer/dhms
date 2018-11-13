package com.joh.dhms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DHMS_PATIENT_MEDICINES")
public class Medicine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "I_PATIENT_MEDICINE")
	private Integer id;

	@Column(name = "MEDICINE_NAME")
	private String name;

	@Column(name = "DOSE")
	private String dose;

	@Column(name = "SCHEDULE")
	private String schedule;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	@Override
	public String toString() {
		return "Medicine [id=" + id + ", name=" + name + ", dose=" + dose + ", schedule=" + schedule + "]";
	}

}
