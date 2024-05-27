package com.mini.autorizador.intera.vr.dto.transaction;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionDTO {
    @NotNull
    @NotEmpty
    private String cardNumber;
    private String password;
    private BigDecimal value;
}
