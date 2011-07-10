package org.fuzzydb.samples;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wwm.db.query.Result;
import com.wwm.db.spring.repository.AttributeMatchQuery;
import com.wwm.db.spring.repository.FuzzyRepository;
import com.wwm.db.spring.repository.SimpleAttributeMatchQuery;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	@Qualifier("counterRepository")
	private FuzzyRepository<MyCounter> counterRepo;

	@Autowired
	@Qualifier("itemRepository")
	private FuzzyRepository<FuzzyItem> itemRepo;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
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

	@Transactional
	@RequestMapping(value="/createMatt", method=RequestMethod.GET) 
	public String saveMatt(Model model) {
		FuzzyItem matt = createMatt();
		itemRepo.save(matt);
		return "redirect:/";
	}

	private FuzzyItem createMatt() {
		FuzzyItem matt = new FuzzyItem("Matt");
		matt.setAttr("isMale", Boolean.TRUE);
		matt.setAttr("age", 32f);
		matt.setAttr("ageRange", new float[]{25f, 32f, 38f}); // A perfect match for own age
		matt.setAttr("salary", 500000f);
		matt.setAttr("smoke", "Cigar-smoker");
		matt.setAttr("newspapers", new String[]{"LA Times", "New York Times"});
		return matt;
	}
	
	@Transactional
	@RequestMapping(value="/createMorePeople", method=RequestMethod.GET) 
	public String createMorePeople() {
		
		FuzzyItem angelina = new FuzzyItem("Angelina");
		angelina.setAttr("isMale", Boolean.FALSE);
		angelina.setAttr("age", 35f);
		angelina.setAttr("ageRange", new float[]{30f, 37f, 50f});
		angelina.setAttr("salary", 500000f);
		angelina.setAttr("smoke", "Cigarette-smoker");
		angelina.setAttr("newspapers", new String[]{"Guardian", "New York Times"});
		itemRepo.save(angelina);

		FuzzyItem brad = new FuzzyItem("Brad");
		brad.setAttr("isMale", Boolean.TRUE);
		brad.setAttr("age", 37f);
		brad.setAttr("ageRange", new float[]{22f, 30f, 40f});
		brad.setAttr("salary", 550000f);
		itemRepo.save(brad);

		FuzzyItem neale = new FuzzyItem("Neale");
		neale.setAttr("isMale", Boolean.TRUE);
		neale.setAttr("age", 21f); // I wish (sort of)
		neale.setAttr("salary", 25000f);
		neale.setAttr("smoke", "Non-smoker");
		itemRepo.save(neale);
		
		return "redirect:/";
	}

	@Transactional
	@RequestMapping(value="/mattsMatches", method=RequestMethod.GET)
	public String findMatchesForMatt(Model model) {
	
//		xdo search here...
		FuzzyItem matt = createMatt();
		AttributeMatchQuery<FuzzyItem> query = new SimpleAttributeMatchQuery<FuzzyItem>(matt, "similarPeople", 10);
		List<Result<FuzzyItem>> results = toList(itemRepo.findMatchesFor(query));
		
		model.addAttribute("heading", "Matt's matches:");
		model.addAttribute("results", results);
		return "results";
	}
	
	public static <T> List<T> toList(Iterator<T> items) {
		Assert.notNull(items);
		
		List<T> list = new LinkedList<T>();
		for (Iterator<T> iterator = items; iterator.hasNext();) {
			T item = iterator.next();
			list.add(item);
		}
		return list;
	}

}

