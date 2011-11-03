package org.fuzzydb.samples;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.wwm.attrs.location.EcefVector;
import com.wwm.db.spring.random.RandomAttributeSource;
import com.wwm.geo.GeoInformation;
import com.wwm.model.attributes.Attribute;
import com.wwm.model.attributes.NonIndexStringAttribute;
import com.wwm.model.dimensions.IPoint3D;
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
//		Attribute<String> randomAttr = addRandomAttr(item, "postcode"); // TODO Could leave out
		NonIndexStringAttribute randomAttr = randomPostcodes.next("not used");
		if (randomAttr != null) {
			GeoInformation location = converter.convert(randomAttr.getValueAsObject());
			IPoint3D vector = EcefVector.fromDegs(0, location.getLatitude(), location.getLongitude());
			item.setLocation(vector);
		}
		
		return item;
	}


	private void addHardcodedPeople() {
		FuzzyItem matt = new FuzzyItem("Matt");
		matt.setIsMale(Boolean.TRUE);
//		matt.setAttr("age", 32f);
//		matt.setAttr("ageRange", new float[]{25f, 32f, 38f}); // A perfect match for own age
//		matt.setAttr("salary", 500000f);
//		matt.setAttr("smoke", "Cigar-smoker");
//		matt.setAttr("newspapers", new String[]{"LA Times", "New York Times"});
		GeoInformation location = converter.convert("CB1");
		IPoint3D vector = EcefVector.fromDegs(0, location.getLatitude(), location.getLongitude());
		matt.setLocation(vector);

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
