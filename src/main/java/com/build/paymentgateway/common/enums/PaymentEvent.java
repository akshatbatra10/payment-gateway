package com.build.paymentgateway.common.enums;

public enum PaymentEvent {
    AUTHORIZE_ATTEMPT,
    AUTHORIZE_SUCCESS,
    AUTHORIZE_FAILURE,
    CAPTURE_REQUEST,
    CAPTURE_SUCCESS,
    CAPTURE_FAILURE,
    REFUND_REQUEST,
    REFUND_COMPLETE,
    SETTLE,
    CANCEL,
    CAPTURE_TIMEOUT
}
