package org.fuzzydb.samples;

import java.util.ArrayList;
import javax.validation.Valid;

import org.fuzzydb.samples.repositories.CafeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class CafeController extends AbstractDataController {


	@Autowired
	private CafeRepository cafeRepo;


	
	@Transactional
	@RequestMapping(value="/cafes/create", method=RequestMethod.GET) 
	public ModelAndView createPeople(@RequestParam(defaultValue="5") int numPeople) {
		
		for (int i = 0; i < numPeople; i++) {
			cafeRepo.save(dataGenerator.createRandomCafe());
		}
		return new ModelAndView(new RedirectView("cafe/search", false, true, false)); //"redirect:/search";
	}

	@Transactional(readOnly=true)
	@RequestMapping(value="/cafes/search", method=RequestMethod.POST) 
	public String search(
			@RequestParam(defaultValue="foodEstablishments") String style,
			@RequestParam(defaultValue="0") int start,
			@RequestParam(defaultValue="10") int pageSize,
			Model model, 
			@ModelAttribute("command") @Valid Cafe form, 
			Errors result) {
		
		Pageable pageable = new PageRequest(start, pageSize);
		doSearch(cafeRepo, model, style, null, pageable, form);
		
		return "cafes/results";
	}

	
	@Transactional(readOnly=true)
	@RequestMapping(value="/cafes/search", method=RequestMethod.GET)
	public String findMatches(
			Model model, 
			@RequestParam(defaultValue="foodEstablishments") String style,
			@RequestParam(required=false) String ref,
			@RequestParam(defaultValue="0") int start,
			@RequestParam(defaultValue="10") int pageSize) {
	
		// We need some attributes to search against.  This doesn't have to be something already in the 
		// database.  For the default, we'll just grab a named sample from our dataGenerator.
		// If we have a key (ref), then we'll use that to grab an item.
		Cafe idealMatch = StringUtils.hasText(ref) ? cafeRepo.findOne(ref) : cafeRepo.findFirst();

		Pageable pageable = new PageRequest(start/pageSize, pageSize);
		doSearch(cafeRepo, model, style, ref, pageable, idealMatch);
		model.addAttribute("command", new Cafe("Entered search"));
		return "cafes/results";
	}

	
	@ModelAttribute("mealTypes")
	public ArrayList<String> getSmokeOptions() {
		return getOptionsForField("mealTypes");
	}

	@ModelAttribute("establishmentTypes")
	public ArrayList<String> getEstablishmentTypes() {
		return getOptionsForField("establishmentType");
	}
}

