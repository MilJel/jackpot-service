package com.jackpot.consumer.controller;

import com.jackpot.consumer.entity.JackpotContribution;
import com.jackpot.consumer.repository.JackpotContributionRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/jackpot")
@Tag(name = "Jackpot Controller", description = "Endpoint for viewing jackpot contributions")
public class JackpotController {

    private final JackpotContributionRepository repository;

    public JackpotController(JackpotContributionRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/contributions")
    @Operation(summary = "All contributions", description = "Returns a list of all jackpot contributions from the H2 database")
    public ResponseEntity<List<JackpotContribution>> getAllContributions() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/amount/{jackpotId}")
    @Operation(summary = "Current jackpot amount", description = "Returns the current accumulated jackpot amount for the given jackpotId")
    public ResponseEntity<BigDecimal> getCurrentAmount(@PathVariable UUID jackpotId) {
        BigDecimal amount = repository.findCurrentJackpotAmount(jackpotId);
        return ResponseEntity.ok(amount);
    }
}
