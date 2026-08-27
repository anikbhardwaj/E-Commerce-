package com.ecomm.user.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecomm.user.response.ApiResponse;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(exception = AppException.class)
	public ResponseEntity<?> handleAppException(AppException exception){
		return new ResponseEntity<>(new ApiResponse<>(exception.getMessage(),
				null,exception.getHttpStatus()),exception.getHttpStatus());
	}
}
