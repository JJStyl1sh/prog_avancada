package com.unicesumar.service.paymentManagers;

import com.unicesumar.service.paymentMethods.*;

public class PaymentMethodFactory {
    public static PaymentMethod create(PaymentType type) {
        switch (type) {
            case PIX:
                return new PixPayment();
            case CARTAO:
                return new CreditCardPayment();
            case BOLETO:
                return new BoletoPayment();
            default:
                return new PixPayment();
        }
    }
}