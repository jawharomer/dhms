package com.joh.dhms.service;

import com.joh.dhms.model.AppUser;

public interface AppUserService {

	AppUser save(AppUser appUser);

	AppUser findByName(String name);

}
