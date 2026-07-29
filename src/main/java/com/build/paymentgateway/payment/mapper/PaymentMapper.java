package com.build.paymentgateway.payment.mapper;

import com.build.paymentgateway.payment.dtos.response.PaymentResponse;
import com.build.paymentgateway.payment.entities.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentMapper {

    @Mapping(target = "orderId", source = "order.id")
    PaymentResponse toPaymentResponse(Payment payment);
}
 