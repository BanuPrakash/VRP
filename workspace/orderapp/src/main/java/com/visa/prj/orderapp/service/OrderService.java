package com.visa.prj.orderapp.service;

import com.visa.prj.orderapp.dao.CustomerRepository;
import com.visa.prj.orderapp.dao.ProductRepository;
import com.visa.prj.orderapp.entity.Customer;
import com.visa.prj.orderapp.entity.Product;
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
