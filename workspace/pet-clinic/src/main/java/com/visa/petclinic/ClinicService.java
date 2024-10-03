package com.visa.petclinic;

import com.visa.petclinic.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClinicService {
    private final PetRepo petRep;
    private final VetRepo vetRepo;
    private final OwnerRepo ownerRepo;
    private final VisitRepo visitRepo;
    private final SpecialtyRepo specialtyRepo;



}
