package com.visa.prj.orderapp.client;

import com.visa.prj.orderapp.entity.Customer;
import com.visa.prj.orderapp.entity.LineItem;
import com.visa.prj.orderapp.entity.Order;
import com.visa.prj.orderapp.entity.Product;
import com.visa.prj.orderapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderClient implements CommandLineRunner {
    private final OrderService service;

    @Override
    public void run(String... args) throws Exception {
        newOrder();
    }

    private void newOrder() {
        Order order = new Order();
//        Customer customer = Customer.builder().email("Uma@visa.com").build();
//        order.setCustomer(customer);
//
//        List<LineItem> items = new ArrayList<>();
//
//        items.add(LineItem.builder().product(Product.builder().id(2).build()).qty(1).build());
//        items.add(LineItem.builder().product(Product.builder().id(1).build()).qty(2).build());
//
//        order.setItems(items);
//
//        System.out.println(service.placeOrder(order));

        Customer customer = Customer.builder().email("ana@visa.com").build();
        order.setCustomer(customer);

        List<LineItem> items = new ArrayList<>();

        items.add(LineItem.builder().product(Product.builder().id(3).build()).qty(3).build());
        items.add(LineItem.builder().product(Product.builder().id(1).build()).qty(1).build());

        order.setItems(items);

        System.out.println(service.placeOrder(order));


    }
}
