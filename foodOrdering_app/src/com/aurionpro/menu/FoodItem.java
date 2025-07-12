package com.aurionpro.menu;

import java.io.Serializable;

public class FoodItem implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int foodItemId;
	private String cuisine;
	private String name;
	private double price;
	private FoodType foodType;

	public int getFoodItemId() {
		return foodItemId;
	}

	public void setFoodItemId(int foodItemId) {
		this.foodItemId = foodItemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public FoodType getfoodType() {
		return foodType;
	}

	public void setfoodType(FoodType foodType) {
		this.foodType = foodType;
	}

	
	public FoodItem() {
		super();
	}

	public FoodItem(int foodItemId, String cuisine, String name, double price, FoodType foodType) {
		super();
		this.foodItemId = foodItemId;
		this.cuisine = cuisine;
		this.name = name;
		this.price = price;
		this.foodType = foodType;
	}

	@Override
	public String toString() {
		return "FoodItem [foodItemId=" + foodItemId + ", cuisine=" + cuisine + ", name=" + name + ", price=" + price
				+ ", foodType=" + foodType + "]";
	}

	
	
}
