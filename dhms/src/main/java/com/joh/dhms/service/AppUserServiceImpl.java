package com.joh.dhms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.joh.dhms.dao.AppUserDAO;
import com.joh.dhms.exception.CusDataIntegrityViolationException;
import com.joh.dhms.model.AppUser;

@Service
public class AppUserServiceImpl implements AppUserService {

	@Autowired
	private AppUserDAO appUserDAO;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public AppUser save(AppUser appUser) {
		try {
			appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
			return appUserDAO.save(appUser);
		} catch (DataIntegrityViolationException e) {
			throw new CusDataIntegrityViolationException("The User name is aleady exist");
		}
	}

	@Override
	public AppUser findByName(String name) {
		return appUserDAO.findByName(name);
	}

}
