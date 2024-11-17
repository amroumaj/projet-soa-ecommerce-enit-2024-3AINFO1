package org.acme.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer_addresses")
public class Address extends PanacheEntity {
    @Column(name = "customer_id", nullable = false)
    public Long customerId;

    @Column(name = "address", nullable = false)
    public String address;
}
