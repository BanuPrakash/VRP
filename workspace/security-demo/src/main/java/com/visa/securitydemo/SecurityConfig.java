package com.visa.securitydemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    DataSource dataSource;

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(requests -> requests.requestMatchers("/admin")
                .hasRole("ADMIN")
                .requestMatchers("/user").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/").permitAll())
                .formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }

@Bean
public PasswordEncoder passwordEncoder() {
        return  new BCryptPasswordEncoder();
}

@Autowired
public void configureGlobal(AuthenticationManagerBuilder authBuilder) throws Exception{
    authBuilder.jdbcAuthentication().dataSource(dataSource);
}

//    @Bean
//    public InMemoryUserDetailsManager users() {
//        return new InMemoryUserDetailsManager(
//                User.withUsername("rita")
//                        .password("{noop}secret")
//                        .authorities("ROLE_USER","ROLE_ADMIN")
//                        .build(),
//                User.withUsername("sita")
//                        .password("{noop}secret")
//                        .authorities("ROLE_USER")
//                        .build()
//        );
//    }
}
