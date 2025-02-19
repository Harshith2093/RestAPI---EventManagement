package com.jsp.eventmanagement.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NO_CONTENT)
public class UnableToSaveEventException extends RuntimeException{

	public UnableToSaveEventException(String message) {
		super(message) ;
	}
}
