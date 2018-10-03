package com.resource.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClientException;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class HttpClientException extends RestClientException {

	private static final long serialVersionUID = 1L;

	public HttpClientException(String message) {
		super(message);
	}
	
}