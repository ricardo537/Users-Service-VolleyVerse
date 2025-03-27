package com.volleyverse.users_service.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.volleyverse.users_service.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{
	
	User save(User user);
	
	Optional<User> findByEmail(String email);
	
	//This method is exclusive for the test
	void deleteByEmail(String email);
	
}
