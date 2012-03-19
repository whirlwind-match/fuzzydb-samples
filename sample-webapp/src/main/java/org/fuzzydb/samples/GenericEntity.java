package org.fuzzydb.samples;

import java.util.HashMap;
import java.util.Map;

/**
 * Generic entity to use as target for accepting JSON or XML requests
 */
public class GenericEntity extends BaseEntity {

	private final Map<String, Object> attributes = new HashMap<String,Object>();
	
	public GenericEntity() {
		super();
	}
	
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	
	@Override
	public void setPostcode(String postcode) {
		super.setPostcode(postcode);
	}
}
