package com.mini.autorizador.vr.kafka.transaction;

import com.mini.autorizador.vr.dto.transaction.TransactionDTO;
import com.mini.autorizador.vr.service.card.CartaoService;
import com.mini.autorizador.vr.service.transaction.TransactionService;
import exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.mini.autorizador.vr.kafka.KafkaTopics.TRANSACTIONS_TOPIC;

@Service
@RequiredArgsConstructor
public class TransactionConsumer {

    private final TransactionService transactionService;
    private final CartaoService cartaoService;
    private final ModelMapper mapper;

    @KafkaListener(id = "transactionConsumer", topics = TRANSACTIONS_TOPIC, containerFactory =
            "transactionEventKafkaListenerContainerFactory")
    public void listen(TransactionEvent transactionEvent) throws ApiException {

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setCardNumber(transactionEvent.cardNumber());
        transactionDTO.setPassword(transactionEvent.password());
        transactionDTO.setValue(transactionEvent.value());

        cartaoService.updateBalance(transactionDTO.getValue(), transactionDTO.getCardNumber());
        transactionService.save(transactionDTO);
    }
}
