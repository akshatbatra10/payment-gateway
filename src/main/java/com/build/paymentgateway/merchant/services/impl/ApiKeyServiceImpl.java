package com.build.paymentgateway.merchant.services.impl;

import com.build.paymentgateway.common.exception.ResourceNotFoundException;
import com.build.paymentgateway.common.util.RandomizerUtil;
import com.build.paymentgateway.merchant.dtos.request.CreateApiKeyRequest;
import com.build.paymentgateway.merchant.dtos.response.ApiKeyResponse;
import com.build.paymentgateway.merchant.dtos.response.CreateApiKeyResponse;
import com.build.paymentgateway.merchant.entities.ApiKey;
import com.build.paymentgateway.merchant.entities.Merchant;
import com.build.paymentgateway.merchant.repository.ApiKeyRepository;
import com.build.paymentgateway.merchant.repository.MerchantRepository;
import com.build.paymentgateway.merchant.services.ApiKeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApiKeyServiceImpl implements ApiKeyService {

    private final ApiKeyRepository apiKeyRepository;
    private final MerchantRepository merchantRepository;

    @Override
    @Transactional
    public CreateApiKeyResponse createApiKey(UUID merchantId, CreateApiKeyRequest request) {
        Merchant merchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("merchant", ""));

        String apiKeyId = "apg_" + request.environment().name().toLowerCase() + "_" + RandomizerUtil.randomBase64(24);
        String rawSecret = RandomizerUtil.randomBase64(40);

        ApiKey apiKey = ApiKey.builder()
                .merchant(merchant)
                .apiKey(apiKeyId)
                .apiSecretHash(rawSecret)
                .environment(request.environment())
                .build();

        apiKey = apiKeyRepository.save(apiKey);

        return new CreateApiKeyResponse(apiKey.getId(), apiKeyId, rawSecret, request.environment());
    }

    @Override
    public List<ApiKeyResponse> listByMerchant(UUID merchantId) {
        Merchant merchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("merchant", ""));

        List<ApiKey> apiKeys = apiKeyRepository.findByMerchant_Id(merchantId);

        return apiKeys.stream()
                .map(apiKey -> new ApiKeyResponse(
                        apiKey.getId(),
                        apiKey.getApiKey(),
                        apiKey.getEnvironment(),
                        apiKey.isEnabled(),
                        apiKey.getLastUsedAt(),
                        null
                ))
                .toList();
    }

    @Override
    @Transactional
    public void revoke(UUID merchantId, UUID apiKeyId) {
        ApiKey apiKey = apiKeyRepository.findById(apiKeyId)
                .filter(key -> key.getMerchant().getId().equals(merchantId))
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey", apiKeyId));

        apiKey.setEnabled(false);
    }

    @Override
    @Transactional
    public CreateApiKeyResponse rotate(UUID merchantId, UUID apiKeyId) {
        ApiKey apiKey = apiKeyRepository.findById(apiKeyId)
                .filter(key -> key.getMerchant().getId().equals(merchantId))
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey", apiKeyId));

        if (!apiKey.isEnabled()) {
            throw new RuntimeException("Cannot rotate api key");
        }

        String newRawSecret = RandomizerUtil.randomBase64(40);
        apiKey.setPrevApiSecretHash(apiKey.getApiSecretHash());
        apiKey.setApiSecretHash(newRawSecret);
        apiKey.setRotatedAt(Instant.now());
        apiKey.setGracePeriodExpiresAt(Instant.now().plusSeconds(60 * 60 * 24));
        apiKey =  apiKeyRepository.save(apiKey);

        return new CreateApiKeyResponse(
                apiKey.getId(),
                apiKey.getApiKey(),
                newRawSecret,
                apiKey.getEnvironment()
        );
    }
}
