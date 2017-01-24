package org.mfaruga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableGlobalMethodSecurity
public class MsBootFirstApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsBootFirstApplication.class, args);
	}
}

@RestController
class GreetingController {
	
	@Autowired
	Environment env;
	
	@RequestMapping("/")
	Greet greet() {
		return new Greet("Hello World on differnet PORT! " + env.getProperty("mfaruga.custom1"));
	}

}

class Greet {
	private String message;

	public Greet() {
	}

	public Greet(String message) {
		this.setMessage(message);
	}
	// add getter and setter

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
