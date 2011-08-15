package org.fuzzydb.samples;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.thoughtworks.xstream.XStream;
import com.wwm.db.query.Result;
import com.wwm.db.spring.repository.AttributeMatchQuery;
import com.wwm.db.spring.repository.FuzzyRepository;
import com.wwm.db.spring.repository.SubjectMatchQuery;

/**
 * Example controller for creating fuzzy items and querying them
 */
@Controller
public class SearchController {


	@Autowired
	private DataGenerator dataGenerator;
	
	/**
	 * A repository provided the line
	 * <code>&lt;fuzzy:repository id="itemRepository" class="org.fuzzydb.samples.FuzzyItem" useDefaultNamespace="true" /></code>
	 * in the application context.
	 */
	@Autowired
	@Qualifier("itemRepository")
	private FuzzyRepository<FuzzyItem> itemRepo;


	
	@Transactional
	@RequestMapping(value="/createPeople", method=RequestMethod.GET) 
	public String createPeople() {
		
		itemRepo.save(dataGenerator.createPerson("Matt"));
		itemRepo.save(dataGenerator.createPerson("Angelina"));
		itemRepo.save(dataGenerator.createPerson("Brad"));
		itemRepo.save(dataGenerator.createPerson("Neale"));
		itemRepo.save(dataGenerator.createPerson("Wayne"));
		return "redirect:/matches";
	}

	
	@Transactional(readOnly=true)
	@RequestMapping(value="/matches", method=RequestMethod.GET)
	public String findMatches(
			Model model, 
			@RequestParam(defaultValue="Matt") String name,
			@RequestParam(defaultValue="similarPeople") String style) {
	
		// We need some attributes to search against.  This doesn't have to be something already in the 
		// database.  For ease, we'll just grab a named sample from our dataGenerator
		FuzzyItem subject = dataGenerator.createPerson(name);

		// This lets the database know the maximum number of results you're going to iterate over
		// you could potentially feed results out as you get them, so long as you've got the 
		// transaction held open... hmmm. I feel some Ajax coming on ;)
		int maxResults = 10; 
		                    
		// A SubjectMatchQuery looks for the best matches for a provided subject, according to the
		// requested match style
		AttributeMatchQuery<FuzzyItem> query = new SubjectMatchQuery<FuzzyItem>(subject, style, maxResults);
		
		// Do the actual query
		Iterator<Result<FuzzyItem>> resultIterator = itemRepo.findMatchesFor(query);
		
		// Extract the results
		List<Result<FuzzyItem>> results = Utils.toList(resultIterator);
		
		// Stick 'em in our model for our view to render
		model.addAttribute("subject", subject);
		model.addAttribute("results", results);
		model.addAttribute("style", style);
		return "results";
	}


	@Transactional(readOnly=true)
	@RequestMapping(value="/dump", method=RequestMethod.GET)
	public void dump(OutputStream response) throws IOException {

		Iterable<FuzzyItem> people = itemRepo.findAll();
		
		XStream xs = new XStream();
		
		for (FuzzyItem item : people) {
			xs.toXML(item, response);
		}
	}
	
	@Transactional(readOnly=true)
	@RequestMapping(value="/dumpMatches", method=RequestMethod.GET)
	public void dumpMatches(
			Model model, 
			@RequestParam(defaultValue="Matt") String name,
			@RequestParam(defaultValue="similarPeople") String style,
			OutputStream response) {
	
		FuzzyItem subject = dataGenerator.createPerson(name);
		int maxResults = 10; 
		AttributeMatchQuery<FuzzyItem> query = new SubjectMatchQuery<FuzzyItem>(subject, style, maxResults);
		Iterator<Result<FuzzyItem>> resultIterator = itemRepo.findMatchesFor(query);
		
		XStream xs = new XStream();
		
		for (Iterator<Result<FuzzyItem>> iterator = resultIterator; iterator.hasNext();) {
			Result<FuzzyItem> item = iterator.next();
			
			xs.toXML(item.getItem(), response);
		}
	}
}

