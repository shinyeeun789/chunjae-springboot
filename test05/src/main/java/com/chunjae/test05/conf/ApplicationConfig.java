package com.chunjae.test05.conf;

import com.chunjae.test05.biz.UserService;
import com.chunjae.test05.biz.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public UserService userService() { return new UserServiceImpl(); }


}
