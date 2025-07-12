package com.aurionpro.admin;

import java.util.Scanner;

import com.aurionpro.delivery.DeliveryManager;
import com.aurionpro.delivery.SwiggyAgent;
import com.aurionpro.delivery.ZomatoAgent;
import com.aurionpro.menu.FoodItem;
import com.aurionpro.menu.FoodType;
import com.aurionpro.menu.Menu;
import com.aurionpro.menu.MenuType;

public class AdminConsole {

    public static void start(Scanner scanner, Menu menu, DeliveryManager deliveryManager) {
    	
        int choice;
        
        do {
                System.out.println("1. Add new cuisine");
                System.out.println("2. Add food item");
                System.out.println("3. Remove cuisine (and food items)");
                System.out.println("4. Remove food item by ID");
                System.out.println("5. Add delivery partner");
                System.out.println("6. Remove delivery partner by name");
                System.out.println("7. Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();
                sc.nextLine(); 

                switch (choice) {
                    case 1: 
                    	addCuisine(scanner);
                    	break;
                    case 2:
                    	addFoodItem(scanner, menu);
                    	break;
                    case 3:
                    	removeCuisine(scanner, menu);
                    	break;
                    case 4:
                    	removeFoodItem(scanner, menu);
                    	break;
                    case 5:
                    	addDeliveryPartner(scanner, deliveryManager);
                    	break;
                    case 6: 
                    	removeDeliveryPartner(scanner, deliveryManager);
                    	break;
                }
            
        } while(choice != 7);
    }

    private static void addCuisine(Scanner scanner) {
        System.out.print("Enter new cuisine name: ");
        String input = scanner.nextLine().trim().toUpperCase();
        try {
            MenuType newType = MenuType.valueOf(input);
            System.out.println("Cuisine already exists: " + newType);
        } catch (IllegalArgumentException e) {
            MenuType newCuisine = Enum.valueOf(MenuType.class, input);
            System.out.println("New cuisine added: " + newCuisine);
            // Note: Java enums are static, you can't truly "add" at runtime
            System.out.println("But enums cannot be modified at runtime — add it to code for persistence.");
        }
    }

    private static void addFoodItem(Scanner scanner, Menu menu) {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter item name: ");
        String name = scanner.nextLine();

        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Choose Food Type:");
        for (FoodType type : FoodType.values()) {
            System.out.println("- " + type);
        }
        System.out.print("Enter food type: ");
        FoodType foodType = FoodType.valueOf(scanner.nextLine().toUpperCase());

        FoodItem item = new FoodItem(id, name, price, foodType);
        menu.addItem(item);
        System.out.println("✅ Food item added.");
    }

    private static void removeCuisine(Scanner scanner, Menu menu) {
        System.out.print("Enter cuisine keyword (e.g., INDIAN, ITALIAN): ");
        String cuisineKey = scanner.nextLine().toUpperCase();

        int before = menu.getAllItems().size();
        menu.getAllItems().removeIf(item -> item.getName().toUpperCase().contains(cuisineKey));
        int after = menu.getAllItems().size();
        System.out.println("✅ Removed " + (before - after) + " items from cuisine: " + cuisineKey);
    }

    private static void removeFoodItem(Scanner scanner, Menu menu) {
        System.out.print("Enter Food Item ID to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean removed = menu.getAllItems().removeIf(item -> item.getId() == id);
        if (removed) {
            System.out.println("✅ Item removed.");
        } else {
            System.out.println("❌ Item not found.");
        }
    }

    private static void addDeliveryPartner(Scanner scanner, DeliveryManager manager) {
        System.out.print("Enter partner name: ");
        String name = scanner.nextLine();

        System.out.print("Choose type (1. Swiggy  2. Zomato): ");
        int type = scanner.nextInt();
        scanner.nextLine();

        switch (type) {
            case 1 -> manager.getAgents().add(new SwiggyAgent(name));
            case 2 -> manager.getAgents().add(new ZomatoAgent(name));
            default -> System.out.println("Invalid type.");
        }
        System.out.println("✅ Delivery partner added.");
    }

    private static void removeDeliveryPartner(Scanner scanner, DeliveryManager manager) {
        System.out.print("Enter partner name to remove: ");
        String name = scanner.nextLine();

        boolean removed = manager.getAgents().removeIf(agent -> agent.getName().equalsIgnoreCase(name));
        if (removed) {
            System.out.println("✅ Partner removed.");
        } else {
            System.out.println("❌ Partner not found.");
        }
    }
}
