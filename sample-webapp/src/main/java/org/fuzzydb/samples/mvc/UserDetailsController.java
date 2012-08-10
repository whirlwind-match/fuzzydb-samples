package org.fuzzydb.samples.mvc;


import javax.validation.Valid;

import org.fuzzydb.samples.mvc.message.Message;
import org.fuzzydb.samples.mvc.message.MessageType;
import org.fuzzydb.samples.repositories.UserRepository;
import org.fuzzydb.samples.security.SignInUtils;
import org.fuzzydb.samples.security.WhirlwindUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;


@Controller
public class UserDetailsController {

	
	@Autowired
	private UserRepository userRepo;


	
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public SignupForm signupForm(WebRequest request) {
		Connection<?> connection = ProviderSignInUtils.getConnection(request);
		if (connection != null) {
			request.setAttribute("message", new Message(MessageType.INFO, "Your " + StringUtils.capitalize(connection.getKey().getProviderId()) + " identity is not associated with an account. If you're new, please sign up."), WebRequest.SCOPE_REQUEST);
			return SignupForm.fromProviderUser(connection.fetchUserProfile());
		} else {
			return new SignupForm();
		}
	}


	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String doSignup(@Valid SignupForm form, Errors result, WebRequest request) {
	
		if (result.hasErrors()) {
			return "signup";
		}

		WhirlwindUserDetails userDetails = WhirlwindUserDetails.createEnabledUser(form.getEmail(), form.getPassword());
		 
		if (exists(form)) { 
			result.rejectValue("email", "accounts.emailAlreadyRegistered");
		}

		if (result.hasErrors()) {
			return "signup";
		}
	
		try {
			SignInUtils.signin(userDetails.getUsername());
			ProviderSignInUtils.handlePostSignUp(userDetails.getUsername(), request);
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

