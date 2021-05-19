package com.shakal.imageapi.model.base;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class MainEntity extends BaseEntity{

	private boolean confidential;

	public boolean isConfidential() {
		return confidential;
	}

	public void setConfidential(boolean confidential) {
		this.confidential = confidential;
	}
	
	
}
