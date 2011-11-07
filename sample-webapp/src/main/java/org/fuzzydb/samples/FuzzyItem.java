package org.fuzzydb.samples;

import java.io.Serializable;
import javax.validation.constraints.Digits;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import com.wwm.db.spring.annotation.DerivedField;
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
	
	@DerivedField(value="postcode")
	private IPoint3D location;
	
	private String[] newspapers;

	private String postcode; 
	
	private Float salary;
	
	private String smoke;

	@Transient // not indexed
	@DerivedField(value="workPostcode")
	private IPoint3D workLocation;
	
	private String workPostcode;

	
	/**
	 * Public constructor needed by some frameworks
	 */
	public FuzzyItem() {
	}
	
	public FuzzyItem(String desc) {
		this.description = desc;
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

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
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

	public IPoint3D getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(IPoint3D workLocation) {
		this.workLocation = workLocation;
	}

	public String getWorkPostcode() {
		return workPostcode;
	}

	public void setWorkPostcode(String workPostcode) {
		this.workPostcode = workPostcode;
	}

	@Override
	public String toString() {
		return description;
	}
}