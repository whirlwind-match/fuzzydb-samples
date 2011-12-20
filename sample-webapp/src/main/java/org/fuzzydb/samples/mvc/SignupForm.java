package org.fuzzydb.samples.mvc;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SignupForm {

	@Pattern(regexp="^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,4}$", message="not an accepted email address format. Expect: a@a.aa as minimum")
	private String email;
	
	@Size(min=6, message="password too short")
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
