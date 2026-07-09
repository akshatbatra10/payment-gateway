package com.build.paymentgateway.merchant.repository;

import com.build.paymentgateway.merchant.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AppUserRepository extends JpaRepository<AppUser, UUID> {
}
