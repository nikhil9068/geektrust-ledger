package com.example.ledger.payment;

import com.example.ledger.entities.Payment;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@ToString
public class PaymentProcessor {
	String bankName;
    String borrowerName;
    double lumpSumAmount;
    int completedEmiNumber;
    
    public List<Payment> updatePayments(List<Payment> paymentList, double lumpSumAmount, int completedEmiNumber, int amountPerEmi, double totalAmount) {
        if(completedEmiNumber == 0) {
        	System.out.println("[INFO]: Processing First payment");
        	paymentList = processFirstPayment(paymentList, totalAmount, lumpSumAmount, amountPerEmi);
        } else {
            for (int i = paymentList.size(); i < completedEmiNumber; i++) {
                Payment lastMonthPayment = paymentList.get(i - 1);
                paymentList.add(processPayment(lastMonthPayment, amountPerEmi, totalAmount));
            }
            
            if (paymentList.size() == completedEmiNumber) {
                Payment lastMonthPayment = paymentList.get(paymentList.size() - 1);
                
                System.out.println("[INFO]: Processing Final payment");
                paymentList.add(processFinalPayment(lastMonthPayment, amountPerEmi, lumpSumAmount, totalAmount));
            }
        }
        
        System.out.println("[INFO]: Updated payment list " + paymentList);
        
        return paymentList;
    }
    
    private List<Payment> processFirstPayment(List<Payment> paymentList, double totalAmount, double lumpSumAmount, int amountPerEmi) {
        double remainingAmount = totalAmount - lumpSumAmount;
        int remainingEmiCount = (int) Math.ceil(remainingAmount / amountPerEmi);
        
        Payment payment = new Payment(lumpSumAmount, remainingEmiCount);
        
        paymentList.remove(paymentList.get(0));
        paymentList.add(payment);
        
        return paymentList;
    }
    
    private Payment processFinalPayment(Payment lastMonthPayment, int amountPerEmi, double lumpSumAmount, double totalAmount) {
        double amountPaid = lastMonthPayment.getAmountPaid() + amountPerEmi + lumpSumAmount;
        
        if(amountPaid > totalAmount) {
        	amountPaid = totalAmount;
        }
        
        double remainingAmount = totalAmount - amountPaid;
        int remainingEmiCount = (int) Math.ceil(remainingAmount / amountPerEmi);
        
        Payment payment = new Payment(amountPaid, remainingEmiCount);
        
        return payment;
    }
    
    public Payment processPayment(Payment lastMonthPayment, int amountPerEmi, double totalAmount) {
        double amountPaid = lastMonthPayment.getAmountPaid() + amountPerEmi;
        
        if(amountPaid > totalAmount) {
        	amountPaid = totalAmount;
        }
        
        Payment payment = new Payment(amountPaid, lastMonthPayment.getEmiMonthsLeft() - 1);
        
        return payment;
    }
}