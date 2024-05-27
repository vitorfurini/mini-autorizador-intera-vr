package com.mini.autorizador.vr.service.transaction.impl;

import com.mini.autorizador.vr.application.i18n.Messages;
import com.mini.autorizador.vr.dto.transaction.TransactionDTO;
import com.mini.autorizador.vr.entity.card.Card;
import com.mini.autorizador.vr.entity.transaction.Transaction;
import com.mini.autorizador.vr.kafka.transaction.TransactionEvent;
import com.mini.autorizador.vr.kafka.transaction.TransactionProducer;
import com.mini.autorizador.vr.repository.CartaoRepository;
import com.mini.autorizador.vr.repository.transaction.TransactionRepository;
import com.mini.autorizador.vr.service.transaction.TransactionService;
import exception.ApiException;
import exception.transaction.TransactionException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    CartaoRepository cartaoRepository;
    TransactionRepository transactionRepository;

    private final ModelMapper mapper;
    private final TransactionProducer transactionProducer;

    @Autowired
    public TransactionServiceImpl(ModelMapper mapper, CartaoRepository cartaoRepository,
            TransactionRepository transactionRepository, TransactionProducer transactionProducer) {
        this.cartaoRepository = cartaoRepository;
        this.transactionRepository = transactionRepository;
        this.mapper = mapper;
        this.transactionProducer = transactionProducer;
    }

    @Override
    public Object createTransaction(TransactionDTO transactionDTO) throws ApiException, TransactionException {
        try {
            var card = cartaoRepository.findByCardNumber(transactionDTO.getCardNumber());
                verificarCartaoNaoExistente(card);
                validatePassword(card.stream().findFirst().get(), transactionDTO.getPassword());
                validateBalance(card.stream().findFirst().get(), transactionDTO.getValue());

            transactionProducer.sendTransaction(TransactionEvent.create(transactionDTO));
            return transactionDTO;
        } catch (TransactionException e) {
            throw new TransactionException(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public void save(TransactionDTO dto) {

    transactionRepository.save(mapper.map(dto, Transaction.class));
    }

    private List<Transaction> addTransactionToCard(TransactionDTO transactionDTO) {
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(mapper.map(transactionDTO, Transaction.class));
        return transactionList;
    }

    private void verificarCartaoNaoExistente(Optional<Card> cartao) throws ApiException{
        if(cartao.isEmpty()){
            throw new ApiException(Messages.CARD_NOT_EXISTS, HttpStatus.NOT_FOUND);
        }
    }

    private void validateBalance(Card card, BigDecimal decreaseValue)throws TransactionException{
        if(decreaseValue.compareTo(card.getBalance()) == 1){
            throw new TransactionException(Messages.SALDO_INSUFICIENTE, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    private void validatePassword(Card card, String passwordCard)throws TransactionException{
        if(!card.getPassword().equals(passwordCard)){
            throw new TransactionException(Messages.PASSWORD_INCORRECT, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
