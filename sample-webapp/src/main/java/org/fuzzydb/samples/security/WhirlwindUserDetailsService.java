package org.fuzzydb.samples.security;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.wwm.db.spring.repository.WhirlwindCrudRepository;

/**
 * Simple UserDetailsService implementation
 * 
 * In fact, this is a generic {@link CrudRepository} implementation
 * 
 * @author Neale Upstone
 *
 */
public class WhirlwindUserDetailsService implements UserDetailsService {

	private WhirlwindCrudRepository<WhirlwindUserDetails, String> repository;
	

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		return repository.findOne(username);
	}

	public void setRepository(WhirlwindCrudRepository<WhirlwindUserDetails, String> repository) {
		this.repository = repository;
	}
}
