package com.build.paymentgateway.vault.entities;

import com.build.paymentgateway.common.entity.Auditable;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "vault_card")
public class VaultCard extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private byte[] encryptedPan;

    @Column(nullable = false)
    private byte[] encryptedDek;

    @Column(nullable = false, length = 4)
    private String lastFourDigits;

    @Column(nullable = false, length = 6)
    private String bin;

    @Column(length = 100)
    private String brand;

    @Column(length = 150)
    private String cardholderName;

    @Column(nullable = false)
    private Integer expiryMonth;

    @Column(nullable = false)
    private Integer expiryYear;

    private Instant deletedAt;
}
