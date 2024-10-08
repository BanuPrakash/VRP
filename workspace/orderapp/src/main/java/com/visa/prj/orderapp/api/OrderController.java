package com.visa.prj.orderapp.api;


import com.visa.prj.orderapp.dto.OrderReport;
import com.visa.prj.orderapp.entity.Order;
import com.visa.prj.orderapp.service.EntityNotFoundException;
import com.visa.prj.orderapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;
    // GET http://localhost:8080/api/orders
    // GET http://localhost:8080/api/orders?order-date=2024-09-29

    @Secured("ADMIN")
    @GetMapping()
    public List<Order> getOrders(@RequestParam(name = "order-date", required = false)
                                     @DateTimeFormat(pattern = "yyyy-MM-dd") Date orderDate) {
        System.out.println(orderDate);
        if(orderDate != null) {
            return service.getOrdersByDate(orderDate);
        }
        return service.getOrders();
    }

    @PostMapping()
    public String placeOrder(@RequestBody Order o) throws EntityNotFoundException {
        return service.placeOrder(o);
    }

    @GetMapping("/report")
    public List<OrderReport> getReport() {
        return service.getReport();
    }


}
