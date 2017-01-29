package org.mfaruga.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.crypto.bcrypt.BCrypt;

@Entity
public class LoginUser {

	public static boolean passwordMatches(String plainTextPassword, String encodedPassword) {
		 return BCrypt.checkpw(plainTextPassword, encodedPassword);
	}
	
	public LoginUser() {
		super();
	}

	public static String hashPassword(String plainTextPassword) {
		String salt = BCrypt.gensalt();
		String hashpw = BCrypt.hashpw(plainTextPassword, salt);
		return hashpw;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(unique=true)
	private String username;

	/** Password that was hashed with use of salt (no need to store salt separately) */
	private String passwordEncoded;
	
	private String firstName;
	
	private String lastName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public String getPassword() {
		return passwordEncoded;
	}

	public void setPasswordEncoded(String passwordEncoded) {
		this.passwordEncoded = passwordEncoded;
	}

	public void setPasswordPlainText(String plainTextPassword) {		
		// TODO might be better to move hash implementation outside of the class - but from the other side, keeping this together will make sure that it's always the same 
		setPasswordEncoded(LoginUser.hashPassword(plainTextPassword));
	}
	
	public String getFirstName() {
		return firstName;
	}

	public LoginUser( String username, String plainTextPassword, String firstName, String lastName) {
		super();
		this.username = username;		 
		this.firstName = firstName;
		this.lastName = lastName;
		setPasswordPlainText(plainTextPassword);
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
}
