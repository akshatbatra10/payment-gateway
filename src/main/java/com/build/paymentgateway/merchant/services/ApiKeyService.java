package com.build.paymentgateway.merchant.services;

import com.build.paymentgateway.merchant.dtos.request.CreateApiKeyRequest;
import com.build.paymentgateway.merchant.dtos.response.ApiKeyResponse;
import com.build.paymentgateway.merchant.dtos.response.CreateApiKeyResponse;

import java.util.List;
import java.util.UUID;

public interface ApiKeyService {
    CreateApiKeyResponse createApiKey(UUID merchantId, CreateApiKeyRequest request);
    List<ApiKeyResponse> listByMerchant(UUID merchantId);
    void revoke(UUID merchantId, UUID apiKeyId);
    CreateApiKeyResponse rotate(UUID merchantId, UUID keyId);
}
