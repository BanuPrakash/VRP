package com.visa.prj.orderapp.client;

import com.visa.prj.orderapp.entity.Product;
import org.springframework.beans.BeanUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class ProductDTO {
    public String name;
    public double price;
}
public class Test {
    public static void main(String[] args) {

       String hireDate = "12-JAN-2024 4:50:00";
       Date d = toDate(hireDate);
       System.out.println(toString(d));
    }

    private static Date toDate(String strDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
        try {
            return sdf.parse(strDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static String toString(Date d) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM-dd-yyyy hh:mm:ss");
        try {
            return sdf.format(d);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
