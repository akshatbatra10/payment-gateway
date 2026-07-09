package com.build.paymentgateway.merchant.repository;

import com.build.paymentgateway.merchant.entities.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MerchantRepository extends JpaRepository<Merchant, UUID> {
    public boolean existsByEmail(String email);
}
