package com.build.paymentgateway.merchant.controller;

import com.build.paymentgateway.merchant.dtos.request.CreateApiKeyRequest;
import com.build.paymentgateway.merchant.dtos.response.ApiKeyResponse;
import com.build.paymentgateway.merchant.dtos.response.CreateApiKeyResponse;
import com.build.paymentgateway.merchant.services.ApiKeyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/merchants/{merchantId}/api-keys")
@RequiredArgsConstructor
public class ApiKeyController {

    private final ApiKeyService apiKeyService;

    @PostMapping
    public ResponseEntity<CreateApiKeyResponse> create(@PathVariable UUID merchantId,
                                                       @Valid @RequestBody CreateApiKeyRequest request) {
        CreateApiKeyResponse response = apiKeyService.createApiKey(merchantId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<ApiKeyResponse>> listByMerchant(@PathVariable UUID merchantId) {
        List<ApiKeyResponse> apiKeys = apiKeyService.listByMerchant(merchantId);
        return ResponseEntity.ok(apiKeys);
    }

    @DeleteMapping(path = "/{keyId}")
    public ResponseEntity<Void> delete(@PathVariable UUID merchantId, @PathVariable UUID keyId) {
        apiKeyService.revoke(merchantId, keyId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/{keyId}/rotate")
    public ResponseEntity<CreateApiKeyResponse> rotate(@PathVariable UUID merchantId, @PathVariable UUID keyId) {
        return ResponseEntity.ok(apiKeyService.rotate(merchantId, keyId));
    }
}
