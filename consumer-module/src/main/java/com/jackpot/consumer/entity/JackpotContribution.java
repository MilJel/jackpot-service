package com.jackpot.consumer.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "jackpot_contribution")
public class JackpotContribution {

    @Id
    @Column(name = "bet_id", nullable = false)
    private UUID betId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "jackpot_id", nullable = false)
    private UUID jackpotId;

    @Column(name = "stake_amount", nullable = false, precision = 19, scale = 4)
    private BigDecimal stakeAmount;

    @Column(name = "contribution_amount", nullable = false, precision = 19, scale = 4)
    private BigDecimal contributionAmount;

    @Column(name = "current_jackpot_amount", nullable = false, precision = 19, scale = 4)
    private BigDecimal currentJackpotAmount;

    @Column(name = "created_at_date", nullable = false)
    private LocalDate createdAtDate;

    public JackpotContribution() {}

    public JackpotContribution(UUID betId, UUID userId, UUID jackpotId,
                                BigDecimal stakeAmount, BigDecimal contributionAmount,
                                BigDecimal currentJackpotAmount, LocalDate createdAtDate) {
        this.betId = betId;
        this.userId = userId;
        this.jackpotId = jackpotId;
        this.stakeAmount = stakeAmount;
        this.contributionAmount = contributionAmount;
        this.currentJackpotAmount = currentJackpotAmount;
        this.createdAtDate = createdAtDate;
    }

    public UUID getBetId() {
        return betId;
    }

    public void setBetId(UUID betId) {
        this.betId = betId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getJackpotId() {
        return jackpotId;
    }

    public void setJackpotId(UUID jackpotId) {
        this.jackpotId = jackpotId;
    }

    public BigDecimal getStakeAmount() {
        return stakeAmount;
    }

    public void setStakeAmount(BigDecimal stakeAmount) {
        this.stakeAmount = stakeAmount;
    }

    public BigDecimal getContributionAmount() {
        return contributionAmount;
    }

    public void setContributionAmount(BigDecimal contributionAmount) {
        this.contributionAmount = contributionAmount;
    }

    public BigDecimal getCurrentJackpotAmount() {
        return currentJackpotAmount;
    }

    public void setCurrentJackpotAmount(BigDecimal currentJackpotAmount) {
        this.currentJackpotAmount = currentJackpotAmount;
    }

    public LocalDate getCreatedAtDate() {
        return createdAtDate;
    }

    public void setCreatedAtDate(LocalDate createdAtDate) {
        this.createdAtDate = createdAtDate;
    }
}
