package com.mini.autorizador.vr.kafka.transaction;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.mini.autorizador.vr.kafka.KafkaTopics.TRANSACTIONS_TOPIC;

@Service
@RequiredArgsConstructor
public class TransactionProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper objectMapper;

    public void sendTransaction(TransactionEvent transactionEvent) {
        try {
            String transaction = objectMapper.writeValueAsString(transactionEvent);
            kafkaTemplate.send(TRANSACTIONS_TOPIC, String.valueOf(transactionEvent.cardNumber()), transaction);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
