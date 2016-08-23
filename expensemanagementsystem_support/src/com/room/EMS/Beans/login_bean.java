package com.room.EMS.Beans;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class login_bean {

	@Size(min = 3, max = 20, message = "Username must be between 3 to 20 characters long")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username should be with alphanumeric characters only")
	String username;
	@Size(min = 3, max = 15, message = "Password must be between 3 to 15 characters long")
	String password;
	String role;
	String status_cd;
	@Size(min = 3, max = 100, message = "email must be between 3 to 50 characters long")
	String email;
	String bankAccount;
	
	
	

	public String getBankAccount() {
	    return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
	    this.bankAccount = bankAccount;
	}

	public String getEmail() {
	    return email;
	}

	public void setEmail(String email) {
	    this.email = email;
	}

	public String getStatus_cd() {
		return status_cd;
	}

	public void setStatus_cd(String status_cd) {
		this.status_cd = status_cd;
	}
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
