package com.example.democonsumerest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class RestTemplateDemoClient implements CommandLineRunner {
    private final RestTemplate template;

    @Override
    public void run(String... args) throws Exception {
//        getPets();
        getPet();
    }

    record PetDTO(String name, Date birthDate) {}

    private void getPet() {
        ResponseEntity<PetDTO> petDTOResponse = template.getForEntity("http://localhost:1234/pets/2", PetDTO.class);
        System.out.println(petDTOResponse.getStatusCode());
        System.out.println(petDTOResponse.getBody());
    }

    private void getPets() {
        String pets = template.getForObject("http://localhost:1234/pets", String.class);
        System.out.println(pets);
    }
}
