package com.jackpot.producer.service;

import com.jackpot.producer.dto.BetRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class BetService {

    private final KafkaTemplate<String, BetRequest> kafkaTemplate;

    @Value("${kafka.topic.jackpot-bets}")
    private String topic;

    public BetService(KafkaTemplate<String, BetRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public SendResult<String, BetRequest> publishBet(BetRequest betRequest) throws ExecutionException, InterruptedException {

        CompletableFuture<SendResult<String, BetRequest>> future =
                kafkaTemplate.send(topic, betRequest.betId().toString(), betRequest);

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                System.err.println("Error sending message: " + ex.getMessage());
            } else {
                System.out.println("Message sent to topic: " + result.getRecordMetadata().topic()
                        + " | partition: " + result.getRecordMetadata().partition()
                        + " | offset: " + result.getRecordMetadata().offset());
            }
        });

        return future.get();
    }
}
