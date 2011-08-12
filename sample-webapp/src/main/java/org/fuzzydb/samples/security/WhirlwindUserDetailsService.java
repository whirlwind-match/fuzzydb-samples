package org.fuzzydb.samples.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

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
	
	static private Logger log = LoggerFactory.getLogger(WhirlwindUserDetailsService.class);

	private WhirlwindCrudRepository<WhirlwindUserDetails, String> repository;
	

	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		WhirlwindUserDetails userDetails = repository.findOne(username);
		if (userDetails == null) {
			throw new UsernameNotFoundException("'" + username + "' is not a registered user id");
		}
		if (userDetails.getAuthorities() == null || userDetails.getAuthorities().size() == 0) {
			throw new UsernameNotFoundException("User '" + username + "' has no granted authorities");
		}
		log.debug("Found user: {}", username);
		return userDetails;
	}

	public void setRepository(WhirlwindCrudRepository<WhirlwindUserDetails, String> repository) {
		this.repository = repository;
	}
}
