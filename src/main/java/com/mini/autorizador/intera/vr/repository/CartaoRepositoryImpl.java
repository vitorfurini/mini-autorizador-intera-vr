package com.mini.autorizador.intera.vr.repository;

import com.mini.autorizador.intera.vr.entity.card.Card;
import com.mini.autorizador.intera.vr.entity.card.Card;
import com.mini.autorizador.intera.vr.repository.jpa.ICartaoRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CartaoRepositoryImpl implements CartaoRepository {

    private final ICartaoRepositoryJpa iCartaoRepositoryJpa;

    @Autowired
    public CartaoRepositoryImpl(ICartaoRepositoryJpa iCartaoRepositoryJpa) {
        this.iCartaoRepositoryJpa = iCartaoRepositoryJpa;
    }

    @Override
    public Optional<Card> findByCardNumber(String cardNumber) {
        return iCartaoRepositoryJpa.findByCardNumber(cardNumber);
    }
}
