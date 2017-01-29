package org.mfaruga.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginUserRepository extends JpaRepository<LoginUser, Long>{
	public LoginUser findByUsername(String username);
}
