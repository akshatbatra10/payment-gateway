package com.build.paymentgateway.merchant.mapper;

import com.build.paymentgateway.merchant.dtos.request.CreateApiKeyRequest;
import com.build.paymentgateway.merchant.dtos.request.MerchantSignupRequest;
import com.build.paymentgateway.merchant.dtos.response.MerchantResponse;
import com.build.paymentgateway.merchant.entities.Merchant;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MerchantMapper {

    Merchant toEntityFromSignupRequest(MerchantSignupRequest merchantSignupRequest);

    MerchantResponse toMerchantResponse(Merchant merchant);
}
