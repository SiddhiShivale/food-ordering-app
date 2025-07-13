package com.aurionpro.payment;

import java.util.HashSet;
import java.util.Scanner;
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
		Scanner scanner = new Scanner(System.in);
		boolean isValid = false;

		do {

			System.out.print("\nEnter UPI id: ");
			String upiId = scanner.nextLine();

			Set<String> validBankHandles = new HashSet<>();

			validBankHandles.add("oksbi");
			validBankHandles.add("okhdfcbank");
			validBankHandles.add("okaxis");
			validBankHandles.add("okicici");
			validBankHandles.add("okpaytm");
			validBankHandles.add("okyesbank");

			if (!upiId.contains("@")) {
				System.out.println("\nInvalid UPI ID: Missing '@' symbol.");
			} else {
				String[] parts = upiId.split("@");
				if (parts.length == 2 && !parts[0].isEmpty() && !parts[1].isEmpty()) {
					if (validBankHandles.contains(parts[1]))
						isValid = true;
					else {
						System.out.println("\nInvalid bank Id.");
					}
				} else
					System.out.println("\nInvalid UPI Id.");
			}

		} while (!isValid);

		System.out.println("\nUPI ID verified.");
		System.out.println("Payment of ₹" + amount + " successful using UPI.");
	}

	@Override
	public String getMode() {
		return "UPI";
	}

}
