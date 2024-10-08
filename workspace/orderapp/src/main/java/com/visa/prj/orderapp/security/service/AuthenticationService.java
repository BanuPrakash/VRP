package com.visa.prj.orderapp.security.service;

import com.visa.prj.orderapp.security.User;
import com.visa.prj.orderapp.security.dao.UserDao;
import com.visa.prj.orderapp.security.dto.SignInRequest;
import com.visa.prj.orderapp.security.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserDao userDao;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    // register
    public  String signup(SignUpRequest request) {
        var user = User.builder()
        .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(request.getRoles())
                .userName(request.getUsername())
                .build();
        System.out.println(user);
        userDao.save(user); // cascade takes care of saving roles also
        var jwt = jwtService.generateToken(user);
        return jwt;
    }

    // login
    public  String signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userDao.findByEmail(request.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email/password"));
        var jwt = jwtService.generateToken(user);
        return jwt;
    }

}

