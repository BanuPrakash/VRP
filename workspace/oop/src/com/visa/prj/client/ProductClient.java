package com.visa.prj.client;

import com.visa.prj.entity.Mobile;
import com.visa.prj.entity.Product;
import com.visa.prj.entity.Tv;

import java.lang.reflect.Method;

public class ProductClient {
    public static void main(String[] args) {
        Product[] products = new Product[5]; // Array of Pointers
        products[0] = new Tv(124, "Sony Bravia", 2_40_000.00, "OLED");
        products[1] = new Mobile(523,"iPhone 15", 89000.00, "5G");
        products[2] = new Mobile(871,"Nokia", 4000.00, "3G");
        products[3] = new Tv(988,"Onida", 9000.00, "CRT");
        products[4] = new Mobile(654,"OnePlus 12", 88000.00, "5G");

        displayExpensive(products);

        displayProducts(products);
        System.out.println("********");
        displayProductsOCP(products);
    }
    // OCP
    private static void displayProductsOCP(Product[] products) {
        for(Product p : products) {
           Method[] methods =  p.getClass().getMethods();
           for(Method m : methods) {
               if(m.getName().startsWith("get")) {
                   try {
                       Object ret = m.invoke(p);
                       System.out.println(m.getName().substring(3).toUpperCase() + " : " + ret);
                   }catch (Exception ex) {
                       ex.printStackTrace();
                   }
               }
           }
            System.out.println("******");
        }
    }
    // OCP?
    private static void displayProducts(Product[] products) {
        for(Product p : products) {
            System.out.println(p.getName() + ", " + p.getPrice());
            if(p instanceof  Tv) {
                Tv t = (Tv) p;
                System.out.println(t.getScreenType());
            } else if (p.getClass() == Mobile.class) {
                Mobile m = (Mobile) p;
                System.out.println(m.getConnectivity());
            }
        }
    }


    // OCP
    private static void displayExpensive(Product[] products) {
        for(Product p : products) {
            if(p.isExpensive()) {
                System.out.println(p.getName() + " is expensive!!!");
            }
        }
    }
}
