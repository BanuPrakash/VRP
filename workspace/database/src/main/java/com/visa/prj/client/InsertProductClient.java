package com.visa.prj.client;

import com.visa.prj.dao.DaoException;
import com.visa.prj.dao.ProductDao;
import com.visa.prj.dao.ProductDaoJdbcImpl;
import com.visa.prj.entity.Product;

public class InsertProductClient {
    public static void main(String[] args) {
        Product  p = Product.builder()
                .name("Reynold")
                .price(50.00)
                .quantity(100).build();
        // use factory
        ProductDao productDao = new ProductDaoJdbcImpl();

        try {
            productDao.addProduct(p);
            System.out.println("Product added!!!");
        } catch (DaoException ex) {
            System.out.println(ex.getMessage()); // end user
            ex.printStackTrace(); // developer
        }

    }
}
