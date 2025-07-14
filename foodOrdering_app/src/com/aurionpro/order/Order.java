package com.aurionpro.order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.aurionpro.delivery.IDeliveryAgent;

public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	public static int idCounter = 1;
	private int orderId;
	private List<OrderItem> items;
	private Date timestamp;
	private IDeliveryAgent assignedAgent;

	public Order() {
		this.orderId = idCounter++;
	    this.items = new ArrayList<>();
	    this.timestamp = new Date();
	}

	public int getOrderId() {
		return orderId;
	}

	public void addItem(OrderItem newItem) {
	    for (OrderItem existingItem : items) {
	        if (existingItem.getItem().getId() == newItem.getItem().getId()) {
	            existingItem.setQuantity(existingItem.getQuantity() + newItem.getQuantity());
	            return;
	        }
	    }
	    items.add(newItem);
	}


	public double calculateTotal() {
	    return items.stream().mapToDouble(OrderItem::getTotalPrice).sum();
	}

	public List<OrderItem> getItems() {
	    return items;
	}

	public Date getTimestamp() {
	    return timestamp;
	}

	public void assignAgent(IDeliveryAgent agent) {
	    this.assignedAgent = agent;
	}

	public IDeliveryAgent getAssignedAgent() {
	    return assignedAgent;
	}

	public static void setStartingOrderId(int nextId) {
		idCounter = nextId;
	}
}
