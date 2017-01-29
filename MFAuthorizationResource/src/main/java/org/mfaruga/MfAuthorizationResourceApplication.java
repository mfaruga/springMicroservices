package org.mfaruga;

import java.security.Principal;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableResourceServer
public class MfAuthorizationResourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MfAuthorizationResourceApplication.class, args);
	}
	
	@RequestMapping("/")
	public String securedCall() {
		return "success (id: " + UUID.randomUUID().toString().toUpperCase() + ")";
	}
	
	@RequestMapping("/second")
	public String securedCall(Principal princ) {
		return "success (id: " + UUID.randomUUID().toString().toUpperCase() + ") User: " + princ ;
	}
	
	
}
