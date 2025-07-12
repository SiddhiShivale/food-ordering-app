package com.aurionpro.delivery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeliveryManager implements Serializable{

	private static final long serialVersionUID = 1L;
	private final List<IDeliveryAgent> agents;
	
	public DeliveryManager() {
	    agents = new ArrayList<>();
	    agents.add(new SwiggyAgent("Swiggy-1"));
	    agents.add(new ZomatoAgent("Zomato-1"));
	}

	public IDeliveryAgent getAvailableAgent() {
	    for (IDeliveryAgent agent : agents) {
	        if (agent.isAvailable()) {
	            agent.assign();
	            return agent;
	        }
	    }
	    return null;
	}

	public void releaseAgent(IDeliveryAgent agent) {
	    agent.release();
	}

	public List<IDeliveryAgent> getAgents() {
	    return agents;
	}
}
