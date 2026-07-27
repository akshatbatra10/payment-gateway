package com.build.paymentgateway.merchant.repository;

import com.build.paymentgateway.merchant.entities.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApiKeyRepository extends JpaRepository<ApiKey, UUID> {
}
