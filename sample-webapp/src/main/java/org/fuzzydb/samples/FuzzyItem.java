package org.fuzzydb.samples;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.Digits;

import org.springframework.data.annotation.Id;

public class FuzzyItem implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String description;
	
	
	private final Map<String, Object> attributes = new HashMap<String,Object>();
	
	@Digits(integer=2, fraction=1)
	private Float age;
	
	private String[] newspapers;

	@Id
	private String ref;

	
	/**
	 * Public constructor needed by some frameworks
	 */
	public FuzzyItem() {
	}
	
	public FuzzyItem(String desc) {
		this.description = desc;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}
	
	public Object getAttr(String name) {
		return attributes.get(name);
	}
	
	public void setAttr(String name, Object value) {
		attributes.put(name, value);
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getRef() {
		return ref;
	}
	
	public Float getAge() {
		return age;
	}

	public void setAge(Float age) {
		this.age = age;
	}
	
	public String[] getNewspapers() {
		return newspapers;
	}
	
	public void setNewspapers(String[] newspapers) {
		this.newspapers = newspapers;
	}

	@Override
	public String toString() {
		return description;
	}
}