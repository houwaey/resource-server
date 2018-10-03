package com.resource.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.resource.dto.ExceptionObject;
import com.resource.exception.ForbiddenException;
import com.resource.exception.InternalServerException;
import com.resource.exception.UnauthorizedException;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ Error.class })
    public final ResponseEntity<Object> handleAllError(Error err, WebRequest request) {
        return new ResponseEntity<Object>(
						new ExceptionObject(HttpStatus.INTERNAL_SERVER_ERROR
										, err.getMessage()
										, err.getCause() == null ? err.getCause().getMessage() : "N/A"
										, request.getDescription(true))
						, HttpStatus.INTERNAL_SERVER_ERROR
					);
    }
	
	@ExceptionHandler({ Exception.class })
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
						new ExceptionObject(HttpStatus.INTERNAL_SERVER_ERROR
										, ex.getMessage()
										, ex.getCause() != null ? ex.getCause().getMessage() : "N/A"
										, request.getDescription(true))
						, HttpStatus.INTERNAL_SERVER_ERROR
					);
    }
	
	@ExceptionHandler({ UnauthorizedException.class })
	public final ResponseEntity<Object> handleUnauthorizedException(Exception ex, WebRequest request) {
		return new ResponseEntity<Object>(
						new ExceptionObject(HttpStatus.UNAUTHORIZED
										, ex.getMessage()
										, ex.getCause() != null ? ex.getCause().getMessage() : "N/A"
										, request.getDescription(true))
						, HttpStatus.UNAUTHORIZED
					);
	}
	
	@ExceptionHandler({ ForbiddenException.class })
	public final ResponseEntity<Object> handleForbiddenException(Exception ex, WebRequest request) {
		return new ResponseEntity<Object>(
						new ExceptionObject(HttpStatus.FORBIDDEN
										, ex.getMessage()
										, ex.getCause() != null ? ex.getCause().getMessage() : "N/A"
										, request.getDescription(true))
						, HttpStatus.FORBIDDEN
					);
	}
	
	@ExceptionHandler({ InternalServerException.class })
	public final ResponseEntity<Object> handleInternalServerException(Exception ex, WebRequest request) {
		return new ResponseEntity<Object>(
						new ExceptionObject(HttpStatus.INTERNAL_SERVER_ERROR
										, ex.getMessage()
										, ex.getCause() != null ? ex.getCause().getMessage() : "N/A"
										, request.getDescription(true))
						, HttpStatus.INTERNAL_SERVER_ERROR
					);
	}
	
}
