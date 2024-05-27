package com.mini.autorizador.intera.vr.service.card.impl;

import com.mini.autorizador.intera.vr.application.i18n.Messages;
import com.mini.autorizador.intera.vr.dto.card.CardDTO;
import com.mini.autorizador.intera.vr.entity.card.Card;
import com.mini.autorizador.intera.vr.repository.jpa.ICartaoRepositoryJpa;
import com.mini.autorizador.intera.vr.service.card.CartaoService;
import com.mini.autorizador.intera.vr.application.i18n.Messages;
import com.mini.autorizador.intera.vr.dto.card.CardDTO;
import com.mini.autorizador.intera.vr.entity.card.Card;
import com.mini.autorizador.intera.vr.repository.jpa.ICartaoRepositoryJpa;
import com.mini.autorizador.intera.vr.service.card.CartaoService;
import exception.ApiException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.math.BigDecimal;
import java.util.Optional;

@Service
@EnableConfigurationProperties
public class CartaoServiceImpl implements CartaoService {

    @Value("${card.balance.default}")
    private BigDecimal defaultBalance;

    @Autowired
    ICartaoRepositoryJpa cartaoRepository;

    private final ModelMapper mapper;

    public CartaoServiceImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Optional<Card> findByCardNumber(String cardNumber) {
        return cartaoRepository.findByCardNumber(cardNumber);
    }

    @Override
    public Object create(CardDTO cartaoRequest) throws ApiException {
        var exists = cartaoRepository.existsByCardNumber(cartaoRequest.getCardNumber());

        if (exists) {
            throw new ApiException(Messages.CARD_ALREADY_EXISTS, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        var cardSave = mapper.map(cartaoRequest, Card.class);
        cardSave.setBalance(defaultBalance);
        return cartaoRepository.save(cardSave);
    }

    @Override
    @Transient
    public BigDecimal getBalanceByCardNumber(String cardNumber) throws ApiException {
        BigDecimal cardBalance;
        if (cartaoRepository.existsByCardNumber(cardNumber)) {
            var card = cartaoRepository.findByCardNumber(cardNumber);
            cardBalance = card.stream().map(Card::getBalance).findFirst().get();
        } else {
            throw new ApiException(Messages.CARD_NOT_EXISTS, HttpStatus.NOT_FOUND);
        }
        return cardBalance;
    }

    @Override
    public void updateBalance(BigDecimal balance, String cardNumber) throws ApiException {
        var card = cartaoRepository.findByCardNumber(cardNumber);

        if (card.isEmpty()) {
            throw new ApiException(Messages.CARD_NOT_EXISTS, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        BigDecimal newBalance = card.stream().findFirst().get().getBalance();
        newBalance = newBalance.subtract(balance);
        card.orElseThrow().setBalance(newBalance);
        cartaoRepository.updateBalanceByCardNumber(card.get().getBalance(), cardNumber);
    }
}
