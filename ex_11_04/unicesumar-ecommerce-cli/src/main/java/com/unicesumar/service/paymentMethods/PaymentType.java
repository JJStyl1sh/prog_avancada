package com.unicesumar.service.paymentMethods;

public enum PaymentType {
    PIX("PIX"),
    BOLETO("BOLETO"),
    CARTAO("CARTÃO DE CRÉDITO");

    private  final String description;

    PaymentType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}