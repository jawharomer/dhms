package com.joh.dhms.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.joh.dhms.domain.model.AppUserDetail;

@Component
public class AuthenticationFacadeServiceImpl implements AuthenticationFacadeService {

	@Override
	public AppUserDetail getAppUserDetail() {
		return (AppUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}