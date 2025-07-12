package com.aurionpro.order;

import java.io.Serializable;

import com.aurionpro.menu.FoodItem;

public class OrderItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private FoodItem item;
	private int quantity;

	public OrderItem(FoodItem item, int quantity) {
	    this.item = item;
	    this.quantity = quantity;
	}

	public double getTotalPrice() {
	    return item.getPrice() * quantity;
	}

	public FoodItem getItem() {
	    return item;
	}

	public int getQuantity() {
	    return quantity;
	}
}

