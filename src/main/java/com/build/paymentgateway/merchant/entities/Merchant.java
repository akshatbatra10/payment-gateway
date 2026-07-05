package com.build.paymentgateway.merchant.entities;

import com.build.paymentgateway.common.enums.BusinessType;
import com.build.paymentgateway.common.enums.MerchantStatus;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "merchant")
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false, length = 150)
    private String businessName;

    @Enumerated(EnumType.STRING)
    private BusinessType businessType;

    @Column(length = 20)
    private String contactNumber;

    @Column(length = 200)
    private String websiteUrl;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MerchantStatus status = MerchantStatus.PENDING_KYC;

    @Column(length = 20)
    private String gstId;

    @Column(length = 20)
    private String panId;

    @Column(length = 50)
    private String settlementBankAccountNumber;

    @Column(length = 150)
    private String settlementBankName;

    @Column(length = 100)
    private String settlementBankIfscCode;

    @OneToMany(mappedBy = "merchant")
    private Set<AppUser> appUsers = new HashSet<>();
}
