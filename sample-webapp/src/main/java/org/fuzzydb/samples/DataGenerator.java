package org.fuzzydb.samples;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class DataGenerator {

	private Map<String, FuzzyItem> people = new HashMap<String, FuzzyItem>();
	
	public DataGenerator() {
		FuzzyItem matt = new FuzzyItem("Matt");
		matt.setAttr("isMale", Boolean.TRUE);
		matt.setAttr("age", 32f);
		matt.setAttr("ageRange", new float[]{25f, 32f, 38f}); // A perfect match for own age
		matt.setAttr("salary", 500000f);
		matt.setAttr("smoke", "Cigar-smoker");
		matt.setAttr("newspapers", new String[]{"LA Times", "New York Times"});
		people.put("Matt", matt);
		
		FuzzyItem angelina = new FuzzyItem("Angelina");
		angelina.setAttr("isMale", Boolean.FALSE);
		angelina.setAttr("age", 35f);
		angelina.setAttr("ageRange", new float[]{30f, 37f, 50f});
		angelina.setAttr("salary", 500000f);
		angelina.setAttr("smoke", "Cigarette-smoker");
		angelina.setAttr("newspapers", new String[]{"Guardian", "New York Times"});
		people.put("Angelina", angelina);

		FuzzyItem brad = new FuzzyItem("Brad");
		brad.setAttr("isMale", Boolean.TRUE);
		brad.setAttr("age", 37f);
		brad.setAttr("ageRange", new float[]{22f, 30f, 40f});
		brad.setAttr("salary", 549999f);
		people.put("Brad", brad);

		FuzzyItem neale = new FuzzyItem("Neale");
		neale.setAttr("isMale", Boolean.TRUE);
		neale.setAttr("age", 21f); // I wish (sort of)
		neale.setAttr("salary", 25000f);
		neale.setAttr("smoke", "Non-smoker");
		people.put("Neale", neale);

	}
	
	
	public FuzzyItem createPerson(String key) {
		return people.get(key);
	}

}
