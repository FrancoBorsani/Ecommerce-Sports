package com.ecommercesports.ecommercesports.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Primary;

@Entity
@Table(name="perfil")
public class Perfil {
	
	@Id
	private int id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "apellido")
	private String firstName;
	
	@Column(name = "nombre")
	private String lastName;
	
	@Column(name = "email")
	private String email;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Perfil(int id, String username, String firstName, String lastName, String email) {
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}


	public Perfil() {
	}
	
	
	
}
