package org.fuzzydb.samples;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wwm.db.spring.random.RandomAttributeSource;
import com.wwm.model.attributes.Attribute;
import com.wwm.postcode.RandomUKShortPostcode;

@Component
public class DataGenerator implements InitializingBean {

	@Autowired
	private RandomAttributeSource randomSource;
	
	private final RandomUKShortPostcode randomPostcodes = new RandomUKShortPostcode();
	
	private final Map<String, Person> people = new HashMap<String, Person>();
	
	public DataGenerator() {
	}

	
	@Override
	public void afterPropertiesSet() throws Exception {
		randomSource.configureFloatAttr("age", 13f, 100f, 0f);
		randomSource.configureFloatAttr("salary", 5000f, 1e6f, 0.05f);
		randomSource.configureEnumAttr("smoke", 0.05f);
		randomSource.configureMultiEnumAttr("newspapers", 0.01f);
		randomSource.addRandomGenerator("postcode", randomPostcodes);
		randomSource.addRandomGenerator("workPostcode", randomPostcodes);

		// Cafes
		randomSource.configureEnumAttr("establishmentType", 0.05f);
		randomSource.configureMultiEnumAttr("foodSourcingPolicy", 0.01f);
		randomSource.configureMultiEnumAttr("mealTypes", 0.01f);

		// Autos
		randomSource.configureFloatAttr("price", 50f, 100000f, 0f);
		randomSource.configureFloatAttr("horsePower", 50f, 700f, 0f);
		randomSource.configureFloatAttr("mpgCombined", 10f, 80f, 0.1f);
		randomSource.configureFloatAttr("co2emissions", 80f, 450f, 0.15f);
		randomSource.configureMultiEnumAttr("options", 0.01f);
		
		addHardcodedPeople();
	}

	public Cafe createRandomCafe() {
		String name = "Generics";
		Cafe item = new Cafe(name);
		BeanWrapper wrapper = new BeanWrapperImpl(item);

		addRandomAttr(wrapper, "establishmentType");
		addRandomAttr(wrapper, "foodSourcingPolicy");
		addRandomAttr(wrapper, "mealTypes");
		addRandomAttr(wrapper, "postcode");
				
		return item;
	}


	public Vehicle createRandomVehicle() {
		String name = "Wreck";
		Vehicle item = new Vehicle(name);
		BeanWrapper wrapper = new BeanWrapperImpl(item);

		addRandomAttr(wrapper, "postcode");
		addRandomAttr(wrapper, "price");
		addRandomAttr(wrapper, "horsePower");
		addRandomAttr(wrapper, "mpgCombined");
		addRandomAttr(wrapper, "co2emissions");
		addRandomAttr(wrapper, "options");
				
		return item;
	}

	
	
	public Person createPerson(String key) {
		return people.get(key);
	}
	
	public Person createRandomPerson() {
		String name = "Anon"; // TODO randomise
		Person item = new Person(name);
		BeanWrapper wrapper = new BeanWrapperImpl(item);

		addRandomAttr(wrapper, "isMale");
		addRandomAttr(wrapper, "age");
		addRandomAttr(wrapper, "salary");
		addRandomAttr(wrapper, "newspapers");
		addRandomAttr(wrapper, "smoke");
		addRandomAttr(wrapper, "postcode");
		addRandomAttr(wrapper, "workPostcode");
				
		return item;
	}


	private void addHardcodedPeople() {
		Person matt = new Person("Matt");
		matt.setIsMale(Boolean.TRUE);
		matt.setAge(32f);
		matt.setAgeRange(new float[]{25f, 32f, 38f}); // A perfect match for own age
		matt.setSalary(500000f);
		matt.setSmoke("Cigar-smoker");
		matt.setNewspapers(new String[]{"LA Times", "New York Times"});
		matt.setPostcode("CB1");

		people.put("Matt", matt);
	}

	@SuppressWarnings("unchecked")
	private <T> Attribute<T> addRandomAttr(BeanWrapper wrapper, String attr) {
		Attribute<?> random = randomSource.getRandom(attr);
		if (random != null) {
			// If directly settable prop then set it
			if (wrapper.isWritableProperty(attr)) {
				wrapper.setPropertyValue(attr, random.getValueAsObject());
			}
			else {
				wrapper.setPropertyValue("attributes[" + attr + "]", random.getValueAsObject());
				//	was			item.setAttr(attr, random.getValueAsObject());
			}
		}
			
		return (Attribute<T>) random;
	}

}
