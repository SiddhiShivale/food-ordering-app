package com.aurionpro.menu;

import java.io.Serializable;

public class FoodItem implements Serializable {

	private static int idCounter = 1;
	private int id;
	private String name;
	private double price;
	private IFoodType foodType;
	private IMenuType menuType;

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public IFoodType getFoodType() {
		return foodType;
	}

	public void setFoodType(IFoodType foodType) {
		this.foodType = foodType;
	}

	public IMenuType getMenuType() {
		return menuType;
	}

	public void setMenuType(IMenuType menuType) {
		this.menuType = menuType;
	}

	public FoodItem(String name, double price, IFoodType foodType, IMenuType menuType) {
		super();
		this.id = idCounter++;
		this.name = name;
		this.price = price;
		this.foodType = foodType;
		this.menuType = menuType;
	}

	public FoodItem() {
		super();
	}
	
	public static void setStartingId(int startFrom) {
        idCounter = startFrom;
    }

	@Override
	public String toString() {
		return "FoodItem [id=" + id + ", name=" + name + ", price=" + price + ", foodType=" + foodType + ", menuType="
				+ menuType + "]";
	}

}
