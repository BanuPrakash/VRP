package com.visa.petclinic.repo;

import com.visa.petclinic.entity.Vet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VetRepo extends JpaRepository<Vet, Integer> {
}
