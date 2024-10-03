package com.visa.petclinic.repo;

import com.visa.petclinic.entity.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialtyRepo extends JpaRepository<Specialty, Integer> {
}
