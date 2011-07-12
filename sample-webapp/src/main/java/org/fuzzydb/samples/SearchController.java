package org.fuzzydb.samples;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wwm.db.query.Result;
import com.wwm.db.spring.repository.AttributeMatchQuery;
import com.wwm.db.spring.repository.FuzzyRepository;
import com.wwm.db.spring.repository.SimpleAttributeMatchQuery;

/**
 * Example controller for creating fuzzy items and querying them
 */
@Controller
public class SearchController {


	@Autowired
	private DataGenerator dataGenerator;
	
	@Autowired
	@Qualifier("itemRepository")
	private FuzzyRepository<FuzzyItem> itemRepo;


	@Transactional
	@RequestMapping(value="/createMatt", method=RequestMethod.GET) 
	public String saveMatt(Model model) {
		itemRepo.save(dataGenerator.createPerson("Matt"));
		return "redirect:/";
	}

	
	@Transactional
	@RequestMapping(value="/createMorePeople", method=RequestMethod.GET) 
	public String createMorePeople() {
		
		itemRepo.save(dataGenerator.createPerson("Angelina"));
		itemRepo.save(dataGenerator.createPerson("Brad"));
		itemRepo.save(dataGenerator.createPerson("Neale"));
		return "redirect:/";
	}

	
	@Transactional
	@RequestMapping(value="/matches", method=RequestMethod.GET)
	public String findMatches(
			Model model, 
			@RequestParam(defaultValue="Matt") String name,
			@RequestParam(defaultValue="similarPeople") String style) {
	
		FuzzyItem subject = dataGenerator.createPerson(name);
		AttributeMatchQuery<FuzzyItem> query = new SimpleAttributeMatchQuery<FuzzyItem>(subject, style, 10);
		List<Result<FuzzyItem>> results = Utils.toList(itemRepo.findMatchesFor(query));
		
		model.addAttribute("heading", "Matches for " + subject + ":");
		model.addAttribute("results", results);
		model.addAttribute("style", style);
		return "results";
	}
}

