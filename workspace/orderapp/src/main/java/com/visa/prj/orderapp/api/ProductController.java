package com.visa.prj.orderapp.api;

import com.visa.prj.orderapp.entity.Product;
import com.visa.prj.orderapp.service.EntityNotFoundException;
import com.visa.prj.orderapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductController {
    private final OrderService service;

    // GET http://localhost:8080/api/products
    // GET http://localhost:8080/api/products?low=1000&high=20000
    @GetMapping()
    public List<Product> getProducts(@RequestParam(name="low",defaultValue = "0.0") double low,
                                     @RequestParam(name="high",defaultValue = "0.0") double high) {
        if(low ==0.0 && high == 0.0) {
            return service.getProducts();
        } else {
            return service.byRange(low, high);
        }
    }

    // GET http://localhost:8080/api/products/4
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") int id) throws EntityNotFoundException {
        return service.getProductById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product p) {
        return  service.addProduct(p);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable("id") int id, @RequestBody Product p) throws EntityNotFoundException{
        return service.updateProductEntity(id, p);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        return "product " + id + " is deleted!!!";
    }
}
