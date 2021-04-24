package com.shakal.imageapi.helpers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletInputStream;

import org.springframework.security.authentication.BadCredentialsException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.util.IOUtils;
import com.shakal.imageapi.dto.auth.GenericLoginDTO;
import com.shakal.imageapi.dto.auth.LoginInputUserDTO;
import com.shakal.imageapi.dto.auth.SocialInputUserDTO;
import com.shakal.imageapi.utils.Messages;

public abstract class AuthHelper {

	public static GenericLoginDTO mapLoginStrategy(ServletInputStream inputStream)throws BadCredentialsException {
		
		String resultStr = valuesrs(inputStream);
		GenericLoginDTO result = null;
		try {
			result = new ObjectMapper().readValue(resultStr, LoginInputUserDTO.class);
			//result = new ObjectMapper().readValue(inputStream.toString(), LoginInputUserDTO.class);
		} catch (IOException e) {}
		try {
			//Object obj = new ObjectMapper().readValue(inputStream, Object.class);
			result = new ObjectMapper().readValue(resultStr, SocialInputUserDTO.class);
			//result = new ObjectMapper().readValue(inputStream.toString(), SocialInputUserDTO.class);
		} catch (IOException e) {}
		
		
		
		if(result == null) {
			throw new BadCredentialsException(Messages.INVALID_JSON_FORMAT);
		}
		return result;
		
		/*
		GenericLoginDTO result = null;
		try {
			Object obj = new ObjectMapper().readValue(inputStream.toString(), Object.class);
			result = ((SocialInputUserDTO) obj);
			if(obj instanceof SocialInputUserDTO) {
				result = (SocialInputUserDTO) obj;
			}
			if(obj instanceof LoginInputUserDTO) {
				result = (LoginInputUserDTO) obj;
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		
		if(result == null) {
			throw new BadCredentialsException(Messages.INVALID_JSON_FORMAT);
		}
		
		return result;
		*/
	}
	private static String valuesrs(ServletInputStream inputStream) {
		
		java.util.Scanner s = new java.util.Scanner(inputStream).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}
}
