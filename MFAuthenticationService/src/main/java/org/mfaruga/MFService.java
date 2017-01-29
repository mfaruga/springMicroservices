package org.mfaruga;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class MFService extends WebSecurityConfigurerAdapter {

		@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/**").authorizeRequests().antMatchers("/", "/user**", "/webjars/**").permitAll().anyRequest()
				.authenticated();
	}
}
