package com.resource.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
	
	@GetMapping("/")
	public ResponseEntity<Void> index() {
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
