package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
@MapperScan("com.example.demo.dao")
public class Application {

    public static void main(String[] args) {
        System.out.println("=====================");
        SpringApplication.run(Application.class, args);
        System.out.println("**********************");
    }

}