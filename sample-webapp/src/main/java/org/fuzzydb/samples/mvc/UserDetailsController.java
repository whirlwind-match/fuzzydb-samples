package org.fuzzydb.samples.mvc;

import java.util.Collection;
import java.util.LinkedList;

import javax.validation.Valid;

import org.fuzzydb.samples.repositories.UserRepository;
import org.fuzzydb.samples.security.WhirlwindUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class UserDetailsController {

	
	@Autowired
	private UserRepository userRepo;


	
	@RequestMapping(value="/signup", method=RequestMethod.GET) 
	public String signupForm(Model model) {
		model.addAttribute("command", new SignupForm()); 	
		return "signup";
	}

	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String doSignup(@ModelAttribute("command") @Valid SignupForm form, Errors result) {
	
		Collection<SimpleGrantedAuthority> auths = new LinkedList<SimpleGrantedAuthority>();
		auths.add(new SimpleGrantedAuthority("USER"));
		WhirlwindUserDetails userDetails = new WhirlwindUserDetails(form.getEmail(), form.getPassword(), true, true, true, true, auths );
		 
		if (exists(form)) { 
			result.rejectValue("email", "accounts.emailAlreadyRegistered");
		}

		if (result.hasErrors()) {
			return "signup";
		}
	
		try {
			saveUser(userDetails);
			return "redirect:/";
		} catch (DuplicateKeyException e) {
			result.rejectValue("email", "accounts.emailAlreadyRegistered");
			return "signup";
		}
	}

	@Transactional(readOnly=true)
	private boolean exists(SignupForm form) {
		return userRepo.exists(form.getEmail());
	}

	@Transactional
	private void saveUser(WhirlwindUserDetails userDetails) {
		userRepo.save(userDetails);
	}
}

