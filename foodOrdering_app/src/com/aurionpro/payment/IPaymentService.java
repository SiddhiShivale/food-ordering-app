package com.aurionpro.payment;

public interface IPaymentService {
	void makePayment(double amount);
	String getMode();
}
