package org.fuzzydb.samples;

import org.fuzzydb.samples.repositories.StatsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private StatsRepository counterRepo;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simple scenario for updating one composite object
	 */
	@Transactional
	@RequestMapping(value=HOME_PAGE, method=RequestMethod.GET)
	public String home(Model model) {
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

