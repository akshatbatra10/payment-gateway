package com.build.paymentgateway.payment.services;

import com.build.paymentgateway.payment.dtos.CreateOrderRequest;
import com.build.paymentgateway.payment.dtos.OrderResponse;

import java.util.UUID;

public interface OrderService {
    OrderResponse createOrder(UUID merchantId, CreateOrderRequest createOrderRequest);
}
