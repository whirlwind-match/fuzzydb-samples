package org.fuzzydb.samples;

import org.springframework.data.annotation.Id;

import com.wwm.model.dimensions.IPoint3D;

public class Cafe {

	/**
	 * Public constructor needed by some frameworks
	 */
	public Cafe() {
	}
	
	public Cafe(String desc) {
		this.description = desc;
	}


	@Id
	private String ref;

	private String description;
	
	private IPoint3D location;

	private String postcode;
	
	private String[] mealTypes;

	
	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public IPoint3D getLocation() {
		return location;
	}

	public void setLocation(IPoint3D location) {
		this.location = location;
	}
	
	public String[] getMealTypes() {
		return mealTypes;
	}

	public void setMealTypes(String[] mealTypes) {
		this.mealTypes = mealTypes;
	}

	public String getPostcode() {
		return postcode;
	}
	
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	@Override
	public String toString() {
		return description;
	}

}
