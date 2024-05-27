package com.mini.autorizador.vr.repository;

import com.mini.autorizador.vr.entity.card.Card;

import java.util.Optional;

public interface CartaoRepository {

    Optional<Card> findByCardNumber(String cardNumber);
}
