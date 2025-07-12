package com.aurionpro.payment;

public class CashPayment implements IPaymentService{

	@Override
	public void makePayment(double amount) {
		System.out.println("\nYou’ve selected Cash as your payment method. Please pay ₹" + amount + " to the service provider upon delivery.");
	}

	@Override
	public String getMode() {
	
		return "Cash";
	}

}
