package com.visa.prj.orderapp.dao;

import com.visa.prj.orderapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Projections
    List<Product> findByNameLike(String name); // select * from products where name like %name%

    List<Product> findByQuantity(int qty); // select * from products where qty = ?

    List<Product> findByNameAndQuantity(String name, int qty);

    List<Product> findByPriceBetween(double low, double high);

    @Modifying
//    @Query(nativeQuery = true, value = "update products set price = :pr where id =:id") //SQL
    @Query("update Product set price = :pr where id =:id") //JPQL
    void updateProduct(@Param("id") int id, @Param("pr") double price);

    @Query("from Product where name = :n and price =:p")
    List<Product> fetchProducts(@Param("n") String name, @Param("p") double price);
}
