package com.woodapp.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	User findByEmail(String email);

//	org.springframework.security.core.userdetails.User save(org.springframework.security.core.userdetails.User newUser);

}
