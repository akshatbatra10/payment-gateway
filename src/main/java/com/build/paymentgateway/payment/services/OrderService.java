package com.build.paymentgateway.payment.services;

import com.build.paymentgateway.payment.dtos.request.CreateOrderRequest;
import com.build.paymentgateway.payment.dtos.response.OrderResponse;
import com.build.paymentgateway.payment.dtos.response.PaymentResponse;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderResponse createOrder(UUID merchantId, CreateOrderRequest createOrderRequest);

    OrderResponse getById(UUID merchantId, UUID orderId);

    OrderResponse cancel(UUID merchantId, UUID orderId);

    List<PaymentResponse> listPayments(UUID merchantId, UUID orderId);
}
