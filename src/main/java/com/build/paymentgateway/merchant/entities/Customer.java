package com.build.paymentgateway.merchant.entities;

import com.build.paymentgateway.common.entity.Auditable;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "customer")
public class Customer extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "merchant_id", nullable = false)
    private Merchant merchant;

    @Column(length = 150)
    private String name;

    @Column(length = 150)
    private String email;

    @Column(length = 150)
    private String phone;

    @Column(length = 20)
    private String gstId;
}
