package com.visa.prj.orderapp.dto;

import java.util.Date;

public record OrderReport(String firstName, String email, Date orderDate, double total) {
}
