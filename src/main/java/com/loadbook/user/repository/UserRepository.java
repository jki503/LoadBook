package com.loadbook.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.loadbook.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select u from User u where u.userInformation.email = :email")
	Optional<User> findByEmail(String email);
}
