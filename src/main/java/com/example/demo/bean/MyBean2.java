package com.example.demo.bean;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 实现CommandLineRunner接口的类可以在SpringApplication启动后运行
 */
@Component
@Order(value = 2)
public class MyBean2 implements CommandLineRunner {

    public void run(String... args) {
        System.out.println("MyBean2 is running with args="+ Arrays.toString(args));
    }

}