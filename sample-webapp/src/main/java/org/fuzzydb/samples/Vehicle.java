package org.fuzzydb.samples;


public class Vehicle extends BaseEntity {

	private String carMake;
	
	/** gCO2/km */
	private Float co2emissions;
	
	private String colour;
	
	private Float horsePower;
	
	private String make;

	private String model;

	private Float mpgCombined;
	
	private String[] options;
	
	private Float price;

	private Float year;
	/**
	 * Public constructor needed by some frameworks
	 */
	public Vehicle() {
	}
	
	public Vehicle(String desc) {
		this.description = desc;
	}

	public String getCarMake() {
		return carMake;
	}

	public Float getCo2emissions() {
		return co2emissions;
	}

	
	public String getColour() {
		return colour;
	}

	public Float getHorsePower() {
		return horsePower;
	}
	
	public String getMake() {
		return make;
	}
	
	public String getModel() {
		return model;
	}

	public Float getMpgCombined() {
		return mpgCombined;
	}

	public String[] getOptions() {
		return options;
	}

	public Float getPrice() {
		return price;
	}

	public Float getYear() {
		return year;
	}
	
	public void setCarMake(String carMake) {
		this.carMake = carMake;
	}

	public void setCo2emissions(Float co2emissions) {
		this.co2emissions = co2emissions;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}
	
	public void setHorsePower(Float horsePower) {
		this.horsePower = horsePower;
	}
	
	public void setMake(String make) {
		this.make = make;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setMpgCombined(Float mpgCombined) {
		this.mpgCombined = mpgCombined;
	}

	public void setOptions(String[] options) {
		this.options = options;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
	
	public void setYear(Float year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return description;
	}

}
