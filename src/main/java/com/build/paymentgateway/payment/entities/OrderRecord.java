package com.build.paymentgateway.payment.entities;

import com.build.paymentgateway.common.entity.Auditable;
import com.build.paymentgateway.common.entity.Money;
import com.build.paymentgateway.common.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "order_record",
        indexes = {
                @Index(name = "idx_order_id_merchant_id", columnList = "id, merchant_id"),
                @Index(name = "idx_order_merchant_id", columnList = "merchant_id")
        })
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRecord extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // no FK - cross-service boundary
    @Column(name = "merchant_id", nullable = false)
    private UUID merchantId;

    @Embedded
    private Money amount;

    @Column(length = 100)
    private String receipt;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private OrderStatus orderStatus = OrderStatus.CREATED;

    @Column(nullable = false)
    @Builder.Default
    private Integer attempts = 0;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> notes;

    @Column(nullable = false)
    private Instant expiresAt;
}
