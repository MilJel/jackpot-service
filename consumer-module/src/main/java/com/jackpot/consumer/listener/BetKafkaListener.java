package com.jackpot.consumer.listener;

import com.jackpot.consumer.dto.BetMessage;
import com.jackpot.consumer.service.JackpotService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class BetKafkaListener {

    private final JackpotService jackpotService;

    public BetKafkaListener(JackpotService jackpotService) {
        this.jackpotService = jackpotService;
    }

    @KafkaListener(
            topics = "${kafka.topic}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(BetMessage bet) {
        System.out.println("Message received: betId=" + bet.betId() + " | userId=" + bet.userId());
        boolean won = jackpotService.processBet(bet);
        if (won) {
            System.out.println("Jackpot was won for jackpotId=" + bet.jackpotId());
        } else {
            System.out.println("Jackpot not won.");
        }
    }
}
