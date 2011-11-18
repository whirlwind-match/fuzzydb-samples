package org.fuzzydb.samples;



public class Cafe extends BaseEntity {

	/**
	 * Public constructor needed by some frameworks
	 */
	public Cafe() {
	}
	
	public Cafe(String desc) {
		this.description = desc;
	}


	private String establishmentType;

	private String[] foodSourcingPolicy;
	
	private String[] mealTypes;
	

	public String getEstablishmentType() {
		return establishmentType;
	}

	public void setEstablishmentType(String establishmentType) {
		this.establishmentType = establishmentType;
	}

	public String[] getFoodSourcingPolicy() {
		return foodSourcingPolicy;
	}

	public void setFoodSourcingPolicy(String[] foodSourcingPolicy) {
		this.foodSourcingPolicy = foodSourcingPolicy;
	}

	public String[] getMealTypes() {
		return mealTypes;
	}

	public void setMealTypes(String[] mealTypes) {
		this.mealTypes = mealTypes;
	}

	@Override
	public String toString() {
		return description;
	}
}
