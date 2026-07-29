package com.build.paymentgateway.operations.entities;

import com.build.paymentgateway.common.entity.Auditable;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class SettlementPaymentId extends Auditable implements Serializable {

    private UUID settlementId;
    private UUID paymentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SettlementPaymentId that)) return false;
        return settlementId.equals(that.settlementId) &&
                paymentId.equals(that.paymentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(settlementId, paymentId);
    }
}
