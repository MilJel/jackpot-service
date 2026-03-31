package com.jackpot.consumer.repository;

import com.jackpot.consumer.entity.JackpotContribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JackpotContributionRepository extends JpaRepository<JackpotContribution, UUID> {

    @Query("SELECT COALESCE(MAX(j.currentJackpotAmount), 0) FROM JackpotContribution j WHERE j.jackpotId = :jackpotId")
    BigDecimal findCurrentJackpotAmount(@Param("jackpotId") UUID jackpotId);

    Optional<JackpotContribution> findTopByJackpotIdOrderByCreatedAtDateDesc(UUID jackpotId);
}
