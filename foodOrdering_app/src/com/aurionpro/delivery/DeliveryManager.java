package com.aurionpro.delivery;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryManager implements Serializable {

    private static final long serialVersionUID = 1L;
    private final List<IDeliveryAgent> agents;

    public DeliveryManager() {
        agents = new ArrayList<>();
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

    // Save to file
    public void saveToFile(String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(this);
        } catch (IOException e) {
            System.out.println("\nFailed to save delivery agents: " + e.getMessage());
        }
    }

    // Load from file
    public static DeliveryManager loadFromFile(String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            return (DeliveryManager) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("\nNo previous delivery data found. Using default agents.");
            return new DeliveryManager();
        }
    }

	@Override
	public String toString() {
		return "DeliveryManager [agents=" + agents + "]";
	}
}
