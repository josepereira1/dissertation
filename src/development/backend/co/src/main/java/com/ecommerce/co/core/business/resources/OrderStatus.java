package com.ecommerce.co.core.business.resources;

public enum OrderStatus {
    PENDING_PAYMENT, FAILED, PROCESSING, COMPLETED, CANCELED
}

/*

Pending payment — Order received, no payment initiated. Awaiting payment (unpaid).
Failed — Payment failed or was declined (unpaid) or requires authentication (SCA). Note that this status may not show immediately and instead show as Pending until verified (e.g., PayPal).
Processing — Payment received (paid) and stock has been reduced; order is awaiting fulfillment. All product orders require processing, except those that only contain products which are both Virtual and Downloadable.
Completed — Order fulfilled and complete – requires no further action.
Canceled — Canceled by an admin or the customer – stock is increased, no further action required.
Refunded — Refunded by an admin – no further action required.

 */