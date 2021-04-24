package com.shakal.imageapi.security.authentication;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.shakal.imageapi.dto.auth.GenericLoginDTO;

public class GenericAuthenticationContext implements Authentication{
	
	private long id;
	private String username;
	private boolean authenticated;
	public GenericLoginDTO loginDetail;
	
	public GenericAuthenticationContext(boolean authenticated,long id, String username) {
		this.authenticated = authenticated;
		this.id = id;
		this.username = username;
	}
	public GenericAuthenticationContext(GenericLoginDTO loginDetail) {
		this.loginDetail = loginDetail;
	}

	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public GenericLoginDTO getLoginDetail() {
		return loginDetail;
	}
	public void setLoginDetail(GenericLoginDTO loginDetail) {
		this.loginDetail = loginDetail;
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
		return new Object();
	}

	@Override
	public Object getDetails() {
		return this.loginDetail;
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
		// TODO Auto-generated method stub
		
	}

}
