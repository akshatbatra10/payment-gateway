package com.build.paymentgateway.payment.repositories;

import com.build.paymentgateway.payment.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
