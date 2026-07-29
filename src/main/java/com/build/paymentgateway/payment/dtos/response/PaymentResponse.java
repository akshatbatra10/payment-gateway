package com.build.paymentgateway.payment.dtos.response;

import com.build.paymentgateway.common.entity.Money;
import com.build.paymentgateway.common.enums.PaymentMethod;
import com.build.paymentgateway.common.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PaymentResponse(
        UUID id,
        UUID orderId,
        UUID merchantId,
        Money amount,
        PaymentStatus status,
        PaymentMethod method,
        Map<String, Object> methodDetails,
        String cardLastFour,
        String cardBrand,
        String bankReference,
        String errorCode,
        String errorDescription,
        Long refundedAmountPaise,
        Instant capturedAt,
        Instant createdAt
) {
}
