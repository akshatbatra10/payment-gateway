package com.build.paymentgateway.merchant.entities;

import com.build.paymentgateway.common.entity.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "merchant_webhook_config",
        indexes = {
                @Index(name = "idx_webhook_merchant_id", columnList = "merchant_id, enabled")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MerchantWebhookConfig extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "merchant_id", nullable = false)
    private Merchant merchant;

    @Column(nullable = false, length = 500)
    private String targetUrl;

    @Column(nullable = false)
    private String webhookSecretHash;

    @Column(nullable = false)
    private boolean enabled = true;

    private String eventTypes;
}
