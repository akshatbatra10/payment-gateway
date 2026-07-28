package com.build.paymentgateway.payment.services.impl;

import com.build.paymentgateway.common.enums.OrderStatus;
import com.build.paymentgateway.common.exception.DuplicateResourceException;
import com.build.paymentgateway.payment.dtos.CreateOrderRequest;
import com.build.paymentgateway.payment.dtos.OrderResponse;
import com.build.paymentgateway.payment.entities.OrderRecord;
import com.build.paymentgateway.payment.repositories.OrderRepository;
import com.build.paymentgateway.payment.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Value("${payment.order.default-order-expiry-seconds:1800}")
    private int defaultExpirySeconds = 3600;

    @Override
    public OrderResponse createOrder(UUID merchantId, CreateOrderRequest request) {
        if (request.receipt() != null &&
                orderRepository.existsByMerchantIdAndReceipt(merchantId, request.receipt())) {
            throw new DuplicateResourceException("ORDER_RECEIPT_DUPLICATE", "Order receipt already exists: " + request.receipt());
        }

        OrderRecord order = OrderRecord.builder()
                .receipt(request.receipt())
                .amount(request.amount())
                .notes(request.notes())
                .merchantId(merchantId)
                .orderStatus(OrderStatus.CREATED)
                .expiresAt(request.expiresAt() != null ? request.expiresAt() : Instant.now().plusSeconds(defaultExpirySeconds))
                .build();

        order = orderRepository.save(order);

        return new OrderResponse(
                order.getId(),
                order.getMerchantId(),
                order.getReceipt(),
                order.getAmount(),
                order.getOrderStatus(),
                order.getAttempts(),
                order.getNotes(),
                order.getExpiresAt(),
                null
        );
    }
}
