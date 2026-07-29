package com.build.paymentgateway.payment.controller;

import com.build.paymentgateway.payment.dtos.request.CreateOrderRequest;
import com.build.paymentgateway.payment.dtos.response.OrderResponse;
import com.build.paymentgateway.payment.services.impl.OrderServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceImpl orderService;

    UUID merchantId = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody @Valid CreateOrderRequest order) {
        OrderResponse response = orderService.createOrder(merchantId, order);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
