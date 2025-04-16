package com.unicesumar.service.paymentMethods;

public interface PaymentMethod {
    public void pay(double amount);
    public String getDescription();
}