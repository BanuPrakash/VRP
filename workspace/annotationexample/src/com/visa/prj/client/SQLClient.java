package com.visa.prj.client;

import com.visa.prj.entity.Book;
import com.visa.prj.entity.Employee;
import com.visa.prj.util.SQLUtility;

public class SQLClient {
    public static void main(String[] args) {
        String SQL = SQLUtility.createStatement(Book.class);
        System.out.println(SQL);

        SQL = SQLUtility.createStatement(Employee.class);
        System.out.println(SQL);

        Book b = new Book("HR1234", 650.22);

        SQL = SQLUtility.insertStatement(b);
        System.out.println(SQL);
    }
}
