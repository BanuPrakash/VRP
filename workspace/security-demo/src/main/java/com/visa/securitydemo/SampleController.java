package com.visa.securitydemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SampleController {

    @GetMapping("/user")
    public String helloUser() {
        return "Hello User!!!";
    }

    @GetMapping("/admin")
    public String helloAdminUser() {
        return "Hello Admin User!!!";
    }

    @GetMapping()
    public String hello() {
        return "Hello All!!!";
    }
}
