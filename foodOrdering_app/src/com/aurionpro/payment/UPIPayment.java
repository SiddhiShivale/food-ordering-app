package com.aurionpro.payment;

import java.util.HashSet;
import java.util.Set;

public class UPIPayment implements IPaymentService {

	private String upiId;

	public String getUpiId() {
		return upiId;
	}

	public void setUpiId(String upiId) {
		this.upiId = upiId;
	}

	public UPIPayment(String upiId) {
		super();
		this.upiId = upiId;
	}

	public UPIPayment() {
		super();
	}

	@Override
	public void makePayment(double amount) {
		System.out.println("\nPayment of ₹" + amount + " done through UPI successfully.");
	}

	@Override
	public String getMode() {
		return "UPI";
	}

}
