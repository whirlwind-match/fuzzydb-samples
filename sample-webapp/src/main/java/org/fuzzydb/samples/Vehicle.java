package org.fuzzydb.samples;


public class Vehicle extends BaseEntity {

	/**
	 * Public constructor needed by some frameworks
	 */
	public Vehicle() {
	}
	
	public Vehicle(String desc) {
		this.description = desc;
	}

	
	private String make;

	private String model;

	private float horsePower;
	
	private float mpgCombined;
	
	/** gCO2/km */
	private float co2emissions;

	private float price;
	


	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public float getHorsePower() {
		return horsePower;
	}

	public void setHorsePower(float horsePower) {
		this.horsePower = horsePower;
	}

	public float getMpgCombined() {
		return mpgCombined;
	}

	public void setMpgCombined(float mpgCombined) {
		this.mpgCombined = mpgCombined;
	}

	public float getCo2emissions() {
		return co2emissions;
	}

	public void setCo2emissions(float co2emissions) {
		this.co2emissions = co2emissions;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return description;
	}

}
