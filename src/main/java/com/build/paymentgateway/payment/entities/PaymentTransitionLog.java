package com.build.paymentgateway.payment.entities;

import com.build.paymentgateway.common.entity.CreatedAuditable;
import com.build.paymentgateway.common.enums.PaymentEvent;
import com.build.paymentgateway.common.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "payment_transition_log",
    indexes = {
        @Index(name = "idx_payment_transition_log_payment_id", columnList = "payment_id")
    })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentTransitionLog extends CreatedAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false, name = "payment_id")
    private Payment payment;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus from_status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus to_status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private PaymentEvent event;

    @Column(length = 100)
    private String actor;

    private Instant occurredAt;
}
