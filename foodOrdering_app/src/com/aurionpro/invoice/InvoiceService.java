package com.aurionpro.invoice;

import com.aurionpro.delivery.IDeliveryAgent;
import com.aurionpro.order.Order;
import com.aurionpro.order.OrderItem;
import com.aurionpro.payment.IPaymentService;

public class InvoiceService {

    public void printInvoice(Order order, double discount, double totalAfterDiscount, IPaymentService paymentService) {
        System.out.println("\n========== INVOICE ==========");
        for (OrderItem item : order.getItems()) {
            System.out.printf("%-20s x%-2d = ₹%.2f\n",
                    item.getItem().getName(),
                    item.getQuantity(),
                    item.getTotalPrice());
        }

        double subtotal = order.calculateTotal();
        System.out.println("------------------------------");
        System.out.printf("Subtotal        : ₹%.2f\n", subtotal);
        System.out.printf("Discount Applied: ₹%.2f\n", discount);
        System.out.printf("Total Payable   : ₹%.2f\n", totalAfterDiscount);
        System.out.println("Payment Mode    : " + paymentService.getMode());

        IDeliveryAgent agent = order.getAssignedAgent();
        String agentInfo = (agent != null) ? agent.getName() : "Finding delivery agents for you.";
        System.out.println("Delivery Agent  : " + agentInfo);
        System.out.println("==============================");
        System.out.println("Thank you for your order!");
    }
}
