package com.shakal.imageapi.service.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shakal.imageapi.contracts.entity.ISocialUser;
import com.shakal.imageapi.contracts.service.user.ISocialUserService;
import com.shakal.imageapi.dto.auth.SocialInputUserDTO;
import com.shakal.imageapi.mappers.UserMapper;
import com.shakal.imageapi.model.enums.SocialTypeEnum;
import com.shakal.imageapi.model.user.FacebookUser;
import com.shakal.imageapi.model.user.GoogleUser;
import com.shakal.imageapi.model.user.SocialUser;
import com.shakal.imageapi.repository.user.IFacebookUserDAO;
import com.shakal.imageapi.repository.user.IGoogleUserDAO;

@Service
public class SocialUserService implements ISocialUserService {

	private IFacebookUserDAO facebookUserDAO;
	private IGoogleUserDAO googleUserDAO;
	
	@Autowired
	public SocialUserService(IFacebookUserDAO facebookUserDAO,IGoogleUserDAO googleUserDAO) {
		this.facebookUserDAO = facebookUserDAO;
		this.googleUserDAO = googleUserDAO;
	}

	@Override
	public ISocialUser handleLogin(SocialInputUserDTO inputValue, SocialTypeEnum type) {
		ISocialUser result = null;
		
		if(type == SocialTypeEnum.FACEBOOK) {
			
			Optional<FacebookUser> search = this.facebookUserDAO.findUserByFacebookId(inputValue.getId());
			
			if(!search.isPresent()) {
				FacebookUser saved = this.facebookUserDAO.save(UserMapper.facebookDTOToEntity(inputValue));
				result = new SocialUser(
						saved.getId(),
						saved.getFacebookId(),
						saved.getUsername());
			}else {
				result = new SocialUser(
						search.get().getId(),
						search.get().getFacebookId(),
						search.get().getUsername());;
			}
			
		}
		if(type == SocialTypeEnum.GOOGLE) {
			Optional<GoogleUser> search = this.googleUserDAO.findUserByGoogleId(inputValue.getId());
			
			if(!search.isPresent()) {
				GoogleUser saved = this.googleUserDAO.save(UserMapper.googleDTOToEntity(inputValue));
				result = new SocialUser(
						saved.getId(),
						saved.getGoogleId(),
						saved.getUsername());
			}else {
				result = new SocialUser(
						search.get().getId(),
						search.get().getGoogleId(),
						search.get().getUsername());;
			}
			
		}
		
		return result;
	}

}
