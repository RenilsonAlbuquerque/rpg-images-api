package com.shakal.imageapi.contracts.service.user;

import com.shakal.imageapi.exception.LoginException;
import com.shakal.imageapi.model.user.NativeUser;

public interface INativeUserService {

	NativeUser handleLogin(String username);
	NativeUser handleLogin(String username,String password) throws LoginException;
}
