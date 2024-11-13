package org.acme.repository;

import org.acme.domain.ShippingOrder;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ShippingRepository implements PanacheRepository<ShippingOrder> {
    public ShippingOrder findByOrderId(Long orderId) {
        return find("orderId", orderId).firstResult();
    }
}
