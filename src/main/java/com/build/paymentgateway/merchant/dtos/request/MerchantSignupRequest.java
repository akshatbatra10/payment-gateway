package com.build.paymentgateway.merchant.dtos.request;

import com.build.paymentgateway.common.enums.BusinessType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MerchantSignupRequest(
        @NotNull(message = "Name is required")
        @Size(max = 50, min = 3, message = "Name should be between 3 to 50 characters")
        String name,

        @Email
        @NotNull(message = "Email is required")
        String email,

        @NotNull(message = "Password is required")
        @Size(min = 8, message = "Password should be at least 8 characters long")
        String password,

        @Size(max = 50, message = "Business name should not be more than 50 characters")
        String businessName,

        BusinessType businessType
) {
}
