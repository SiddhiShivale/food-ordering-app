# 🍔 Java Console Food Ordering Application

A robust, console-based application for managing a restaurant's menu, processing customer orders, handling payments, assigning delivery agents, and maintaining customer/menu persistence.

This project is a detailed implementation of Object-Oriented Programming (OOP) principles in Java, focusing on a layered architecture and the use of interfaces for extensibility (e.g., payments, discounts, delivery).

---

## ✨ Features

* **Dual User Modes:** Separate interfaces for **Admin** and **Customer**.
* **Menu Management (Admin):** Admins can view, add, and remove food items, categorized by **Cuisine** (e.g., Indian, Italian) and **Food Type** (e.g., Starter, Main Course, Dessert).
* **Customer Ordering:** Customers can view the menu (filtered by cuisine), place orders with multiple items and quantities.
* **Order Persistence:** Menu items and customer order history are saved to files using Java **Serialization** and loaded upon startup.
* **Discount Service:** Implements a flat discount logic (e.g., ₹50 off orders over ₹500).
* **Polymorphic Payments:** Supports multiple payment methods (**Cash** and **UPI**), leveraging the `IPaymentService` interface.
* **Delivery Management:** Manages a pool of delivery agents (Swiggy, Zomato) and assigns an available agent to an order.
* **Detailed Invoicing:** Prints a formatted invoice summarizing the order, discount, total amount, payment method, and assigned delivery agent.

---

## 🛠️ Technologies Used

* **Java (JDK 8+):** Core application language.
* **Java Serialization:** Used for data persistence (saving and loading menu, customer data, and delivery agents).
* **OOP Principles:** Extensive use of **Interfaces** and **Composition**.

---

## 🧑‍💻 Usage and Workflow

### 1. Main Entry Point (`CustomerTest.java`)

The application starts with a choice between **Admin** and **Customer** mode.

### 2. Admin Console

The Admin panel allows full control over the restaurant's operational data:

* **Menu Management:** Add new dishes (specifying name, price, cuisine, and category), and remove existing dishes by ID.
* **Delivery Management:** Add new agents (as either Swiggy or Zomato) or remove agents from the pool.

### 3. Customer Workflow

1.  **Login/Signup:** Enter a name. If the name is new, a new customer profile is created and saved. If it exists, the customer's history is loaded.
2.  **View Menu:** Customers can view the entire menu or filter it by cuisine (Indian, Italian, Chinese).
3.  **Place Order:**
    * Items are added by ID and quantity.
    * The total is calculated, and a discount is applied if applicable (currently $50 off orders over $500).
    * The customer selects a payment method (Cash or UPI). UPI requires input validation.
    * An available delivery agent is assigned (first-come, first-served).
    * A final, formatted invoice is printed, and the order is added to the customer's history.
4.  **Order History:** Customers can review all their past orders, including item details and totals.

---

## 📂 Project Structure Overview

The code follows a clean, layered package structure:

| Package | Purpose | Key Classes / Interfaces |
| :--- | :--- | :--- |
| `com.aurionpro.admin` | Administrative functions and login. | `AdminConsole`, `AdminService` |
| `com.aurionpro.customer` | Customer entity and data management. | `Customer`, `CustomerDatabase` |
| `com.aurionpro.menu` | Food items and menu catalog management. | `Menu`, `FoodItem`, `IMenuType`, `IFoodType` |
| `com.aurionpro.order` | Core order and item logic. | `Order`, `OrderItem` |
| `com.aurionpro.delivery` | Delivery agent management and assignment. | `DeliveryManager`, `IDeliveryAgent`, `SwiggyAgent`, `ZomatoAgent` |
| `com.aurionpro.payment` | Payment processing interfaces and implementations. | `IPaymentService`, `CashPayment`, `UPIPayment` |
| `com.aurionpro.discount` | Discount calculation logic. | `IDiscountService`, `FlatDiscountService` |
| `com.aurionpro.invoice` | Logic for generating the final invoice. | `InvoiceService` |
| `com.aurionpro.test` | Main entry point for the application. | `CustomerTest` |

---

## 💡 Extensibility (Design Pattern Focus)

The use of **Interfaces** allows for easy extension of the application without modifying existing core logic:

* **Adding a New Cuisine:** Create a new class implementing `IMenuType` (e.g., `MexicanMenu`).
* **Adding a New Delivery Partner:** Create a new class implementing `IDeliveryAgent` (e.g., `UberEatsAgent`).
* **Adding a New Payment Method:** Create a new class implementing `IPaymentService` (e.g., `CreditCardPayment`).
* **Changing Discount Logic:** Create a new class implementing `IDiscountService` (e.g., `PercentageDiscountService`).
