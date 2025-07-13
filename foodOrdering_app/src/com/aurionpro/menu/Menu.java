package com.aurionpro.menu;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
		System.out.printf("\n%-5s %-20s %-10s %-15s %-15s\n", "ID", "Name", "Price", "Food Type", "Cuisine");
		System.out.println("----------------------------------------------------------------");
		for (FoodItem item : foodItems) {
			System.out.printf(
	                "%-5d %-20s ₹%-9.2f %-15s %-15s\n",
	                item.getId(),
	                item.getName(),
	                item.getPrice(),
	                item.getFoodType().getCategoryName(),
	                item.getMenuType().getTypeName()
	            );

		}
	}
	
	public void displayByCuisine(String selectedCuisine) {
		System.out.printf("\n%-5s %-20s %-10s %-15s %-15s\n", "ID", "Name", "Price", "Food Type", "Cuisine");
		System.out.println("----------------------------------------------------------------");
		for(FoodItem item : foodItems) {
			String itemCuisine = item.getMenuType().getTypeName();
			if(itemCuisine != null && itemCuisine.equalsIgnoreCase(selectedCuisine)) {
				System.out.printf(
		                "%-5d %-20s ₹%-9.2f %-15s %-15s\n",
		                item.getId(),
		                item.getName(),
		                item.getPrice(),
		                item.getFoodType().getCategoryName(),
		                item.getMenuType().getTypeName()
		            );

			}
		}
	}
	
	public boolean containsItem(String name, IMenuType menuType, IFoodType foodType) {
	    for (FoodItem item : foodItems) {
	        if (item.getName().equalsIgnoreCase(name)
	                && item.getMenuType().getTypeName().equalsIgnoreCase(menuType.getTypeName())
	                && item.getFoodType().getCategoryName().equalsIgnoreCase(foodType.getCategoryName())) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public boolean removeItemById(int id) {
	    for (int i = 0; i < foodItems.size(); i++) {
	        if (foodItems.get(i).getId() == id) {
	            foodItems.remove(i);
	            return true;
	        }
	    }
	    return false;
	}

	public FoodItem getItemById(int id) {
		for (FoodItem item : foodItems) {
			if (item.getId() == id)
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
			 Menu loadedMenu = (Menu) ois.readObject();
			int maxId = 0;
			for (FoodItem item : loadedMenu.foodItems) {
			    if (item.getId() > maxId) {
			        maxId = item.getId();
			    }
			}
			FoodItem.setStartingId(maxId + 1);
			return loadedMenu;

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
