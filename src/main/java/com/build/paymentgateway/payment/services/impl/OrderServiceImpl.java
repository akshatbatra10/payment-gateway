package com.build.paymentgateway.payment.services.impl;

import com.build.paymentgateway.common.enums.OrderStatus;
import com.build.paymentgateway.common.exception.BusinessRuleViolationException;
import com.build.paymentgateway.common.exception.DuplicateResourceException;
import com.build.paymentgateway.common.exception.ResourceNotFoundException;
import com.build.paymentgateway.payment.dtos.request.CreateOrderRequest;
import com.build.paymentgateway.payment.dtos.response.OrderResponse;
import com.build.paymentgateway.payment.dtos.response.PaymentResponse;
import com.build.paymentgateway.payment.entities.OrderRecord;
import com.build.paymentgateway.payment.entities.Payment;
import com.build.paymentgateway.payment.mapper.PaymentMapper;
import com.build.paymentgateway.payment.repositories.OrderRepository;
import com.build.paymentgateway.payment.repositories.PaymentRepository;
import com.build.paymentgateway.payment.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

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

    @Override
    public OrderResponse getById(UUID merchantId, UUID orderId) {
        OrderRecord order = orderRepository.findByIdAndMerchantId(orderId, merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", orderId));

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

    @Override
    @Transactional
    public OrderResponse cancel(UUID merchantId, UUID orderId) {
        OrderRecord order = orderRepository.findByIdAndMerchantId(orderId, merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", orderId));

        if (order.getOrderStatus().equals(OrderStatus.CANCELLED) || order.getOrderStatus().equals(OrderStatus.PAID)) {
            throw new BusinessRuleViolationException("ORDER_CANNOT_CANCEL", "Cannot cancel order with status " + order.getOrderStatus().name());
        }

        order.setOrderStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);

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

    @Override
    public List<PaymentResponse> listPayments(UUID merchantId, UUID orderId) {
        orderRepository.findByIdAndMerchantId(orderId, merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", orderId));

        List<Payment> payments = paymentRepository.findByOrder_Id(orderId);

        return payments.stream()
                .map(paymentMapper::toPaymentResponse)
                .collect(Collectors.toList());
    }
}
