package com.visa.prj.entity;

import com.visa.prj.annotations.Column;
import com.visa.prj.annotations.Table;

@Table(name="EMP")
public class Employee {
    int empId;
    String firstName;
    String lastName;

    @Column(name="EMP_ID", type = "int")
    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    @Column(name="FNAME")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name="LNAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
