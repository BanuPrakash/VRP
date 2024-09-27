package com.visa.springdemo.service;

import com.visa.springdemo.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class AppService {
    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    DataSource ds;

    public  void insert() {
        try {
            Connection con = ds.getConnection();
            System.out.println(con);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select * from products");
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        employeeDao.addEmployee();
    }
}
