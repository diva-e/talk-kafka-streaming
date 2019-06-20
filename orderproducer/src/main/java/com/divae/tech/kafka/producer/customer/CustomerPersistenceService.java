package com.divae.tech.kafka.producer.customer;

import com.divae.tech.kafka.producer.model.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomerPersistenceService {

    private final Set<CustomerDto> customers = new HashSet<>();

    public Set<CustomerDto> getAllCustomers() {
        return customers;
    }

    public void addCustomer(final CustomerDto dto) {
        customers.add(dto);
    }
}
