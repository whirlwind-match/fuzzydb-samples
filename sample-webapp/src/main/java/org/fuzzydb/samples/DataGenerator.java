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
		randomSource.configureFloatAttr("age", 13f, 100f, 0f);
		randomSource.configureFloatAttr("salary", 5000f, 1e6f, 0.05f);
		randomSource.configureEnumAttr("smoke", 0.05f);
		randomSource.configureMultiEnumAttr("newspapers", 0.01f);
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
		addRandomAttr(item, "newspapers");
		addRandomAttr(item, "smoke");
		
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
	}

	private void addRandomAttr(FuzzyItem item, String attr) {
		Attribute<?> random = randomSource.getRandom(attr);
		if (random != null) {
			item.setAttr(attr, random.getValueAsObject());
		}
	}

}
