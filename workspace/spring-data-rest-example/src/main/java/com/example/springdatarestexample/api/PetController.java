package com.example.springdatarestexample.api;

import com.example.springdatarestexample.dao.PetRepo;
import com.example.springdatarestexample.entity.Pet;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@BasePathAwareController
@RequiredArgsConstructor
public class PetController {
    private  final PetRepo petRepo;

    @RequestMapping(value = "pets", method = RequestMethod.GET)
    public @ResponseBody List<Pet> getPets() {
        System.out.println("Called!!!");
        return petRepo.findAll().subList(1,2);
    }
}
