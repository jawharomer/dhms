package com.joh.dhms.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "APP_USERS")
public class AppUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "I_APP_USER")
	private Integer id;

	@NotBlank(message = "name is blank")
	@Column(name = "USER_NAME", unique = true, nullable = false)
	private String name;

	@NotBlank(message = "password is blank")
	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "USERS_ROLES", joinColumns = @JoinColumn(name = "I_APP_USER"), inverseJoinColumns = @JoinColumn(name = "I_APP_ROLE"))
	private Collection<AppRole> roles;

	@Column(name = "REFERENCE")
	private Integer reference;

	@Column(name = "ENABLED",nullable=false)
	private boolean enabled;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<AppRole> getRoles() {
		return roles;
	}

	public void setRoles(Collection<AppRole> roles) {
		this.roles = roles;
	}

	public Integer getReference() {
		return reference;
	}

	public void setReference(Integer reference) {
		this.reference = reference;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "AppUser [id=" + id + ", name=" + name + ", password=" + password + ", roles=" + roles + ", reference="
				+ reference + ", enabled=" + enabled + "]";
	}

}
