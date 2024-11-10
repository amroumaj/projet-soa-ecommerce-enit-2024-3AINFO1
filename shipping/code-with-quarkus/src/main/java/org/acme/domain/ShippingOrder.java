package org.acme.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@Table(name = "shipping_orders")
public class ShippingOrder extends PanacheEntity {

    @Column(name = "order_id", nullable = false, unique = true)
    public Long orderId;

    @Column(name = "customer_id", nullable = false)
    public Long customerId;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    public ShippingStatus status;

    public enum ShippingStatus {
        PENDING, IN_PROGRESS, DELIVERED
    }
}
