package org.fuzzydb.samples;

import org.fuzzydb.dto.dimensions.IPoint3D;
import org.fuzzydb.spring.annotation.DerivedField;
import org.springframework.data.annotation.Id;


public class BaseEntity {

	@Id
	private String ref;
	protected String description;
	@DerivedField("postcode")
	private IPoint3D location;
	private String postcode;

	public BaseEntity() {
		super();
	}

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

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

}