package com.visa.prj.orderapp.security.service;

import com.visa.prj.orderapp.security.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl {
    private final UserDao userDao;

    public UserDetailsService userDetailsService() {
        return  new UserDetailsService() {
            // username will be email instead
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userDao.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("user not found!!!"));
            }
        };
    }
}
