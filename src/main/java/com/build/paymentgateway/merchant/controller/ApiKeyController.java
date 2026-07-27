package com.build.paymentgateway.merchant.controller;

import com.build.paymentgateway.merchant.dtos.request.CreateApiKeyRequest;
import com.build.paymentgateway.merchant.dtos.response.ApiKeyResponse;
import com.build.paymentgateway.merchant.services.ApiKeyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/merchants/{merchantId}/api-keys")
@RequiredArgsConstructor
public class ApiKeyController {

    private final ApiKeyService apiKeyService;

    @PostMapping
    public ResponseEntity<ApiKeyResponse> create(@PathVariable UUID merchantId,
                                                 @Valid @RequestBody CreateApiKeyRequest request) {
        ApiKeyResponse response = apiKeyService.createApiKey(merchantId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}
