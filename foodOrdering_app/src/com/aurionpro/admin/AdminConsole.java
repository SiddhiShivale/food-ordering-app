package com.aurionpro.admin;

import java.util.List;
import java.util.Scanner;

import com.aurionpro.delivery.DeliveryManager;
import com.aurionpro.delivery.IDeliveryAgent;
import com.aurionpro.delivery.SwiggyAgent;
import com.aurionpro.delivery.ZomatoAgent;
import com.aurionpro.menu.FoodItem;
import com.aurionpro.menu.IFoodType;
import com.aurionpro.menu.IMenuType;
import com.aurionpro.menu.Menu;

public class AdminConsole {
    private final Menu menu;
    private final DeliveryManager deliveryManager;
    private final Scanner sc;

    public AdminConsole(Menu menu, DeliveryManager deliveryManager, Scanner sc) {
        this.menu = menu;
        this.deliveryManager = deliveryManager;
        this.sc = sc;
    }

    public void launch() {
        AdminService adminService = new AdminService();

        System.out.print("Enter admin username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if (!adminService.login(username, password)) {
            System.out.println("\nLogin failed!");
            return;
        }

        System.out.println("\nWelcome Admin!");

        boolean back = false;
        while (!back) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add Food Item");
            System.out.println("2. Remove Food Item");
            System.out.println("3. Add Delivery Partner");
            System.out.println("4. Remove Delivery Partner");
            System.out.println("5. View All Menu Items");
            System.out.println("6. Exit Admin Panel");
            System.out.print("\nEnter choice: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                	addFoodItem();
                	break;
                case 2:
                	removeFoodItem();
                	break;
                case 3:
                	addDeliveryPartner();
                	break;
                case 4:
                	removeDeliveryPartner();
                	break;
                case 5:
                	menu.displayAll();
                	break;
                case 6:
                	back = true;
                	break;
              
            }
        }
    }

    private void addFoodItem() {
        try {

            System.out.print("Enter food name: ");
            String name = sc.nextLine();

            System.out.print("Enter price: ");
            double price = Double.parseDouble(sc.nextLine());

            System.out.print("Enter menu type (e.g., Indian): ");
            String menuTypeName = sc.nextLine();

            System.out.print("Enter food category (e.g., MainCourse): ");
            String foodTypeName = sc.nextLine();

            IMenuType menuType = () -> menuTypeName;
            IFoodType foodType = () -> foodTypeName;

            if (menu.containsItem(name, menuType, foodType)) {
                System.out.println("\nA food item with this name and type already exists in this category.");
                return;
            }

            menu.addItem(new FoodItem(name, price, foodType, menuType));
            System.out.println("\nItem added successfully.");
        } catch (Exception e) {
            System.out.println("\nFailed to add item: " + e.getMessage());
        }
    }


    private void removeFoodItem() {
        menu.displayAll();
        System.out.print("\nEnter ID of item to remove: ");
        int id = Integer.parseInt(sc.nextLine());
        boolean removed = menu.removeItemById(id);
        if (removed) {
            System.out.println("\nItem removed.");
        } else {
            System.out.println("\nItem not found.");
        }
    }

    private void addDeliveryPartner() {
        System.out.print("\nEnter delivery partner name: ");
        String name = sc.nextLine();
        System.out.print("Type (1. Swiggy  2. Zomato): ");
        int type = Integer.parseInt(sc.nextLine());

        IDeliveryAgent agent;
        if (type == 1) {
            agent = new SwiggyAgent(name);
        } else if (type == 2) {
            agent = new ZomatoAgent(name);
        } else {
            System.out.println("\nInvalid type");
            return;
        }

        deliveryManager.getAgents().add(agent);
        List<IDeliveryAgent> deliveryAgents = deliveryManager.getAgents();
        for(IDeliveryAgent deliveryAgent : deliveryAgents) {
        	System.out.println(deliveryAgent.getName());
        }
        System.out.println("\nDelivery partner added.");
    }

    private void removeDeliveryPartner() {
        var agents = deliveryManager.getAgents();
        for (int i = 0; i < agents.size(); i++) {
            System.out.println((i + 1) + ". " + agents.get(i).getName());
        }
        System.out.print("Enter number to remove: ");
        int index = Integer.parseInt(sc.nextLine());
        if (index >= 1 && index <= agents.size()) {
            agents.remove(index - 1);
            System.out.println("\nPartner removed.");
        } else {
            System.out.println("\nInvalid index");
        }
    }
}
