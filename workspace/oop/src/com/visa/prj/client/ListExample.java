package com.visa.prj.client;

import com.visa.prj.entity.Mobile;
import com.visa.prj.entity.Product;
import com.visa.prj.entity.Tv;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ListExample {
    public static void main(String[] args) {

        List<Integer> ints = Arrays.asList(4,1,3,9,7,6,2,5);
        List<Integer> evens = ints.stream().filter(no -> no % 2 ==0).collect(Collectors.toList());

        List<Product> products = new ArrayList<>();
       products.add(new Product(124, "Sony Bravia", 2_40_000.00, "tv"));
        products.add(new Product(523,"iPhone 15", 89000.00, "mobile"));
        products.add(new Product(871,"Nokia", 4000.00, "mobile"));
        products.add(new Product(988,"Onida", 9000.00, "tv"));
        products.add(new Product(654,"Wacom", 7000, "computer"));

        List<Product> mobiles = products.stream().filter(p -> p.getCategory().equals("tv")).collect(Collectors.toList());

        List<String> names = products.stream().filter(p -> p.getCategory().equals("tv")).map(p -> p.getName()).collect(Collectors.toList());

        double total = products.stream()
                .filter(p -> p.getCategory().equals("mobile"))
                .map(p -> p.getPrice())
                .reduce(0.0, (v1, v2) -> v1 + v2);
        System.out.println(total);

        Map<String, List<Product>> productMap = products.stream().collect(Collectors.groupingBy(p-> p.getCategory()));

//        List<Product> tvs = productMap.get("tv");

        productMap.forEach((k,v) -> {
            System.out.println(k);
            v.forEach(p -> System.out.println(p.getName() + ","  + p.getPrice()));
        });

        //        Collections.sort(products, new Comparator<Product>() {
//            @Override
//            public int compare(Product o1, Product o2) {
//                return Double.compare(o1.getPrice(), o2.getPrice());
//            }
//        });
//
//        //lambda
//        Collections.sort(products, ( o1,  o2) ->  Double.compare(o1.getPrice(), o2.getPrice()));

    }
}
