package org.mfaruga.security;

import org.springframework.security.core.GrantedAuthority;

public enum MFAuthorities implements GrantedAuthority{

	ADMIN("ADMIN"), USER("USER");

	MFAuthorities(String id) {
		this.id = id;
	}

	private String id;

	public String toString() {
		return "MFAuthorities: " + id;
	}

	public String getAuthority() {
		return id;
	}

	
	
}
