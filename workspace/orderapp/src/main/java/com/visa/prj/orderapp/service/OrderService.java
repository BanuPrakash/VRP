package com.visa.prj.orderapp.service;

import com.visa.prj.orderapp.dao.CustomerRepository;
import com.visa.prj.orderapp.dao.OrderRepository;
import com.visa.prj.orderapp.dao.ProductRepository;
import com.visa.prj.orderapp.dto.OrderReport;
import com.visa.prj.orderapp.entity.Customer;
import com.visa.prj.orderapp.entity.LineItem;
import com.visa.prj.orderapp.entity.Order;
import com.visa.prj.orderapp.entity.Product;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerRepository customerRepository; // constructor wiring
    private final ProductRepository productRepository; // constructor wiring
    private final OrderRepository orderRepository;

    public List<OrderReport> getReport() {
        return  orderRepository.getReport();
    }
    /*
          {
        "customer": {
            "email": "Uma@visa.com"
        },
        "items": [
            {"product": {"id": 2}, "qty": 1},
            {"product": {"id": 1}, "qty" : 2}
        ]
    }

     */
    @Transactional
    public  String placeOrder(Order order) {
        double total = 0.0;
        List<LineItem> items = order.getItems();
        for(LineItem item : items) {
            Product p = getProductById(item.getProduct().getId());
            if(p.getQuantity() < item.getQty()) {
                throw  new IllegalArgumentException("Product " + p.getName() + " not in Stock!!!");
            }
            p.setQuantity(p.getQuantity() - item.getQty()); // DIRTY CHECKING
            item.setAmount(p.getPrice() * item.getQty()); // discount , GST
            total += item.getAmount();
        }
        order.setTotal(total);
        orderRepository.save(order); // saves order and line_items also ==> CASCADE
        return  "Order Placed!!!";
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }


    public  Product getProductById(int id) {
        Optional<Product> opt = productRepository.findById(id);
        if(opt.isPresent()) {
            return opt.get();
        }
        return null;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(Product p) {
        return productRepository.save(p);
    }

    public long getCustomerCount() {
        return  customerRepository.count();
    }

    public Customer addCustomer(Customer c) {
        return customerRepository.save(c);
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

}
