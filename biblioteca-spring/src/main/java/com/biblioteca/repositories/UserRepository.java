package com.biblioteca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findById(long id);

	User save(User u);

	void delete(User u);

	List<User> findAll();
	
	User  findByEmail(String email);
}
