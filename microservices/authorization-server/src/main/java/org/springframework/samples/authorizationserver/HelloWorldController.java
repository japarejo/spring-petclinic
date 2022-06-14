package org.springframework.samples.authorizationserver;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@RequestMapping("/home")
	public String index() {
		return "Greetings from Spring Boot!";
	}

}
