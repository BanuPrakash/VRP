package com.visa.prj.orderapp.dao;

import com.visa.prj.orderapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
