package com.aurionpro.invoice;

import com.aurionpro.delivery.DeliveryManager;
import com.aurionpro.delivery.IDeliveryAgent;
import com.aurionpro.order.Order;
import com.aurionpro.order.OrderItem;
import com.aurionpro.payment.IPaymentService;

public class InvoiceService {
	private static final String DELIVERY_AGENTS_FILE = "agents";
	DeliveryManager deliveryManager = DeliveryManager.loadFromFile(DELIVERY_AGENTS_FILE);

	public void printInvoice(Order order, double discount, double total, IPaymentService paymentService) {
	    System.out.println("\n========== INVOICE ==========");
        for (OrderItem item : order.getItems()) {
            System.out.printf("%-20s x%-2d = ₹%.2f\n",
                    item.getItem().getName(),
                    item.getQuantity(),
                    item.getTotalPrice());
        }

	    System.out.println("\n-------------------------------------------");
	    System.out.printf("Subtotal        : ₹%.2f\n", order.calculateTotal());
	    System.out.printf("Discount        : ₹%.2f\n", discount);
	    System.out.printf("Total Amount    : ₹%.2f\n", total);
	    System.out.println("Payment Method  : " + paymentService.getClass().getSimpleName().replace("Payment", ""));

	    String agentInfo = (order.getAssignedAgent() != null)
	        ? order.getAssignedAgent().getName()
	        : "Not assigned";
	    System.out.println("Delivery Agent  : " + agentInfo);
        System.out.println("==============================");
        System.out.println("Thank you for your order!");

	}
}
