package com.aurionpro.test;

import java.util.Scanner;

import com.aurionpro.admin.AdminConsole;
import com.aurionpro.customer.Customer;
import com.aurionpro.customer.CustomerDatabase;
import com.aurionpro.delivery.DeliveryManager;
import com.aurionpro.delivery.IDeliveryAgent;
import com.aurionpro.discount.FlatDiscountService;
import com.aurionpro.discount.IDiscountService;
import com.aurionpro.invoice.InvoiceService;
import com.aurionpro.menu.FoodItem;
import com.aurionpro.menu.Menu;
import com.aurionpro.order.Order;
import com.aurionpro.order.OrderItem;
import com.aurionpro.payment.CashPayment;
import com.aurionpro.payment.IPaymentService;
import com.aurionpro.payment.UPIPayment;

public class CustomerTest {

	private static final String MENU_FILE = "menu";
	private static final String CUSTOMER_FILE = "customers";
	private static final String DELIVERY_AGENTS_FILE = "agents";

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Load data
		Menu menu = Menu.loadFromFile(MENU_FILE);
		CustomerDatabase customerDB = CustomerDatabase.loadFromFile(CUSTOMER_FILE);
		int maxOrderId = 0;

		for (Customer c : customerDB.getAllCustomers()) {
		    for (Order order : c.getOrderHistory()) {
		        if (order.getOrderId() > maxOrderId) {
		            maxOrderId = order.getOrderId();
		        }
		    }
		}
		Order.setStartingOrderId(maxOrderId + 1);
		DeliveryManager deliveryManager = DeliveryManager.loadFromFile(DELIVERY_AGENTS_FILE);
		IDiscountService discountService = new FlatDiscountService(500, 50);
		InvoiceService invoiceService = new InvoiceService();
		int entry;

		do {

			System.out.println("-----Welcome to Food Ordering App-----");
			System.out.println("\n1. Admin");
			System.out.println("2. Customer");
			System.out.println("3. Exit");
			System.out.print("\nEnter choice: ");
			entry = Integer.parseInt(scanner.nextLine());

			if (entry == 1) {
				new AdminConsole(menu, deliveryManager, scanner).launch();
				menu.saveToFile(MENU_FILE);
				customerDB.saveToFile(CUSTOMER_FILE);
				deliveryManager.saveToFile(DELIVERY_AGENTS_FILE);
			}

			if (entry == 2) {

				System.out.print("\nEnter your name: ");
				String name = scanner.nextLine();

				Customer customer = customerDB.getCustomer(name);
				if (customer == null) {
					customer = new Customer(name);
					customerDB.addCustomer(customer);
					System.out.println("Welcome, " + name + "! (new profile created)");
				} else {
					System.out.println("Welcome back, " + name + "!");
				}

				int choice;

				do {
					System.out.println("\n--- Customer Menu ---");
					System.out.println("1. View Full Menu");
					System.out.println("2. Place Order");
					System.out.println("3. View Order History");
					System.out.println("4. Exit");
					System.out.print("\nEnter choice: ");
					choice = scanner.nextInt();
					scanner.nextLine();

					switch (choice) {
					case 1:

						System.out.println("\nSelect Cuisine:");
						System.out.println("1. Indian");
						System.out.println("2. Italian");
						System.out.println("3. Chinese");
						System.out.print("\nEnter choice: ");
						int cuisineChoice = scanner.nextInt();
						scanner.nextLine();

						switch (cuisineChoice) {
						case 1:
							menu.displayByCuisine("Indian");
							break;
						case 2:
							menu.displayByCuisine("Italian");
							break;
						case 3:
							menu.displayByCuisine("Chinese");
							break;
						default:
							System.out.println("\nInvalid choice. Showing full menu.\n");
							menu.displayAll();
							break;
						}

						break;

					case 2: {
						Order order = new Order();
						while (true) {
							menu.displayAll();
							System.out.print("\nEnter item ID to order (0 to finish): ");
							int itemId = scanner.nextInt();
							if (itemId == 0)
								break;
							FoodItem item = menu.getItemById(itemId);
							if (item == null) {
								System.out.println("Invalid ID!");
								continue;
							}
							System.out.print("Enter quantity: ");
							int quantity = scanner.nextInt();
							order.addItem(new OrderItem(item, quantity));
						}

						if (order.getItems().isEmpty()) {
							System.out.println("Order is empty. Cancelled.");
							break;
						}

						double subtotal = order.calculateTotal();
						double discount = discountService.applyDiscount(subtotal);
						double total = subtotal - discount;

						System.out.println("\nChoose payment mode:");
						System.out.println("1. Cash");
						System.out.println("2. UPI");
						System.out.print("\nEnter option: ");
						int payOption = scanner.nextInt();
						scanner.nextLine();

						IPaymentService paymentService = null;

						switch (payOption) {
						case 1:
							paymentService = new CashPayment();
							break;
						case 2:

							paymentService = new UPIPayment();
							break;

						default:
							System.out.println("Invalid payment. Defaulting to Cash.");
							paymentService = new CashPayment();
						}

						paymentService.makePayment(total);

						IDeliveryAgent agent = deliveryManager.getAvailableAgent();
						if (agent != null) {
							order.assignAgent(agent);
							System.out.println("\nDelivery Partner Assigned: " + agent.getName());
						} else {
							System.out.println("\nNo delivery agents available. Order will be delayed.");
						}

						customer.addOrder(order);
						invoiceService.printInvoice(order, discount, total, paymentService);

						customerDB.saveToFile(CUSTOMER_FILE);
						break;
					}

					case 3: 
						if (customer.getOrderHistory().isEmpty()) {
						    System.out.println("No previous orders.");
						} else {
						    System.out.println("\n--- Order History ---\n");
						    for (Order order : customer.getOrderHistory()) {
						    	System.out.println("Order ID: " + order.getOrderId());
						        System.out.println("Order on: " + order.getTimestamp());
						        System.out.printf("%-20s %-10s %-10s\n", "Item", "Quantity", "Price (₹)");
						        System.out.println("----------------------------------------------");

						        for (OrderItem item : order.getItems()) {
						            System.out.printf("%-20s %-10d ₹%-9.2f\n",
						                item.getItem().getName(),
						                item.getQuantity(),
						                item.getTotalPrice());
						        }

						        System.out.println("----------------------------------------------");
						        System.out.printf("%-20s %-10s ₹%-9.2f\n\n", "Total", "", order.calculateTotal());
						    }
						}
						break;

					}
				} while (choice != 4);
			}

		} while (entry != 3);

		menu.saveToFile(MENU_FILE);
		customerDB.saveToFile(CUSTOMER_FILE);
		deliveryManager.saveToFile(DELIVERY_AGENTS_FILE);

	}
}
