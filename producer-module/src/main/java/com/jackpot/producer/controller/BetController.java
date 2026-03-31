package com.jackpot.producer.controller;

import com.jackpot.producer.dto.BetRequest;
import com.jackpot.producer.service.BetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/bets")
@Tag(name = "Bet Controller", description = "Endpoint for sending bets to a Kafka topic")
public class BetController {


    private final BetService betService;

    public BetController(BetService betService) {
        this.betService = betService;
    }

    @PostMapping("/publish")
    @Operation(summary = "Submit a bet", description = "Creates a JSON message and sends it to the Kafka topic 'jackpot-bets'")
    public ResponseEntity<String> publishBet(@RequestBody BetRequest betRequest) {
        SendResult<String, BetRequest> result;
        try {
            result = betService.publishBet(betRequest);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (result != null) {
        return ResponseEntity.accepted()
                .body("Bet with betId=" + betRequest.betId() + " is sent tp Kafka topic.");
        }
        return ResponseEntity.badRequest().build();
    }
}
