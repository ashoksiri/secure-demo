package com.demo.myapp;

import java.util.Collections;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	@GetMapping("/")
	String getMessage() {
		return "Hello";
	}
	
	@GetMapping("/version")
	Map<String, Object> getVersion() {
		return Collections.singletonMap("version", "2.0.0");
	}
}
