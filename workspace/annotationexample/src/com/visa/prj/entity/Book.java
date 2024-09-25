package com.visa.prj.entity;

import com.visa.prj.annotations.Column;
import com.visa.prj.annotations.Table;

@Table(name = "books")
public class Book {
    private  String isbn;
    private  double price;

    public Book() {
    }

    public Book(String isbn, double price) {
        this.isbn = isbn;
        this.price = price;
    }

    @Column(name="BOOK_ISBN")
    public String getIsbn() {
        return isbn;
    }


    @Column(name="AMT", type = "NUMERIC(12,2)")
    public double getPrice() {
        return price;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
