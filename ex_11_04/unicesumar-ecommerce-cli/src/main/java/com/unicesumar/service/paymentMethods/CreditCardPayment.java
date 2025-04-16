package com.unicesumar.service.paymentMethods;

public class CreditCardPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Pagamento efetuado com sucesso via cartão de crédito");
    }

    @Override
    public String getDescription() {
        return "CARTÃO DE CRÉDITO";
    }
}