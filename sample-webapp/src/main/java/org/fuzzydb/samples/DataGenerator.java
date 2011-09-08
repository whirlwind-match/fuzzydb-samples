package org.fuzzydb.samples;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.wwm.attrs.location.EcefVector;
import com.wwm.db.spring.random.RandomAttributeSource;
import com.wwm.geo.GeoInformation;
import com.wwm.model.attributes.Attribute;
import com.wwm.model.attributes.NonIndexStringAttribute;
import com.wwm.model.attributes.Point3DAttribute;
import com.wwm.postcode.RandomUKShortPostcode;

@Component
public class DataGenerator implements InitializingBean {


	@Autowired
	private RandomAttributeSource randomSource;
	
	@Autowired
	private Converter<String, GeoInformation> converter;

	private final RandomUKShortPostcode randomPostcodes = new RandomUKShortPostcode();
	
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
		randomSource.addRandomGenerator("postcode", randomPostcodes);
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
//		Attribute<String> randomAttr = addRandomAttr(item, "postcode"); // TODO Could leave out
		NonIndexStringAttribute randomAttr = randomPostcodes.next("not used");
		if (randomAttr != null) {
			GeoInformation location = converter.convert(randomAttr.getValueAsObject());
			Point3DAttribute vector = new Point3DAttribute("location", EcefVector.fromDegs(0, location.getLatitude(), location.getLongitude()));
			item.setAttr("location", vector);
		}
		
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

	@SuppressWarnings("unchecked")
	private <T> Attribute<T> addRandomAttr(FuzzyItem item, String attr) {
		Attribute<?> random = randomSource.getRandom(attr);
		if (random != null) {
			item.setAttr(attr, random.getValueAsObject());
		}
		return (Attribute<T>) random;
	}

}
