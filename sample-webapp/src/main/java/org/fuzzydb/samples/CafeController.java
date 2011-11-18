package org.fuzzydb.samples;

import java.util.ArrayList;
import org.fuzzydb.samples.repositories.CafeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cafes") 
public class CafeController extends AbstractDataController<Cafe> {


	@Autowired
	private CafeRepository cafeRepo;

	
	@Override
	protected void createItem() {
		cafeRepo.save(dataGenerator.createRandomCafe());
	}

	
	@Override
	protected String getDefaultSearchStyle() {
		return "foodEstablishments";
	}

	@Override
	protected CafeRepository getRepo() {
		return cafeRepo;
	}

	@Override
	protected String getViewPathPrefix() {
		return "cafes";
	}

	@Override
	protected Cafe getSearchForm() {
		return new Cafe("Entered search");
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

