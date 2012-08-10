package org.fuzzydb.samples.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;


public class WhirlwindUserDetails extends User {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	@Id
	private String email;

	private String firstName;

	private String lastName;

	public WhirlwindUserDetails(String username, String password,
			String firstName, String lastName,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, true, true, true, true, authorities);
		email = username;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public WhirlwindUserDetails(String username, String password,
			String firstName, String lastName) {
		this(username, password, null, null, Collections.singletonList(new SimpleGrantedAuthority("USER")) );
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	static public WhirlwindUserDetails createEnabledUser(String email,
			String password) {
		WhirlwindUserDetails userDetails = new WhirlwindUserDetails(email, password, null, null);
		return userDetails;
	}
}
