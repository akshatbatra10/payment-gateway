package com.build.paymentgateway.payment.dtos;

import com.build.paymentgateway.common.entity.Money;
import com.build.paymentgateway.common.enums.OrderStatus;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public record OrderResponse(
        UUID id,
        UUID merchant,
        String receipt,
        Money amount,
        OrderStatus status,
        Integer attempts,
        Map<String, Object> notes,
        Instant expiresAt,
        Instant createdAt
) {
}
