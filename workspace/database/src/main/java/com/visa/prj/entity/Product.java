package com.visa.prj.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;
}
