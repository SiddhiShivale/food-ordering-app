package com.aurionpro.delivery;

public class ZomatoAgent implements IDeliveryAgent {

	private static final long serialVersionUID = 1L;
	private final String name;
	private boolean available;

	public ZomatoAgent(String name) {
	    this.name = name;
	    this.available = true;
	}

	@Override
	public String getName() {
	    return name;
	}

	@Override
	public boolean isAvailable() {
	    return available;
	}

	@Override
	public void assign() {
	    this.available = false;
	}

	@Override
	public void release() {
	    this.available = true;
	}
}
