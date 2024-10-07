package com.visa.prj.orderapp.security.api;

import com.visa.prj.orderapp.security.dto.JwtTokenResponse;
import com.visa.prj.orderapp.security.dto.SignInRequest;
import com.visa.prj.orderapp.security.dto.SignUpRequest;
import com.visa.prj.orderapp.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private  final AuthenticationService service;

    @PostMapping("/register")
    public JwtTokenResponse register(@RequestBody SignUpRequest request) {
        System.out.println("Entered!!!");
        return new JwtTokenResponse(service.signup(request));
    }

    @PostMapping("/login")
    public String login(@RequestBody SignInRequest request) {
        return service.signIn(request);
    }
}
