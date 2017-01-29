package org.mfaruga.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.mfaruga.dao.LoginUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MFUserDetails implements UserDetails {

	private static final long serialVersionUID = 1993367209592960995L;
	private LoginUser loginUser;	
	private final List<GrantedAuthority> listOfAuthorities = new ArrayList<GrantedAuthority>(); 
	
	public MFUserDetails(LoginUser user, Collection<GrantedAuthority> authorities) {
		this.loginUser = user;
		listOfAuthorities.addAll(authorities);
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {		
		return Collections.unmodifiableList(listOfAuthorities);
	}

	public String getUsername() {
		return loginUser.getUsername();
	}

	public boolean verifyPlainTextPassword(String plainTextPassword) {
		return LoginUser.passwordMatches(plainTextPassword, loginUser.getPassword());
	}
	
	public boolean verifyHashedPassword(String hashedPassword) {
		return loginUser.getPassword().equals(hashedPassword);
	}
	
	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {		
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {		
		return true;
	}

	public String getPassword() {		
		return loginUser.getPassword();
	}

}
