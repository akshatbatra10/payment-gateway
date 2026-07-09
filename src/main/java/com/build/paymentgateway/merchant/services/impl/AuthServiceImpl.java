package com.build.paymentgateway.merchant.services.impl;

import com.build.paymentgateway.common.enums.MerchantStatus;
import com.build.paymentgateway.common.enums.UserRole;
import com.build.paymentgateway.common.exception.DuplicateResourceException;
import com.build.paymentgateway.merchant.dtos.request.MerchantSignupRequest;
import com.build.paymentgateway.merchant.dtos.response.MerchantResponse;
import com.build.paymentgateway.merchant.entities.AppUser;
import com.build.paymentgateway.merchant.entities.Merchant;
import com.build.paymentgateway.merchant.repository.AppUserRepository;
import com.build.paymentgateway.merchant.repository.MerchantRepository;
import com.build.paymentgateway.merchant.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MerchantRepository merchantRepository;
    private final AppUserRepository appUserRepository;

    @Override
    @Transactional
    public MerchantResponse signup(MerchantSignupRequest request) {
        if (merchantRepository.existsByEmail(request.email())) {
            throw new DuplicateResourceException("DUPLICATE_MERCHANT_EMAIL",
                    "Merchant with email already exist: " + request.email());
        }
        Merchant merchant = Merchant.builder()
                .name(request.name())
                .email(request.email())
                .businessName(request.businessName())
                .businessType(request.businessType())
                .status(MerchantStatus.PENDING_KYC)
                .build();

        merchant =  merchantRepository.save(merchant);

        AppUser appUser = AppUser.builder()
                .name(request.name())
                .email(request.email())
                .password(request.password())
                .merchant(merchant)
                .role(UserRole.OWNER)
                .build();

        appUserRepository.save(appUser);

        return new MerchantResponse(merchant.getId(), merchant.getName(), merchant.getEmail(),
                merchant.getBusinessName(), merchant.getBusinessType(), merchant.getStatus());
    }
}
