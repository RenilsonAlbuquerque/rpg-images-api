package com.shakal.imageapi.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shakal.imageapi.model.user.GoogleUser;

public interface IGoogleUserDAO extends JpaRepository<GoogleUser,Long>{

	@Query("SELECT gus FROM GoogleUser gus where gus.googleId = ?1")
	Optional<GoogleUser> findUserByGoogleId(String googleId);
}
