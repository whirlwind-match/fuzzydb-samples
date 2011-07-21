package org.fuzzydb.samples;

import org.fuzzydb.samples.security.WhirlwindUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class UserDetailsController {


	
	@Autowired
	@Qualifier("userDetailsRepository")
	private CrudRepository<WhirlwindUserDetails,String> userRepo;


	
	@RequestMapping(value="/signup", method=RequestMethod.GET) 
	public String signupForm() {
		
		return "signup";
	}

	@Transactional
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String doSignup(@RequestBody WhirlwindUserDetails form) {
		
		try {
			userRepo.save(form);
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
		
		return "redirect:/";
	}
}

