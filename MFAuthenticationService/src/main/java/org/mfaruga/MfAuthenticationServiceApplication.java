package org.mfaruga;

import java.security.Principal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.mfaruga.dao.LoginUser;
import org.mfaruga.dao.LoginUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.ManagementServerProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableAuthorizationServer
@RestController
public class MfAuthenticationServiceApplication extends WebMvcConfigurerAdapter implements CommandLineRunner {

	@Autowired
	LoginUserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(MfAuthenticationServiceApplication.class, args);
	}

	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}

	@RequestMapping("/me")
	public Principal userMe(Principal user) {
		return user;
	}

	
//	@RequestMapping("/user")
//	public Map<String, String> user(Principal user) {
//		
//		System.out.println("Principal: " + user);
//		
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("name", "mfaruga");
//		map.put("email", "m.faruga@vp.pl");
//		
//		int rand = new Random().nextInt(2);
//		if (rand == 0) {
//			System.out.println("Returning user mfaruga");	
//		} else {
//			System.out.println("Throwing exception");
//			throw new UsernameNotFoundException("User not found");
//		}
//				
//		return map;
//		//return user;
//	}
	
	
	@Configuration
	@Order(ManagementServerProperties.ACCESS_OVERRIDE_ORDER)
	protected static class LoginConfig extends WebSecurityConfigurerAdapter {
		
		@Autowired
		private AuthenticationManager authenticationManager;
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.formLogin().permitAll().and().authorizeRequests().anyRequest().authenticated();
			// http.formLogin().loginPage("/login").permitAll().and().authorizeRequests().antMatchers("/user").permitAll().anyRequest().authenticated();
			//http.authorizeRequests().antMatchers("/**").permitAll().and().formLogin();
//			http.antMatcher("/**").authorizeRequests().anyRequest().permitAll();
		}
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.parentAuthenticationManager(authenticationManager);
		}
	}
	
	@Configuration
	@EnableResourceServer
	public class ResourceServer
	        extends ResourceServerConfigurerAdapter {
	    @Override
	    public void configure(HttpSecurity http) throws Exception {
	        http
	            .antMatcher("/me")
	            .authorizeRequests().anyRequest().authenticated();
	    }
	}
	
	@Override
	public void run(String... args) throws Exception {

		if (userRepository.findAll().size() > 0) {
			System.out.println("Some users are already defined, do not populate");
		} else {
			System.out.println("Creating default users");
			LoginUser[] defaultUsers = { new LoginUser("mfaruga", "mfaruga", "Michal", "Faruga"),
					new LoginUser("sfaruga", "sfaruga", "Sylwia", "Faruga"),
					new LoginUser("bfaruga", "bfaruga", "Bartosz", "Faruga"),
					new LoginUser("milena", "milena", "Milena", "Faruga") };
			userRepository.save(Arrays.asList(defaultUsers));
			
		}
	}
}
