package com.shakal.imageapi.security;



import com.shakal.imageapi.exception.LoginException;
import com.shakal.imageapi.model.enums.SocialTypeEnum;
import com.shakal.imageapi.model.user.NativeUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.shakal.imageapi.contracts.entity.ISocialUser;
import com.shakal.imageapi.contracts.service.user.INativeUserService;
import com.shakal.imageapi.contracts.service.user.ISocialUserService;
import com.shakal.imageapi.dto.auth.GenericLoginDTO;
import com.shakal.imageapi.dto.auth.LoginInputUserDTO;
import com.shakal.imageapi.dto.auth.SocialInputUserDTO;
import com.shakal.imageapi.security.authentication.GenericAuthenticationContext;



@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {


	@Autowired
	private INativeUserService userService;
	@Autowired
	private ISocialUserService socialUserService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
       
        try {
        	
        	GenericLoginDTO loginDTO = (GenericLoginDTO)authentication.getDetails();
            
            Authentication auth = null;
            if(loginDTO instanceof LoginInputUserDTO) {
            	NativeUser natUs = this.userService.handleLogin(loginDTO.getUsername(),
            			((LoginInputUserDTO)loginDTO).getPassword());
            	 auth = new GenericAuthenticationContext(
            			 true,
            			 natUs.getId(),
            			 natUs.getUsername()
                 );
            }
            if(loginDTO instanceof SocialInputUserDTO) {
            	
            	ISocialUser socUs = this.socialUserService.handleLogin(((SocialInputUserDTO)loginDTO),
            			SocialTypeEnum.fromInteger( ((SocialInputUserDTO)loginDTO).getProvider()) );
            	 auth = new GenericAuthenticationContext(
            			 true,
            			 socUs.id(),
                         socUs.username() 
                 );
            }
           return auth;

        }catch(LoginException e){
            throw new BadCredentialsException(e.getMessage());
        }
        
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(GenericAuthenticationContext.class);
    }
}
