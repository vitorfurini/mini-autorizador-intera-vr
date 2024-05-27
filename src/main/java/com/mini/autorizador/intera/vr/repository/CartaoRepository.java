package com.mini.autorizador.intera.vr.repository;

import com.mini.autorizador.intera.vr.entity.card.Card;
import com.mini.autorizador.intera.vr.entity.card.Card;

import java.util.Optional;

public interface CartaoRepository {

    Optional<Card> findByCardNumber(String cardNumber);
}
