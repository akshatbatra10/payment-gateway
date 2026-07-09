package com.build.paymentgateway.operations.entities;

import com.build.paymentgateway.common.entity.Auditable;
import com.build.paymentgateway.common.entity.Money;
import com.build.paymentgateway.common.enums.SettlementStatus;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "settlement")
public class Settlement extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID merchantId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "amountUnits",
                    column = @Column(name = "grossAmountUnits")
            ),
            @AttributeOverride(
                    name = "currency",
                    column = @Column(name = "grossCurrency")
            )
    })
    private Money grossAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "amountUnits",
                    column = @Column(name = "refundAmountUnits")
            ),
            @AttributeOverride(
                    name = "currency",
                    column = @Column(name = "refundCurrency")
            )
    })
    private Money refundedAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "amountUnits",
                    column = @Column(name = "feeAmountUnits")
            ),
            @AttributeOverride(
                    name = "currency",
                    column = @Column(name = "feeCurrency")
            )
    })
    private Money feeAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "amountUnits",
                    column = @Column(name = "gstAmountUnits")
            ),
            @AttributeOverride(
                    name = "currency",
                    column = @Column(name = "gstCurrency")
            )
    })
    private Money gstAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "amountUnits",
                    column = @Column(name = "netAmountUnits")
            ),
            @AttributeOverride(
                    name = "currency",
                    column = @Column(name = "netCurrency")
            )
    })
    private Money netAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SettlementStatus status;

    @Column(nullable = false)
    private String bankReference;

    private Instant processed;
}
