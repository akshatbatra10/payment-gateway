package com.build.paymentgateway.common.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Money {
    private Integer amountUnits;
    private String currency;

    public static Money of(Integer amountUnits, String currency) {
        return new Money(amountUnits, currency);
    }

    public static Money inr(Integer amountUnits, String currency) {
        return new Money(amountUnits, "INR");
    }

    public Money add(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Cannot add moneys with different currency");
        }
        return new Money(this.amountUnits + other.amountUnits, currency);
    }

    public Money subtract(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Cannot subtract moneys with different currency");
        }
        return new Money(this.amountUnits - other.amountUnits, currency);
    }
}
