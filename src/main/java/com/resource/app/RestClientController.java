package com.resource.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestClientController {

	@GetMapping("/test")
	public ResponseEntity<Void> test() {
		System.out.println("fuck this shit");
	
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}