package com.shakal.imageapi.model.base;



import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;



@MappedSuperclass
@Where(clause="deleted=0")
public class BaseEntity {

	private boolean deleted;
	
	
	@CreatedBy
	private Long userCreatorId;

	
	public boolean isDeleted() {
		return this.deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Long getUserCreatorId() {
		return userCreatorId;
	}

	public void setUserCreatorId(Long userCreatorId) {
		this.userCreatorId = userCreatorId;
	}
	
	

}
