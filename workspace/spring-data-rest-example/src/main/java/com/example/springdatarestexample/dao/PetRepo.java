package com.example.springdatarestexample.dao;

import com.example.springdatarestexample.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepo extends JpaRepository<Pet, Integer> {
}
