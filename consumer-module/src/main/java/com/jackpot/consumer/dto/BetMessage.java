package com.jackpot.consumer.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record BetMessage(
        UUID betId,
        UUID userId,
        UUID jackpotId,
        BigDecimal betAmount
) {}
