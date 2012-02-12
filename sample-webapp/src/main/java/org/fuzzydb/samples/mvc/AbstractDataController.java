package org.fuzzydb.samples.mvc;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.thoughtworks.xstream.XStream;
import com.wwm.attrs.AttributeDefinitionService;
import com.wwm.db.query.Result;
import com.wwm.db.spring.repository.AttributeMatchQuery;
import com.wwm.db.spring.repository.FuzzyRepository;
import com.wwm.db.spring.repository.SubjectMatchQuery;

public abstract class AbstractDataController<ENTITY> {

	@Autowired
	protected DataGenerator dataGenerator;
	
	@Autowired
	private AttributeDefinitionService attrDefs;

	public AbstractDataController() {
		super();
	}

	/**
	 * Insert a random item into the database
	 */
	abstract protected void createItem();
	
	
	abstract protected String getDefaultSearchStyle();

	abstract protected FuzzyRepository<ENTITY, String> getRepo();
	
	abstract protected ENTITY getSearchForm();

	abstract protected String getViewPathPrefix();
	
	
	@Transactional
	@RequestMapping(value="/createItems", method=RequestMethod.GET) 
	public String createItems(@RequestParam(defaultValue="5") int numItems) {
		
		for (int i = 0; i < numItems; i++) {
			createItem();
		}
		return "redirect:search";
	}

	@Transactional(readOnly=true)
	@RequestMapping(value="/search", method=RequestMethod.POST) 
	public void search(
			@RequestParam(required=false) String style,
			@RequestParam(defaultValue="0") int start,
			@RequestParam(defaultValue="10") int pageSize,
			Model model, 
			@ModelAttribute("command") @Valid ENTITY form, 
			Errors result) {
		
		style = style == null ? getDefaultSearchStyle() : style;
		
		Pageable pageable = new PageRequest(start, pageSize);
		doSearch(getRepo(), model, style, null, pageable, form);
	}

	
	/**
	 * 
	 * @param style The name of the matching configuration or 'style'
	 * @param ref A database reference - something we probably don't want in a real app
	 * @param maxResults This lets the database know the maximum number of results you're going to iterate over
     *	 		you could potentially feed results out as you get them, so long as you've got the 
	 *			transaction held open... hmmm. I feel some Ajax coming on ;)
	 */
	@Transactional(readOnly=true)
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public void search(
			Model model, 
			@RequestParam(required=false) String style,
			@RequestParam(required=false) String ref,
			@RequestParam(defaultValue="0") int start,
			@RequestParam(defaultValue="10") int pageSize) {
	
		style = style == null ? getDefaultSearchStyle() : style;

		// We need some attributes to search against.  This doesn't have to be something already in the 
		// database.  For the default, we'll just grab a named sample from our dataGenerator.
		// If we have a key (ref), then we'll use that to grab an item.
		ENTITY idealMatch = StringUtils.hasText(ref) ? getRepo().findOne(ref) : getRepo().findFirst();

		Pageable pageable = new PageRequest(start/pageSize, pageSize);
		doSearch(getRepo(), model, style, ref, pageable, idealMatch);
		model.addAttribute("command", getSearchForm()); 
	}

	@Transactional(readOnly=true)
	@RequestMapping(value="/json/search", method=RequestMethod.GET,
	produces="application/json")
	@ResponseBody
	public Object jsonSearch(
			Model model, 
			@RequestParam(required=false) String style,
			@RequestParam(required=false) String ref,
			@RequestParam(defaultValue="0") int start,
			@RequestParam(defaultValue="10") int pageSize) {
	
		style = style == null ? getDefaultSearchStyle() : style;

		// We need some attributes to search against.  This doesn't have to be something already in the 
		// database.  For the default, we'll just grab a named sample from our dataGenerator.
		// If we have a key (ref), then we'll use that to grab an item.
		ENTITY idealMatch = StringUtils.hasText(ref) ? getRepo().findOne(ref) : getRepo().findFirst();

		Pageable pageable = new PageRequest(start/pageSize, pageSize);
		doSearch(getRepo(), model, style, ref, pageable, idealMatch);
		return model; //.asMap().get("results");
	}

	
	@Transactional(readOnly=true)
	@RequestMapping(value="/json/search", method=RequestMethod.POST,
			consumes="application/json",
			produces="application/json")
	@ResponseBody
	public Object jsonSearch(
		@RequestParam(required=false) String style,
		@RequestParam(defaultValue="0") int start,
		@RequestParam(defaultValue="10") int pageSize,
		Model model, 
		@RequestBody @ModelAttribute("command") @Valid ENTITY form, 
		Errors result) {
	
	style = style == null ? getDefaultSearchStyle() : style;
	
	Pageable pageable = new PageRequest(start, pageSize);
	doSearch(getRepo(), model, style, null, pageable, form);
	return model.asMap().get("results");
}


	@Transactional(readOnly=true)
	@RequestMapping(value="/json/dump", method=RequestMethod.GET,
			produces="application/json")
	@ResponseBody
	public Object jsonDump() throws IOException {
		Iterable<ENTITY> items = getRepo().findAll();
		
		List<ENTITY> list = new ArrayList<ENTITY>();
		for (ENTITY item : items) {
			list.add(item);
		}
		return list;
	}
	
	@Transactional(readOnly=true)
	@RequestMapping(value="/dump", method=RequestMethod.GET)
	public void dump(OutputStream response) throws IOException {

		Iterable<ENTITY> items = getRepo().findAll();
		
		XStream xs = new XStream();
		
		for (ENTITY item : items) {
			xs.toXML(item, response);
		}
	}

	
	@Transactional(readOnly = true)
	protected ArrayList<String> getOptionsForField(String attrName) {
		// TODO: push this in as getOptionsForField(String)
		return attrDefs.getEnumDefForAttrId(attrDefs.getAttrId(attrName)).getValues();
	}
	
	
	protected void doSearch( FuzzyRepository<ENTITY,String> repo, Model model, String style, String ref,
			Pageable pageable, ENTITY idealMatch) {
		// requested match style
		int maxResults = pageable.getOffset() + pageable.getPageSize(); 
		AttributeMatchQuery<ENTITY> query = new SubjectMatchQuery<ENTITY>(idealMatch, style, maxResults);
		
		// Do the actual query
		Page<Result<ENTITY>> results = repo.findMatchesFor(query, pageable);
				
		// Stick 'em in our model for our view to render
		model.addAttribute("subject", idealMatch.toString() == null ? "search" : idealMatch.toString());
		model.addAttribute("ref", ref);
		model.addAttribute("results", results.getContent());
		model.addAttribute("style", style);
		model.addAttribute("startNextPage", results.hasNextPage() ? maxResults : -1);
		model.addAttribute("pageSize", results.getSize());
	}



}