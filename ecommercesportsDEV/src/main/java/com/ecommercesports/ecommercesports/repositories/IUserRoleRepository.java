package com.ecommercesports.ecommercesports.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommercesports.ecommercesports.entities.UserRole;

@Repository("userRoleRepository")
public interface IUserRoleRepository extends JpaRepository<UserRole, Serializable>{
	public abstract UserRole findById(int idRole);
}