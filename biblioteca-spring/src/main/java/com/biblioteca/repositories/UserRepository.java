package com.biblioteca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.biblioteca.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findById(long id);

	User save(User u);

	void delete(User u);

	List<User> findAll();
	
	@Query("SELECT u FROM User u WHERE u.accountLocked=false")
	List<User> findAllEnabled();
	
	User  findByEmail(String email);
	
	@Query("SELECT u FROM User u WHERE u.role='ADMIN' AND u.accountLocked=false")
	List<User> findEnabledAdmins();
	
	@Query("SELECT u FROM User u WHERE u.role='ADMIN' AND u.accountLocked=true")
	List<User> findDisabledAdmins();
}
