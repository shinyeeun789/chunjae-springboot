package com.chunjae.test06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Test06Application {

    public static void main(String[] args) {
        SpringApplication.run(Test06Application.class, args);
    }

}
