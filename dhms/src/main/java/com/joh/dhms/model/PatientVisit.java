package com.joh.dhms.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.NotBlank;

import com.joh.dhms.validator.PatientVisitValidation;

@Entity
@Table(name = "DHMS_PATIENT_VISITS")
public class PatientVisit {

	@Column(name = "I_PATIENT_VISIT")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Valid()
	@ManyToOne()
	@JoinColumn(name = "I_PATIENT")
	private Patient patient;

	@NotBlank(groups = { PatientVisitValidation.Insert.class })
	@Column(name = "VISIT_CASE")
	private String visitCase;

	@Column(name = "VISIT_TIME", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp()
	@ColumnDefault("CURRENT_TIMESTAMP")
	private Date time;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "I_PATIENT_VISIT")
	private List<Examination> examinations = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "I_PATIENT_VISIT")
	private List<Medicine> medicines = new ArrayList<>();

	@Column(name = "RESULT")
	private String result;

	@Column(name = "NOTE")
	private String note;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getVisitCase() {
		return visitCase;
	}

	public void setVisitCase(String visitCase) {
		this.visitCase = visitCase;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public List<Examination> getExaminations() {
		return examinations;
	}

	public void setExaminations(List<Examination> examinations) {
		this.examinations = examinations;
	}

	public List<Medicine> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<Medicine> medicines) {
		this.medicines = medicines;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "PatientVisit [id=" + id + ", patient=" + patient + ", visitCase=" + visitCase + ", time=" + time
				+ ", examinations=" + examinations + ", medicines=" + medicines + ", result=" + result + ", note="
				+ note + "]";
	}

}
