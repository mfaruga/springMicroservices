package org.mfaruga.security;

import java.util.Arrays;

import org.mfaruga.dao.LoginUser;
import org.mfaruga.dao.LoginUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class MFUserDetailsServiceImpl implements MFUserDetailsService {

	@Autowired
	LoginUserRepository userRepository;
	
	public MFUserDetails loadUserByUserName(String userName) {
		
		LoginUser loginUser = userRepository.findByUsername(userName);
		if (loginUser == null) {
			return null;
		}		
		return new MFUserDetails(loginUser, Arrays.asList(new GrantedAuthority[] { MFAuthorities.ADMIN, MFAuthorities.USER }));		
	}

}
