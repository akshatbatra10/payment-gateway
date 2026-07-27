package com.build.paymentgateway.merchant.dtos.request;

import com.build.paymentgateway.common.enums.Environment;

public record CreateApiKeyRequest(
        Environment environment
) {
}
