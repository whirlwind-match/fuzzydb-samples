package org.fuzzydb.samples;

import javax.validation.constraints.Digits;

import org.fuzzydb.spring.annotation.DerivedField;
import org.springframework.data.annotation.Transient;

import com.wwm.model.dimensions.IPoint3D;

public class Person extends BaseEntity {
	
	private Boolean isMale;
	
	@Digits(integer=2, fraction=1)
	private Float age;
	
	private float[] ageRange;
	
	private String[] newspapers;

	private Float salary;
	
	private String smoke;

	@Transient // not indexed
	@DerivedField(value="workPostcode")
	private IPoint3D workLocation;
	
	private String workPostcode;

	
	/**
	 * Public constructor needed by some frameworks
	 */
	public Person() {
	}
	
	public Person(String desc) {
		this.description = desc;
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