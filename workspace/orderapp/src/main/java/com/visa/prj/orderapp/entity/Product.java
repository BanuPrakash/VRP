package com.visa.prj.orderapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name="products")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "{NotBlank.product.name}")
    private  String name;

    @Min(value = 10, message = "{Min.product.price}")
    private double price;

    @Min(value = 1, message = "{Min.product.quantity}")
    private int quantity;

    @Version
    @Column(name = "ver")
    int version;
}
