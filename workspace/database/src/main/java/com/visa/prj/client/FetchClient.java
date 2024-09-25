package com.visa.prj.client;

import com.visa.prj.dao.ProductDao;
import com.visa.prj.dao.ProductDaoJdbcImpl;
import com.visa.prj.entity.Product;

import java.util.List;

public class FetchClient {
    public static void main(String[] args) {
        ProductDao productDao = new ProductDaoJdbcImpl();
        List<Product> products = productDao.getProducts();
        for(Product p : products) {
            System.out.println(p); // toString
        }
    }
}
