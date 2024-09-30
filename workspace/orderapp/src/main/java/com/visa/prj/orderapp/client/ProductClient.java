package com.visa.prj.orderapp.client;

import com.visa.prj.orderapp.entity.Product;
import com.visa.prj.orderapp.service.EntityNotFoundException;
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
      //  printByRange();
        getById();
    }

    private void getById() {
        try {
            Product p = service.getProductById(19);
            System.out.println(p);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }

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
