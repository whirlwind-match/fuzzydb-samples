package org.fuzzydb.samples;

import java.util.Collection;
import java.util.LinkedList;

import javax.validation.Valid;

import org.fuzzydb.samples.security.WhirlwindUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wwm.db.exceptions.KeyCollisionException;


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

	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String doSignup(@ModelAttribute("command") @Valid SignupForm form, Errors result) {
	
		Collection<GrantedAuthorityImpl> auths = new LinkedList<GrantedAuthorityImpl>();
		auths.add(new GrantedAuthorityImpl("USER"));
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

