package com.mini.autorizador.intera.vr.service.card;

import com.mini.autorizador.intera.vr.dto.card.CardDTO;
import com.mini.autorizador.intera.vr.entity.card.Card;
import com.mini.autorizador.intera.vr.dto.card.CardDTO;
import com.mini.autorizador.intera.vr.entity.card.Card;
import exception.ApiException;

import java.math.BigDecimal;
import java.util.Optional;

public interface CartaoService {

    Optional<Card> findByCardNumber(String cardNumber);

    Object create(CardDTO cartaoRequest) throws ApiException;

    BigDecimal getBalanceByCardNumber(String cardNumber) throws ApiException;

    void updateBalance(BigDecimal balance, String cardNumber) throws ApiException;
}
