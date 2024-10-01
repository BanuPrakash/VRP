package com.visa.prj.orderapp.api;


import com.visa.prj.orderapp.dto.OrderReport;
import com.visa.prj.orderapp.entity.Order;
import com.visa.prj.orderapp.service.EntityNotFoundException;
import com.visa.prj.orderapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;
    @GetMapping()
    public List<Order> getOrders() {
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
