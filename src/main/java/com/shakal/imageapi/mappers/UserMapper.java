package com.shakal.imageapi.mappers;

import com.shakal.imageapi.dto.auth.SocialInputUserDTO;
import com.shakal.imageapi.model.user.FacebookUser;
import com.shakal.imageapi.model.user.GoogleUser;


public class UserMapper {

    
    public static FacebookUser facebookDTOToEntity(SocialInputUserDTO dto) {
    	FacebookUser entity = new FacebookUser();
    	entity.setUsername(dto.getUsername());
    	entity.setLastName(dto.getLastName());
    	entity.setFacebookId(dto.getId());
    	entity.setEmail(dto.getEmail());
    	entity.setPhotoUrl(dto.getImage());
    	return entity;
    	
    }
    public static GoogleUser googleDTOToEntity(SocialInputUserDTO dto) {
    	GoogleUser entity = new GoogleUser();
    	entity.setUsername(dto.getUsername());
    	entity.setLastName(dto.getLastName());
    	entity.setGoogleId(dto.getId());
    	entity.setEmail(dto.getEmail());
    	entity.setPhotoUrl(dto.getImage());
    	return entity;
    }
    
}
