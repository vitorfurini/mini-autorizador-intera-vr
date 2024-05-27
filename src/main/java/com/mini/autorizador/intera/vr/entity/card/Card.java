package com.mini.autorizador.vr.entity.card;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "card")
public class Card implements Serializable {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O campo number não pode ser vazio")
    @NotNull
    @Size(min = 16, max = 16)
    private String cardNumber;

    @NotEmpty(message = "O campo password não pode ser vazio")
    @NotNull
    private String password;

    private BigDecimal balance;

}
