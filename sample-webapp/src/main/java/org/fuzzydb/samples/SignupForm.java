package org.fuzzydb.samples;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SignupForm {

//	@Pattern(regexp="^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$", "not an accepted email address format")
	private String email;
//	@Size(min=6, message="password too short")
	private String password;
	
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
	
	
}
