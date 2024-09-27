package com.visa.springdemo.dao;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnMissingBean(name="employeeDaoJdbcImpl")
public class EmployeeDaoMongoImpl implements EmployeeDao{
    @Override
    public void addEmployee() {
        System.out.println("Mongo Store!!");
    }
}
