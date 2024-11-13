package org.acme.repository;

import org.acme.domain.Address;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AddressRepository implements PanacheRepository<Address> {
    public Address findByCustomerId(Long customerId) {
        return find("customerId", customerId).firstResult();
    }
}
