package com.masai.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SupportRepresentative {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 40,nullable = false, unique = true)
	private String email;
	
	@Column(length = 20, nullable = false,unique = true)
	private String username;
	
	@Column(length = 20, nullable = false)
	private String password;

	public SupportRepresentative() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SupportRepresentative(String email, String username, String password) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;

	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "SupportRepresentative [id=" + id + ", email=" + email + ", username=" + username + ", password="
				+ password + "]";
	}

	
	


	
	
	
	
}
