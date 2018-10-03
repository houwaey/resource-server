package com.resource.validator;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.resource.dto.Token;
import com.resource.exception.ForbiddenException;
import com.resource.exception.InternalServerException;
import com.resource.exception.UnauthorizedException;
import com.resource.util.Constant;

@Component
public class TokenValidator {
	
	private RestTemplate restTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	public TokenValidator(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
	}

	public boolean isTokenValid(HttpServletRequest request) {
		String token = request.getHeader("Access-Token");
		if (token == null) {
			throw new UnauthorizedException("Empty token");
		}
		
		try {
			ResponseEntity<String> response = restTemplate.getForEntity(Constant.AUTH_SERVER + Constant.GRANT_CLIENTCREDENTIALS, String.class, token);
			if (response.getStatusCode() == HttpStatus.OK) {
				Token t = objectMapper.readValue(response.getBody(), Token.class);
				if (t.getExpires_in() <= 0) {
					throw new ForbiddenException("Token is expired");
				}
				return true;
			} else {
				throw new ForbiddenException("Invalid token");
			}
		} catch (RestClientException e) {
			throw new ForbiddenException("Forbidden", e);
		} catch (JsonParseException e) {
			throw new InternalServerException("JSON Parsing exception", e);
		} catch (JsonMappingException e) {
			throw new InternalServerException("JSON Mapping exception", e);
		} catch (IOException e) {
			throw new InternalServerException("I/O exception", e);
		}
	}
	
}
