package com.visa.prj.orderapp.security.service;

import com.visa.prj.orderapp.security.User;
import com.visa.prj.orderapp.security.dao.UserDao;
import com.visa.prj.orderapp.security.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserDao userDao;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    // register
    public  String signup(SignUpRequest request) {
        var user = User.builder()
        .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(request.getRoles())
                .userName(request.getUsername())
                .build();
        userDao.save(user); // cascade takes care of saving roles also
        var jwt = jwtService.generateToken(user);
        return jwt;
    }
}
