package com.ecommercesports.ecommercesports.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommercesports.ecommercesports.entities.User;


@Repository("userRepository")
public interface IUserRepository extends JpaRepository<User, Serializable> {

	@Query("SELECT u FROM User u JOIN FETCH u.userRoles WHERE u.username = (:username)")
	public abstract User findByUsernameAndFetchUserRolesEagerly(@Param("username") String username);
	
	public User findByUsername(String username);
	
	@Query("SELECT u FROM User u WHERE u.email = (:email)")	
	public abstract User findByEmail(@Param("email") String email);
	
	
	
	@Query("SELECT username FROM User u WHERE u.email = (:username)")
	public abstract String userNameByEmailInUsername(@Param("username") String username);

	
	public User findByIdUser(long idUser);
}
