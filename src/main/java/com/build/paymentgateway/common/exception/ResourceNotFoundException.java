package com.build.paymentgateway.common.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {

    private final String resourceName;
    private final String identifier;

    public ResourceNotFoundException(String resourceName, String identifier) {
        super("Resource " + resourceName + " with identifier " + identifier + " not found");
        this.resourceName = resourceName;
        this.identifier = identifier;
    }
}
