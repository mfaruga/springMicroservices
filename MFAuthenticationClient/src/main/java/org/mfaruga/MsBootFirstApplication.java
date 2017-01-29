//package org.mfaruga;
//
//import java.security.Principal;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.actuate.autoconfigure.ManagementServerProperties;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//@EnableAutoConfiguration
//@Configuration
//@RestController
//@EnableOAuth2Sso
//public class MsBootFirstApplication extends WebMvcConfigurerAdapter{
//
//	public static void main(String[] args) {
//		SpringApplication.run(MsBootFirstApplication.class, args);
//	}
//	
//}
//
//@Configuration
//class ABBA extends WebSecurityConfigurerAdapter {
//	
//	@Autowired
//	private AuthenticationManager authenticationManager;
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().anyRequest().permitAll();
//		//http.authorizeRequests().anyRequest().authenticated();
//		// http.formLogin().loginPage("/login").permitAll().and().authorizeRequests().antMatchers("/user").permitAll().anyRequest().authenticated();
//		//http.authorizeRequests().antMatchers("/**").permitAll().and().formLogin();
////		http.antMatcher("/**").authorizeRequests().anyRequest().permitAll();
//	}
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.parentAuthenticationManager(authenticationManager);
//	}
//}
//
//
//@RestController
//class GreetingController {
//	@RequestMapping(path = "/hello", method = RequestMethod.GET, produces = "application/json")
//	Greet greet() {
//		System.out.println("Handling greet");
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		String currentPrincipalName = authentication.getName();
//		return new Greet("Hello World! " + currentPrincipalName);
//	}
//	
//	@RequestMapping("/second")
//	Greet second(Principal principal) {				
//		System.out.println("Handling second");
//		return new Greet("Hello Second! " + principal);
//	}
//	
//	@RequestMapping(path = "/", method = RequestMethod.GET, produces = "application/json")
//	Greet base(Principal principal) {				
//		return new Greet("Hello base! " + principal);
//	}
//}
//
//class Greet {
//	private String message;
//
//	public Greet() {
//	}
//
//	public Greet(String message) {
//		this.setMessage(message);
//	}
//	// add getter and setter
//
//	public String getMessage() {
//		return message;
//	}
//
//	public void setMessage(String message) {
//		this.message = message;
//	}
//}





package org.mfaruga;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableAutoConfiguration
@Configuration
@EnableOAuth2Sso
public class MsBootFirstApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsBootFirstApplication.class, args);
	}
}

@RestController
class GreetingController {
	@RequestMapping(path = "/hello", method = RequestMethod.GET, produces = "application/json")
	Greet greet(Principal principal) {
		System.out.println("Principal: " + principal);
		return new Greet("Hello World! " + principal);
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
