package com.mini.autorizador.vr.dto.card;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CardDTO {
    @NotNull
    @NotEmpty
    private String cardNumber;

    @NotNull
    @NotEmpty
    private String password;
}
