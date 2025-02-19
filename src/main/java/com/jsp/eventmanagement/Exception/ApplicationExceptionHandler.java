package com.jsp.eventmanagement.Exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jsp.eventmanagement.Response.ResponseStructure;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler(value = UnableToSaveEventException.class)
	public ResponseEntity<ResponseStructure<String>> handleUnableToSaveEventException(
			UnableToSaveEventException exception) {

		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.NO_CONTENT.value());
		response.setMessage("Event Failed to save");
		response.setData(exception.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.NO_CONTENT);
	}

	@ExceptionHandler(value = EventNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleEventNotFoundException(EventNotFoundException exception) {

		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setMessage("Event failed to find");
		response.setData(exception.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> myMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		Map<String, String> response = new HashMap<>();
		e.getBindingResult().getAllErrors().forEach(err -> {
			String fieldName = ((FieldError) err).getField();
			String message = err.getDefaultMessage();
			response.put(fieldName, message);
		});
		return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
	}	

}
