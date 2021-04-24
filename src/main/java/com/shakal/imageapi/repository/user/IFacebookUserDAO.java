package com.shakal.imageapi.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shakal.imageapi.model.user.FacebookUser;

public interface IFacebookUserDAO extends JpaRepository<FacebookUser,Long>{

	@Query("SELECT fbus FROM FacebookUser fbus where fbus.facebookId = ?1")
	Optional<FacebookUser> findUserByFacebookId(String facebookId);
}
