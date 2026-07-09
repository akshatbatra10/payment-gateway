package com.build.paymentgateway.merchant.services;

import com.build.paymentgateway.merchant.dtos.request.MerchantSignupRequest;
import com.build.paymentgateway.merchant.dtos.response.MerchantResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthService {
    public MerchantResponse signup(MerchantSignupRequest request);
}
