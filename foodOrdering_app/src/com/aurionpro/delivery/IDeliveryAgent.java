package com.aurionpro.delivery;

import java.io.Serializable;

public interface IDeliveryAgent extends Serializable{

	String getName();
	boolean isAvailable();
	void assign();
	void release();
}
