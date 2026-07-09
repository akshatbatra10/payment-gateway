package com.build.paymentgateway.merchant.dtos.response;

import com.build.paymentgateway.common.enums.BusinessType;
import com.build.paymentgateway.common.enums.MerchantStatus;

import java.util.UUID;

public record MerchantResponse(
        UUID id,
        String name,
        String email,
        String businessName,
        BusinessType businessType,
        MerchantStatus merchantStatus
) {
}
