package com.visa.prj.dao;

import com.visa.prj.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    void addProduct(Product product) throws DaoException;
    List<Product> getProducts();
}
