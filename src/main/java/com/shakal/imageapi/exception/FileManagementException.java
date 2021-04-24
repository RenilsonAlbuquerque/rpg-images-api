package com.shakal.imageapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
public class FileManagementException extends Exception{
	
	public FileManagementException(String message) {
        super(message);

    }

}
