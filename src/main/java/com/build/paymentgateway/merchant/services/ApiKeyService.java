package com.build.paymentgateway.merchant.services;

import com.build.paymentgateway.merchant.dtos.request.CreateApiKeyRequest;
import com.build.paymentgateway.merchant.dtos.response.ApiKeyResponse;

import java.util.UUID;

public interface ApiKeyService {
    public ApiKeyResponse createApiKey(UUID merchantId, CreateApiKeyRequest request);
}
