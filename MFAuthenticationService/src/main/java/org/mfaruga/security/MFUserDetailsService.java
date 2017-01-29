package org.mfaruga.security;

public interface MFUserDetailsService {
	MFUserDetails loadUserByUserName(String userName);
}
