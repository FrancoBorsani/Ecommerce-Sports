package com.ecommercesports.ecommercesports.entities;

import java.net.URL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Primary;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="perfil")
public class Perfil {
	
	@Id
	private int id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "nombre")
	private String firstName;
	
	@Column(name = "user_role")
	private String userRol;
	
	@Column(name = "apellido")
	private String lastName;
	
	@Column(name = "phone")
	private String cellPhone;
	
	@Column(name = "email")
	private String email;

	@Column(name="imagen")
	private String imagen;
	
	@Column(name = "AboutMe" , length = 200)
	private String aboutMe;
	
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

	
	
	public String getCellPhone() {
		return cellPhone;
	}


	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}


	public String getImagen() {
		return imagen;
	}


	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	public String getAboutMe() {
		return aboutMe;
	}


	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	
	
	public String getUserRol() {
		return userRol;
	}


	public void setUserRol(String userRol) {
		this.userRol = userRol;
	}


	public Perfil(int id, String username, String userRol ,String firstName, String lastName, String cellPhone ,String email) {
		this.id = id;
		this.username = username;
		this.userRol = userRol;
		this.firstName = firstName;
		this.lastName = lastName;
		this.cellPhone = cellPhone;
		this.email = email;
		this.aboutMe="En esta parte podras escribir informacion que te parezca relevante para destacar en la pagina";
	}


	public Perfil() {
	}
	
	
	
}
