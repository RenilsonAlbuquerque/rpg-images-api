package com.shakal.imageapi.model.user;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name= "tb_user_fb")
@PrimaryKeyJoinColumn(name = "user_id")
public class FacebookUser extends User  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7788752108898200712L;

	private String facebookId;
	
	private String photoUrl;
	
	private String email;
	
	private String lastName;

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	
	

}
