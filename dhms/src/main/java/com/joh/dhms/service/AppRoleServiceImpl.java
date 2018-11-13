package com.joh.dhms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.joh.dhms.dao.AppRoleDAO;
import com.joh.dhms.dao.AppUserDAO;
import com.joh.dhms.exception.CusDataIntegrityViolationException;
import com.joh.dhms.model.AppRole;
import com.joh.dhms.model.AppUser;

@Service
public class AppRoleServiceImpl implements AppRoleService {

	@Autowired
	private AppRoleDAO appRoleDAO;

	@Override
	public AppRole save(AppRole appRole) {
		try {
			return appRoleDAO.save(appRole);
		} catch (DataIntegrityViolationException e) {
			throw new CusDataIntegrityViolationException("The Role name is aleady exist");
		}
	}

}
