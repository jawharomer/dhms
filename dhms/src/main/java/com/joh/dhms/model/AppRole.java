package com.joh.dhms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "APP_ROLES")
public class AppRole {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "I_APP_ROLE")
	private Integer id;

	@NotBlank(message = "Role name is blank")
	@Column(name = "ROLE_NAME", unique = true, nullable = false)
	private String name;

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

	@Override
	public String toString() {
		return "AppRole [id=" + id + ", name=" + name + "]";
	}

}
