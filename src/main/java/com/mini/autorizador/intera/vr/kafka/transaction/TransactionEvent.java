package com.mini.autorizador.vr.kafka.transaction;


import com.mini.autorizador.vr.dto.transaction.TransactionDTO;

import java.math.BigDecimal;

public record TransactionEvent(String cardNumber, String password, BigDecimal value) {

    public static TransactionEvent create(TransactionDTO transaction) {

        return new TransactionEvent(transaction.getCardNumber(),
                transaction.getPassword(),
                transaction.getValue());
    }
}
