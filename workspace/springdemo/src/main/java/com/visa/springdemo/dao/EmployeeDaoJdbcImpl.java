package com.visa.springdemo.dao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoJdbcImpl implements EmployeeDao{
    @Override
    public void addEmployee() {
        System.out.println("stored in database!!!");
    }
}
