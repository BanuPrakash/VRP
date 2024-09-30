package com.visa.prj.orderapp.client;

import com.visa.prj.orderapp.dto.OrderReport;
import com.visa.prj.orderapp.entity.Customer;
import com.visa.prj.orderapp.entity.LineItem;
import com.visa.prj.orderapp.entity.Order;
import com.visa.prj.orderapp.entity.Product;
import com.visa.prj.orderapp.service.EntityNotFoundException;
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
     //   newOrder();
//        getOrders();
 //       printOrders();
    }

    private void printOrders() {
        List<OrderReport> reports = service.getReport();
        for(OrderReport report : reports) {
            System.out.println(report.firstName() +", " + report.email() +", " + report.orderDate() +", " + report.total());
        }
    }

    private void getOrders() {
        List<Order> orders = service.getOrders();
        for(Order order : orders) {
            System.out.println(order.getCustomer().getFirstName() + ", "  + order.getOrderDate() + ", " + order.getTotal());
            var items = order.getItems(); // proxy
            for(LineItem item : items) {
                System.out.println(item.getProduct().getName() + ", " + item.getQty() +", " + item.getAmount());
            }
        }
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

        try {
            System.out.println(service.placeOrder(order));
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}
