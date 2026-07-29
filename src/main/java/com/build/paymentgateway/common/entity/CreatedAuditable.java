package com.build.paymentgateway.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class CreatedAuditable {

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private Instant createdAt;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;
}