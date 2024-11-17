package org.acme.service;

import org.acme.domain.Address;
import org.acme.domain.ShippingOrder;
import org.acme.domain.ShippingOrder.ShippingStatus;
import org.acme.repository.AddressRepository;
import org.acme.repository.ShippingRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ShippingService {

    @Inject
    ShippingRepository shippingOrderRepository;

    @Inject
    AddressRepository customerAddressRepository;

    @Transactional
    public ShippingOrder createShippingOrder(Long orderId, Long customerId) {
        Address address = customerAddressRepository.findByCustomerId(customerId);
        if (address == null) {
            throw new IllegalArgumentException("Customer address not found");
        }

        ShippingOrder shippingOrder = new ShippingOrder();
        shippingOrder.orderId = orderId;
        shippingOrder.customerId = customerId;
        shippingOrder.status = ShippingStatus.PENDING;
        shippingOrderRepository.persist(shippingOrder);
        return shippingOrder;
    }

    @Transactional
    public void updateShippingStatus(Long orderId, ShippingStatus status) {
        ShippingOrder shippingOrder = shippingOrderRepository.findByOrderId(orderId);
        if (shippingOrder == null) {
            throw new IllegalArgumentException("Shipping order not found");
        }
        shippingOrder.status = status;
        shippingOrderRepository.persist(shippingOrder);
    }

    public Address addCustomerAddress(Long customerId, String address) {
        Address customerAddress = new Address();
        customerAddress.customerId = customerId;
        customerAddress.address = address;
        customerAddressRepository.persist(customerAddress);
        return customerAddress;
    }
}
