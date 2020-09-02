package com.nikhil.springboot2securityjwt.pojo.requests;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.util.StringUtils;




public class SignupRequest {

	@NotBlank
	@Size(min = 3, max = 20)
	private String username;
	
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;
	
	@NotBlank
	@Size(min = 6,max = 40)
	private String password;
	
	
	@NotBlank
	private String firstname;
	
	@NotBlank
	private String lastname;
	
	
	@NotBlank
	private String phone;
	

	private boolean active;
	
	
	
	
	
	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	private Set<String> role;
	
	

	


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	
	public Set<String> getRole() {
		return role;
	}


	
	public void setRole(Set<String> role) {
		
		this.role = role;
	}


	@Override
	public String toString() {
		return "SignupRequest [role=" + role + "]";
	}
}
	
	
  
