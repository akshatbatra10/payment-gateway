package com.build.paymentgateway.merchant.entities;

import com.build.paymentgateway.common.enums.Environment;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "api_key")
public class ApiKey {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "merchant_id", nullable = false)
    private Merchant merchant;

    @Column(length = 50, nullable = false)
    private String apiKey;

    @Column(length = 150, nullable = false)
    private String apiSecretHash;

    @Column(length = 100, nullable = false)
    private String webhookSecretHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Environment environment;

    @Column(nullable = false)
    private boolean enabled = true;

    private Instant lastUsedAt;

    private Instant rotatedAt;

    private Instant gracePeriodExpiresAt;
}
