package org.fuzzydb.samples;

import java.util.Collection;
import java.util.LinkedList;

import org.fuzzydb.samples.security.WhirlwindUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class UserDetailsController {

	
	@Autowired
	@Qualifier("userDetailsRepository")
	private CrudRepository<WhirlwindUserDetails,String> userRepo;


	
	@RequestMapping(value="/signup", method=RequestMethod.GET) 
	public String signupForm(Model model) {
		model.addAttribute("command", new SignupForm()); 	
		return "signup";
	}

	@Transactional
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String doSignup(@ModelAttribute("command") SignupForm form) {
		
		Collection<GrantedAuthorityImpl> auths = new LinkedList<GrantedAuthorityImpl>();
		auths.add(new GrantedAuthorityImpl("USER"));
		WhirlwindUserDetails userDetails = new WhirlwindUserDetails(form.getEmail(), form.getPassword(), true, true, true, true, auths );
		
		try {
			userRepo.save(userDetails);
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
		
		return "redirect:/";
	}
}

