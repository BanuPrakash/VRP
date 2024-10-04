package com.example.springdatarestexample.dao;

import com.example.springdatarestexample.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepo extends JpaRepository<Visit, Integer> {
}
