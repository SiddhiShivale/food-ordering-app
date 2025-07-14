package com.aurionpro.customer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerDatabase implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<String, Customer> customers;

    public CustomerDatabase() {
        this.customers = new HashMap<>();
    }

    public void addCustomer(Customer customer) {
        customers.putIfAbsent(customer.getName(), customer);
    }

    public Customer getCustomer(String name) {
        return customers.get(name);
    }
    
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }  

    public boolean exists(String name) {
        return customers.containsKey(name);
    }

    public static CustomerDatabase loadFromFile(String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            return (CustomerDatabase) in.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            System.out.println("No previous customer data found. Starting fresh.");
            return new CustomerDatabase();
        }
    }

    public void saveToFile(String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(this);
        } catch (IOException exception) {
            System.out.println("Error saving customer data: " + exception.getMessage());
        }
    }

	@Override
	public String toString() {
		return "CustomerDatabase [customers=" + customers + "]";
	}
    
    
}
