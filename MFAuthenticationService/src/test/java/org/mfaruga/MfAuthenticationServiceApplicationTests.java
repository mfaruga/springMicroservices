package org.mfaruga;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class MfAuthenticationServiceApplicationTests {

	@Test
	public void contextLoads() {
	
		String password = "faro";
		String salt = BCrypt.gensalt();
		String hashpw = BCrypt.hashpw(password, salt);
	
		System.out.println("Pass: " + password);
		System.out.println("Salt: " + salt);
		System.out.println("Hashed: " + hashpw);
		
		boolean matches = BCrypt.checkpw(password, hashpw);
		System.out.println("Matches!");
		
		assertTrue(matches);
	}

}
