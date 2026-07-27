package com.build.paymentgateway.merchant.dtos.response;

import com.build.paymentgateway.common.enums.Environment;

import java.util.UUID;

public record ApiKeyResponse(
        UUID id,
        String keyId,
        String keySecret,
        Environment environment
) {
}
