package org.fuzzydb.samples.mvc;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.fuzzydb.samples.Person;
import org.fuzzydb.samples.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.thoughtworks.xstream.XStream;
import com.wwm.db.query.Result;
import com.wwm.db.spring.repository.AttributeMatchQuery;
import com.wwm.db.spring.repository.SubjectMatchQuery;

/**
 * Example controller for creating fuzzy items and querying them
 */
@Controller
@RequestMapping("/people")
public class PeopleController extends AbstractDataController<Person> {


	@Autowired
	private PersonRepository personRepo;


	@Override
	protected void createItem() {
		personRepo.save(dataGenerator.createRandomPerson());
	}
	
	@Override
	protected String getDefaultSearchStyle() {
		return "similarPeople";
	}

	@Override
	protected PersonRepository getRepo() {
		return personRepo;
	}
	
	@Override
	protected Person getSearchForm() {
		return new Person("Entered search");
	}

	@Override
	protected String getViewPathPrefix() {
		return "people";
	}



	@ModelAttribute("newspapers")
	public ArrayList<String> getNewspaperOptions() {
		return getOptionsForField("newspapers");
	}
	
	@ModelAttribute("smokeOptions")
	public ArrayList<String> getSmokeOptions() {
		return getOptionsForField("smoke");
	}

	
	@Transactional(readOnly=true)
	@RequestMapping(value="/dumpMatches", method=RequestMethod.GET)
	public void dumpMatches(
			Model model, 
			@RequestParam(defaultValue="Matt") String name,
			@RequestParam(defaultValue="similarPeople") String style,
			OutputStream response) {
	
		Person subject = dataGenerator.createPerson(name);
		int maxResults = 10; 
		AttributeMatchQuery<Person> query = new SubjectMatchQuery<Person>(subject, style, maxResults + 1); // + 1 so we can check if there are more results
		Iterator<Result<Person>> resultIterator = personRepo.findMatchesFor(query);
		
		XStream xs = new XStream();
		
		for (Iterator<Result<Person>> iterator = resultIterator; iterator.hasNext();) {
			Result<Person> item = iterator.next();
			
			xs.toXML(item.getItem(), response);
		}
	}
}

