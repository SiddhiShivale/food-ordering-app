package com.aurionpro.order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.aurionpro.delivery.IDeliveryAgent;

public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<OrderItem> items;
	private Date timestamp;
	private IDeliveryAgent assignedAgent;

	public Order() {
	    this.items = new ArrayList<>();
	    this.timestamp = new Date();
	}

	public void addItem(OrderItem item) {
	    items.add(item);
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
}
