package com.aurionpro.menu;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Menu implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<FoodItem> foodItems;
	private MenuType menuType;

	public List<FoodItem> getFoodItems() {
		return foodItems;
	}

	public MenuType getMenuType() {
		return menuType;
	}

	public void addFoodItem(FoodItem item) {
		foodItems.add(item);
	}

	public Menu() {
		super();
		this.foodItems = new ArrayList<>();
	}

	public void addItem(FoodItem item) {
		foodItems.add(item);
	}

	public void displayAll() {
		System.out.printf("\n%-5s %-20s %-10s %-15s\n", "ID", "Name", "Price", "Food Type");
		System.out.println("--------------------------------------------------------");
		for (FoodItem item : foodItems) {
			System.out.printf(
	                "%-5d %-20s ₹%-9.2f %-15s\n",
	                item.getFoodItemId(),
	                item.getName(),
	                item.getPrice(),
	                item.getfoodType()
	            );

		}
	}
	
	public void displayByCuisine(String selectedCuisine) {
		System.out.printf("\n%-5s %-20s %-10s %-15s\n", "ID", "Name", "Price", "Food Type");
		System.out.println("--------------------------------------------------------");
		for(FoodItem item : foodItems) {
			String itemCuisine = item.getCuisine();
			if(itemCuisine != null && itemCuisine.equalsIgnoreCase(selectedCuisine)) {
				System.out.printf(
		                "%-5d %-20s ₹%-9.2f %-15s\n",
		                item.getFoodItemId(),
		                item.getName(),
		                item.getPrice(),
		                item.getfoodType()
		            );

			}
		}
	}

	public FoodItem getItemById(int id) {
		for (FoodItem item : foodItems) {
			if (item.getFoodItemId() == id)
				return item;
		}
		return null;
	}

	public void saveToFile(String filename) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
			oos.writeObject(this);
		} catch (IOException e) {
			System.out.println("Error saving menu: " + e.getMessage());
		}
	}

	public static Menu loadFromFile(String filename) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
			return (Menu) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("No saved menu found, starting fresh.");
			return new Menu();
		}

	}

	@Override
	public String toString() {
		return "Menu [foodItems=" + foodItems + ", menuType=" + menuType + "]";
	}

	
}
