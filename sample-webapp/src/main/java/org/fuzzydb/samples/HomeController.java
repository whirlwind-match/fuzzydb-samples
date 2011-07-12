package org.fuzzydb.samples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.wwm.db.spring.repository.FuzzyRepository;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	@Qualifier("counterRepository")
	private FuzzyRepository<MyCounter> counterRepo;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simple scenario for updating one composite object
	 */
	@Transactional
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home(Model model) {
		logger.info("Welcome home!");
		
		MyCounter counter = counterRepo.findFirst();
		
		if (counter == null) {
			counter = new MyCounter();
		}
		counter.count++;
		counterRepo.save(counter);
		
		model.addAttribute("count", counter.count);
		return "home";
	}
}

