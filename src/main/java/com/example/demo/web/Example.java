package com.example.demo.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ZhaoYuJie on 2018/6/20.
 */
@RestController
@EnableAutoConfiguration
public class Example {
    @RequestMapping("/")
    public String home(){
        return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(Example.class,args);
    }
}
