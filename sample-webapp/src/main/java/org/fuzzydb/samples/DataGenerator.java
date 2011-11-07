package org.fuzzydb.samples;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.wwm.db.spring.random.RandomAttributeSource;
import com.wwm.geo.GeoInformation;
import com.wwm.model.attributes.Attribute;
import com.wwm.postcode.RandomUKShortPostcode;

@Component
public class DataGenerator implements InitializingBean {

	@Autowired
	private RandomAttributeSource randomSource;
	
	@Autowired
	private Converter<String, GeoInformation> converter;

	private final RandomUKShortPostcode randomPostcodes = new RandomUKShortPostcode();
	
	private final Map<String, FuzzyItem> people = new HashMap<String, FuzzyItem>();
	
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

		addHardcodedPeople();
	}

	
	public FuzzyItem createPerson(String key) {
		return people.get(key);
	}
	
	public FuzzyItem createRandomPerson() {
		String name = "Anon"; // TODO randomise
		FuzzyItem item = new FuzzyItem(name);
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
		FuzzyItem matt = new FuzzyItem("Matt");
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


	public Cafe createRandomCafe() {
		// TODO Auto-generated method stub
		return null;
	}
}
