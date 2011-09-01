package org.fuzzydb.samples;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wwm.db.spring.random.RandomAttributeSource;
import com.wwm.model.attributes.Attribute;

@Component
public class DataGenerator implements InitializingBean {

	@Autowired
	private RandomAttributeSource randomSource;
	
	private Map<String, FuzzyItem> people = new HashMap<String, FuzzyItem>();
	
	public DataGenerator() {
		addHardcodedPeople();
	}

	
	@Override
	public void afterPropertiesSet() throws Exception {
		randomSource.configureFloatAttr("age", 13f, 100f, 0);
		randomSource.configureFloatAttr("salary", 5000f, 1e6f, 10);
	}

	
	public FuzzyItem createPerson(String key) {
		return people.get(key);
	}
	
	public FuzzyItem createRandomPerson() {
		String name = "Anon"; // TODO randomise
		FuzzyItem item = new FuzzyItem(name);
		addRandomAttr(item, "isMale");
		addRandomAttr(item, "age");
		addRandomAttr(item, "salary");
		
		return item;
	}


	private void addHardcodedPeople() {
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
		brad.setAttr("salary", 547000f);
		brad.setAttr("newspapers", new String[]{"LA Times"});
		people.put("Brad", brad);

		FuzzyItem neale = new FuzzyItem("Neale");
		neale.setAttr("isMale", Boolean.TRUE);
		neale.setAttr("age", 21f); // I wish (sort of)
		neale.setAttr("salary", 25000f);
		neale.setAttr("smoke", "Non-smoker");
		neale.setAttr("newspapers", new String[]{"Guardian"});
		people.put("Neale", neale);

		FuzzyItem wayne = new FuzzyItem("Wayne");
		wayne.setAttr("isMale", Boolean.TRUE);
		wayne.setAttr("age", 33f); 
		wayne.setAttr("salary", 2500000f);
		wayne.setAttr("smoke", "Non-smoker");
		wayne.setAttr("newspapers", new String[]{"Sun"});
		people.put("Wayne", wayne);
	}

	private void addRandomAttr(FuzzyItem item, String attr) {
		Attribute<?> random = randomSource.getRandom(attr);
		if (random != null) {
			item.setAttr(attr, random.getValueAsObject());
		}
	}

}
