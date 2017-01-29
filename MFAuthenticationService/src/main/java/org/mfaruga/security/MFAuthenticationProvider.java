package org.mfaruga.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MFAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	MFUserDetailsService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		MFUserDetails user = null;
		Authentication auth = null;
		
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		user = (MFUserDetails) userService.loadUserByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("wrong user/password");
		}

		if (password == null || !user.verifyPlainTextPassword(password)) {
			throw new UsernameNotFoundException("wrong user/password");
		}
		auth = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
		
		return auth;	
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
