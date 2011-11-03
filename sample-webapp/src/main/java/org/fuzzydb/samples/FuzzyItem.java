package org.fuzzydb.samples;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.Digits;

import org.springframework.data.annotation.Id;

import com.wwm.model.dimensions.IPoint3D;

public class FuzzyItem implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private String ref;

	private String description;
	
	
	
	private Boolean isMale;
	
	@Digits(integer=2, fraction=1)
	private Float age;
	
	private float[] ageRange;
	
	private Float salary;
	
	private String smoke;
	
	private String[] newspapers;

	private IPoint3D location;

	private final Map<String, Object> attributes = new HashMap<String,Object>();
	
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
	
	public float[] getAgeRange() {
		return ageRange;
	}

	public void setAgeRange(float[] ageRange) {
		this.ageRange = ageRange;
	}

	public Boolean getIsMale() {
		return isMale;
	}
	
	public void setIsMale(Boolean isMale) {
		this.isMale = isMale;
	}
	
	public IPoint3D getLocation() {
		return location;
	}

	public void setLocation(IPoint3D location) {
		this.location = location;
	}

	public String[] getNewspapers() {
		return newspapers;
	}
	
	public void setNewspapers(String[] newspapers) {
		this.newspapers = newspapers;
	}

	public Float getSalary() {
		return salary;
	}
	
	public void setSalary(Float salary) {
		this.salary = salary;
	}
	
	public String getSmoke() {
		return smoke;
	}

	public void setSmoke(String smoke) {
		this.smoke = smoke;
	}

	@Override
	public String toString() {
		return description;
	}
}