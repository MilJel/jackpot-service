package com.jackpot.producer.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record BetRequest(
        UUID betId,
        UUID userId,
        UUID jackpotId,
        BigDecimal betAmount
) {
    public BetRequest {
        if (betId == null) betId = UUID.randomUUID();
    }
}
