package com.example.t2scd.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/home")
public class AppController {
	@GetMapping("")
	public ResponseEntity<String> getHome() {
		return ResponseEntity.ok("HEYYYYYYYYYYYYYYYYYY");
	}

	@GetMapping("/test")
	public ResponseEntity<String> testEndpoint() {
		return ResponseEntity.ok("Test endpoint works!");
	}
}
