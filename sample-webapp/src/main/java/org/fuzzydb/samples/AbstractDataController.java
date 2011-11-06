package org.fuzzydb.samples;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.wwm.attrs.AttributeDefinitionService;
import com.wwm.db.query.Result;
import com.wwm.db.spring.repository.AttributeMatchQuery;
import com.wwm.db.spring.repository.FuzzyRepository;
import com.wwm.db.spring.repository.SubjectMatchQuery;

public class AbstractDataController {

	@Autowired
	protected DataGenerator dataGenerator;
	
	@Autowired
	private AttributeDefinitionService attrDefs;

	public AbstractDataController() {
		super();
	}

	@Transactional(readOnly = true)
	protected ArrayList<String> getOptionsForField(String attrName) {
		// TODO: push this in as getOptionsForField(String)
		return attrDefs.getEnumDefForAttrId(attrDefs.getAttrId(attrName)).getValues();
	}
	
	protected <T> void doSearch( FuzzyRepository<T,String> repo, Model model, String style, String ref,
			Pageable pageable, T idealMatch) {
		// requested match style
		int maxResults = pageable.getOffset() + pageable.getPageSize(); 
		AttributeMatchQuery<T> query = new SubjectMatchQuery<T>(idealMatch, style, maxResults);
		
		// Do the actual query
		Page<Result<T>> results = repo.findMatchesFor(query, pageable);
				
		// Stick 'em in our model for our view to render
		model.addAttribute("subject", idealMatch);
		model.addAttribute("ref", ref);
		model.addAttribute("results", results.getContent());
		model.addAttribute("style", style);
		model.addAttribute("startNextPage", results.hasNextPage() ? maxResults : -1);
		model.addAttribute("pageSize", results.getSize());
	}



}