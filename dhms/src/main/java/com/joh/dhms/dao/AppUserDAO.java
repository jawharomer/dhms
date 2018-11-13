package com.joh.dhms.dao;

import org.springframework.data.repository.CrudRepository;

import com.joh.dhms.model.AppUser;

public interface AppUserDAO extends CrudRepository<AppUser, Integer> {
	AppUser findByName(String userName);
}
