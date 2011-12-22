package org.fuzzydb.samples.mvc;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.social.connect.UserProfile;

public class SignupForm {

	@Pattern(regexp="^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,4}$", message="not an accepted email address format. Expect: a@a.aa as minimum")
	private String email;
	
	@Size(min=6, message="password too short")
	private String password;

	private String firstName;

	private String lastName;

	
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
	
	public static SignupForm fromProviderUser(UserProfile providerUser) {
		SignupForm form = new SignupForm();
		form.setEmail(providerUser.getEmail());
		form.setLastName(providerUser.getLastName());
		form.setFirstName(providerUser.getFirstName());
		return form;
	}
}
