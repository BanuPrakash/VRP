package com.visa.petclinic.repo;

import com.visa.petclinic.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepo extends JpaRepository<Visit, Integer> {
}
