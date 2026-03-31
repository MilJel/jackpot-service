package com.jackpot.consumer.service;

import com.jackpot.consumer.dto.BetMessage;
import com.jackpot.consumer.entity.JackpotContribution;
import com.jackpot.consumer.repository.JackpotContributionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;


@Service
public class JackpotService {

    private static final BigDecimal CONTRIBUTION_RATE = new BigDecimal("0.10");
    private static final BigDecimal JACKPOT_THRESHOLD = new BigDecimal("10000.00");

    private final JackpotContributionRepository repository;

    public JackpotService(JackpotContributionRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public boolean processBet(BetMessage bet) {
        BigDecimal contribution = bet.betAmount().multiply(CONTRIBUTION_RATE);

        BigDecimal previousJackpot = repository.findCurrentJackpotAmount(bet.jackpotId());
        BigDecimal newJackpotAmount = previousJackpot.add(contribution);

        JackpotContribution entity = new JackpotContribution(
                bet.betId(),
                bet.userId(),
                bet.jackpotId(),
                bet.betAmount(),
                contribution,
                newJackpotAmount,
                LocalDate.now()
        );

        repository.save(entity);

        boolean jackpotWon = newJackpotAmount.compareTo(JACKPOT_THRESHOLD) >= 0;

        if (jackpotWon) {
            System.out.println("JACKPOT WIN! Player:" + bet.userId()
                    + " | JackpotId: " + bet.jackpotId()
                    + " | Amount: " + newJackpotAmount);
        }

        return jackpotWon;
    }
}
