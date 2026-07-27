package com.build.paymentgateway.merchant.services.impl;

import com.build.paymentgateway.common.exception.ResourceNotFoundException;
import com.build.paymentgateway.merchant.dtos.request.CreateApiKeyRequest;
import com.build.paymentgateway.merchant.dtos.response.ApiKeyResponse;
import com.build.paymentgateway.merchant.entities.ApiKey;
import com.build.paymentgateway.merchant.entities.Merchant;
import com.build.paymentgateway.merchant.repository.ApiKeyRepository;
import com.build.paymentgateway.merchant.repository.MerchantRepository;
import com.build.paymentgateway.merchant.services.ApiKeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApiKeyServiceImpl implements ApiKeyService {

    private final ApiKeyRepository apiKeyRepository;
    private final MerchantRepository merchantRepository;

    @Override
    public ApiKeyResponse createApiKey(UUID merchantId, CreateApiKeyRequest request) {
        Merchant merchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("merchant", ""));

        String apiKeyId = "apg_" + request.environment().name().toUpperCase() + "big_secret";
        String rawSecret = "big_random_secret";

        ApiKey apiKey = ApiKey.builder()
                .merchant(merchant)
                .apiKey(apiKeyId)
                .apiSecretHash(rawSecret)
                .environment(request.environment())
                .build();

        apiKey = apiKeyRepository.save(apiKey);

        return new ApiKeyResponse(apiKey.getId(), apiKeyId, rawSecret, request.environment());
    }
}
