package com.build.paymentgateway.merchant.services;

import com.build.paymentgateway.merchant.dtos.request.CreateApiKeyRequest;
import com.build.paymentgateway.merchant.dtos.response.ApiKeyResponse;
import com.build.paymentgateway.merchant.dtos.response.CreateApiKeyResponse;

import java.util.List;
import java.util.UUID;

public interface ApiKeyService {
    public CreateApiKeyResponse createApiKey(UUID merchantId, CreateApiKeyRequest request);
    public List<ApiKeyResponse> listByMerchant(UUID merchantId);
    public void deleteApiKey(UUID merchantId, UUID apiKeyId);
}
