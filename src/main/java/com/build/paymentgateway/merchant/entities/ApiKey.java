package com.build.paymentgateway.merchant.entities;

import com.build.paymentgateway.common.entity.Auditable;
import com.build.paymentgateway.common.enums.Environment;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "api_key",
        indexes = {
            @Index(name = "idx_api_key_merchant_env", columnList = "merchant_id, environment, enabled")
        })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiKey extends Auditable {

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

    @Column(length = 150)
    private String prevApiSecretHash;

    @Column(length = 100, nullable = false)
    private String webhookSecretHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Environment environment;

    @Column(nullable = false)
    @Builder.Default
    private boolean enabled = true;

    private Instant lastUsedAt;

    private Instant rotatedAt;

    private Instant gracePeriodExpiresAt;
}
