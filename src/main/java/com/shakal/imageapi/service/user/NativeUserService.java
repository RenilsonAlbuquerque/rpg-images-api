package com.shakal.imageapi.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.shakal.imageapi.contracts.service.user.INativeUserService;
import com.shakal.imageapi.exception.LoginException;
import com.shakal.imageapi.model.user.NativeUser;
import com.shakal.imageapi.repository.user.INativeUserDAO;
import com.shakal.imageapi.security.PasswordEncoder;
import com.shakal.imageapi.utils.Messages;

@Service
public class NativeUserService implements INativeUserService{
	
	
	private INativeUserDAO nativeUserRepository;
    private PasswordEncoder encoder;
	
	@Autowired
	public NativeUserService(INativeUserDAO nativeUserRepository,PasswordEncoder encoder) {
		this.nativeUserRepository = nativeUserRepository;
		this.encoder = encoder;
	}
	
	@Override
	public NativeUser handleLogin(String username) {
		return this.nativeUserRepository.findByUsername(username)
				.orElseThrow(() -> new BadCredentialsException(Messages.USER_NOT_FOUND));
	}

	@Override
	public NativeUser handleLogin(String username, String password) throws LoginException {
		 NativeUser user = this.nativeUserRepository.findByUsername(username)
				 .orElseThrow(() -> new LoginException(Messages.USER_NOT_FOUND));
         if (encoder.bCryptPasswordEncoder().matches(password,user.getPassword())) {
         	
         	return user;
         }else{
             throw new LoginException(Messages.INVALID_PASSWORD);
         }
	}

	
	
	
	
	

}
