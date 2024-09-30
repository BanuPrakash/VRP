package com.visa.prj.orderapp.client;

import com.visa.prj.orderapp.entity.Product;
import com.visa.prj.orderapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Order(1)
public class ProductClient implements CommandLineRunner {
    private final OrderService service;

    @Override
    public void run(String... args) throws Exception {
//           printProducts();
        printByRange();
    }

    private void printByRange() {
        List<Product> products = service.byRange(5000, 25000);
        for(Product p : products) {
            System.out.println(p);
        }
    }

    private void printProducts() {
        List<Product> products = service.getProducts();
        for(Product p : products) {
            System.out.println(p);
        }
    }
}
