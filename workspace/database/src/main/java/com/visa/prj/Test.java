package com.visa.prj;

import com.visa.prj.entity.Product;

public class Test {
    public static void main(String[] args) {
        System.out.println(method());
    }

    private static int method() {
        Product p = new Product();
        p.setName("");
        p.setPrice(333);

        Product p1 = new Product(3, "", 22, 44);

        Product p2= Product.builder().name("Wacom").price(4677.77).build();
        try {
            int[] data = {4,2};
            return data[0];
        } catch (Exception ex) {
            System.out.println(ex);
           // return 100;
        } finally {
            return 999;
        }
    }
}
