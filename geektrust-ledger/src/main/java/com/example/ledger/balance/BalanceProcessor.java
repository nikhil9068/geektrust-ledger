package com.example.ledger.balance;

import com.example.ledger.entities.Payment;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@AllArgsConstructor
public class BalanceProcessor {
	String bankName;
    String borrowerName;
    int completedEmiNumber;
    
    public void displayBalance(List<Payment> paymentList, int completedEmiNumber, String bankName, String borrowerName) {
        Payment payment = paymentList.get(completedEmiNumber);
        
        System.out.println(bankName + " " + borrowerName + " " + (int)(payment.getAmountPaid()) + " " + payment.getEmiMonthsLeft());
    }
    
    private Payment calculatePayment(Payment lastMonthPayment, int amountPerEmi, double totalAmount) {
        double totalAmountPaid = lastMonthPayment.getAmountPaid() + amountPerEmi;
        
        if(totalAmountPaid > totalAmount) {
            totalAmountPaid = totalAmount;
        }
        
        Payment payment = new Payment(totalAmountPaid, lastMonthPayment.getEmiMonthsLeft() - 1);
        
        return payment;
    }
    
    public List<Payment> updatePayments(List<Payment> paymentList, int completedEmiNumber, int amountPerEmi, double totalAmount) {
        for(int i = paymentList.size(); i <= completedEmiNumber; i++) {
            Payment lastMonthPayment = paymentList.get(i-1);
            
            paymentList.add(calculatePayment(lastMonthPayment, amountPerEmi, totalAmount));
        }
        
        System.out.println("[INFO]: Updated Payment list in Balance " + paymentList);
        
        return paymentList;
    }
}