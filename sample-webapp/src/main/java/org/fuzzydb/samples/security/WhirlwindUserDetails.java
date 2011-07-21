package org.fuzzydb.samples.security;

import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;


public class WhirlwindUserDetails extends User {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	@Id
	private String email;
	
	public WhirlwindUserDetails(String username, String password,
			boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		email = username;
	}
}
