package com.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // mvcMatchers : 접두어만 일치해도 됨
        // antMatchers : 모두 일치해야 됨
        http.authorizeRequests()
                .antMatchers("/", "/**", "/ex").permitAll()
                .anyRequest().authenticated();          // 앞에 설정한 모든 것을 인증해주라는 의미

        return http.build();
    }

}
