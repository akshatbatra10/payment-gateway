package com.build.paymentgateway.merchant.controller;

import com.build.paymentgateway.merchant.dtos.response.MerchantResponse;
import com.build.paymentgateway.merchant.dtos.request.MerchantSignupRequest;
import com.build.paymentgateway.merchant.services.impl.AuthServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authService;

    @PostMapping(path = "/signup")
    public ResponseEntity<MerchantResponse> signup(@RequestBody @Valid MerchantSignupRequest request) {
        MerchantResponse merchant = authService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(merchant);
    }
}
