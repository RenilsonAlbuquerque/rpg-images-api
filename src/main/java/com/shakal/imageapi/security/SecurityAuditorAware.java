package com.shakal.imageapi.security;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import com.shakal.imageapi.security.authentication.AuthenticationContext;

public class SecurityAuditorAware implements AuditorAware<Long> {

	@Override
	public Optional<Long> getCurrentAuditor() {
		AuthenticationContext authentication =(AuthenticationContext) SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()) {
		      return null;
		}
		return Optional.of(authentication.getId());
	}

}
