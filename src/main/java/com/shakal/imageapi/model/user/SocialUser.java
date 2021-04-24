package com.shakal.imageapi.model.user;

import com.shakal.imageapi.contracts.entity.ISocialUser;

public class SocialUser implements ISocialUser{
	
	private long id;
	private String socialId;
	private String username;

	public SocialUser() {
		
	}
	public SocialUser(long id,String socialId, String username) {
		this.id = id;
		this.socialId = socialId;
		this.username = username;
	}

	

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	@Override
	public String username() {
		return this.username;
	}
	@Override
	public long id() {
		return this.id;
	}
	@Override
	public String socialId() {
		return this.socialId;
	}

}
