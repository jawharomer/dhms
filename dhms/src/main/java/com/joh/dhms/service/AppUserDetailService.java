package com.joh.dhms.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.joh.dhms.domain.model.AppUserDetail;
import com.joh.dhms.model.AppUser;

@Service
public class AppUserDetailService implements UserDetailsService {

	private static final Logger logger = Logger.getLogger(AppUserDetailService.class);

	@Autowired
	private AppUserService appUserService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Entered User name=" + username);
		AppUser appUser = null;
		
		try {
			appUser = appUserService.findByName(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("appUser=" + appUser);

		if (appUser == null)
			throw new UsernameNotFoundException("User does not exisit");
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		appUser.getRoles().stream().forEach(e -> {
			authorities.add(new SimpleGrantedAuthority(e.getName()));
		});

		AppUserDetail appUserDetail = new AppUserDetail();

		appUserDetail.setAuthorities(authorities);
		appUserDetail.setUsername(appUser.getName());
		appUserDetail.setPassword(appUser.getPassword());
		appUserDetail.setEnabled(appUser.isEnabled());

		if (appUser.getReference() != null)
			appUserDetail.setReference(appUser.getReference());

		logger.info("appUserDetail=" + appUserDetail);
		return appUserDetail;

	}

}