package com.visa.prj.orderapp.client;

import com.visa.prj.orderapp.entity.Customer;
import com.visa.prj.orderapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(2)
public class CustomerClient implements CommandLineRunner {
    private final OrderService service;

    @Override
    public void run(String... args) throws Exception {
        addCustomers();
    }

    private void addCustomers() {
        if(service.getCustomerCount() == 0) {
            Customer c1 = Customer.builder().
                    email("ana@visa.com").
                    firstName("Anna").lastName("Ben").
            build();

            Customer c2 = Customer.builder().
                    email("Uma@visa.com").
                    firstName("Uma").lastName("Thurman").
                    build();

            service.addCustomer(c1);
            service.addCustomer(c2);
        }
    }
}
