package com.build.paymentgateway.payment.mapper;

import com.build.paymentgateway.payment.dtos.response.OrderResponse;
import com.build.paymentgateway.payment.entities.OrderRecord;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    OrderResponse toOrderResponse(OrderRecord order);
}
