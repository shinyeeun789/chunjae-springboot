package com.shop.config;

import com.shop.service.BookService;
import com.shop.service.BookServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// web.xml
@Configuration
public class WebConfig {

    @Bean
    public BookService bookService() {
        return new BookServiceImpl();
    }

}
