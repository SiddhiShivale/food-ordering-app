package com.aurionpro.discount;

public class FlatDiscountService implements IDiscountService{

	private final double threshold;
	private final double discountAmount;
	
	public FlatDiscountService(double threshold, double discountAmount) {
	    this.threshold = threshold;
	    this.discountAmount = discountAmount;
	}

	@Override
	public double applyDiscount(double totalAmount) {
	    if (totalAmount > threshold) {
	        return discountAmount;
	    }
	    return 0;
	}
}
