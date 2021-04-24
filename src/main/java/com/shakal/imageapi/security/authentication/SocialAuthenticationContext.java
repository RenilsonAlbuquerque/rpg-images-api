package com.shakal.imageapi.security.authentication;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class SocialAuthenticationContext implements Authentication {
	
	private Boolean authenticated;
	private long id;
	private String socialId;
	private String username;
	
	public SocialAuthenticationContext(Boolean authenticated,long id,String socialId, String username) {
		this.authenticated = authenticated;
		this.id = id;
		this.socialId = socialId;
		this.username = username;
	}

	
	public Boolean getAuthenticated() {
		return authenticated;
	}


	public void setAuthenticated(Boolean authenticated) {
		this.authenticated = authenticated;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getSocialId() {
		return socialId;
	}


	public void setSocialId(String socialId) {
		this.socialId = socialId;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	@Override
	public String getName() {
		
		return this.username;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAuthenticated() {
		
		return this.authenticated;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		this.authenticated = isAuthenticated;
		
	}

}
