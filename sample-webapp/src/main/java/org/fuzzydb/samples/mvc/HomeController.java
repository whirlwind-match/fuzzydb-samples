package org.fuzzydb.samples.mvc;

import java.security.Principal;

import javax.inject.Inject;
import javax.inject.Provider;

import org.fuzzydb.samples.MyCounter;
import org.fuzzydb.samples.repositories.StatsRepository;
import org.fuzzydb.samples.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final String HOME_PAGE = "/";

	private final Provider<ConnectionRepository> connectionRepositoryProvider;
	
	private final UserRepository accountRepository;

	@Inject
	public HomeController(Provider<ConnectionRepository> connectionRepositoryProvider, UserRepository accountRepository) {
		this.connectionRepositoryProvider = connectionRepositoryProvider;
		this.accountRepository = accountRepository;
	}

	@Autowired
	private StatsRepository counterRepo;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	private ConnectionRepository getConnectionRepository() {
		return connectionRepositoryProvider.get();
	}
	/**
	 * Simple scenario for updating one composite object
	 */
	@Transactional
	@RequestMapping(value=HOME_PAGE, method=RequestMethod.GET)
	public String home(Principal currentUser, Model model) {
		model.addAttribute("connectionsToProviders", getConnectionRepository().findAllConnections());
		if (currentUser != null) {
			model.addAttribute("account", accountRepository.findOne(currentUser.getName()));
		}

		logger.info("Welcome home!");
		
		MyCounter counter = counterRepo.findOne(HOME_PAGE);
		
		if (counter == null) {
			counter = new MyCounter(HOME_PAGE);
		}
		counter.count++;
		counterRepo.save(counter);
		
		model.addAttribute("count", counter.count);
		return "home";
	}
}

