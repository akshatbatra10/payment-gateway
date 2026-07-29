package com.build.paymentgateway.merchant.mapper;

import com.build.paymentgateway.merchant.dtos.response.ApiKeyResponse;
import com.build.paymentgateway.merchant.dtos.response.CreateApiKeyResponse;
import com.build.paymentgateway.merchant.entities.ApiKey;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ApiKeyMapper {

    CreateApiKeyResponse toCreateApiKeyResponse(ApiKey apiKey);

    List<ApiKeyResponse> toApiKeyResponseList(List<ApiKey> apiKeys);
}
