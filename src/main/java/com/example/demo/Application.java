package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
//@MapperScan({"com.example.demo.dao","com.clouderwork.log.mapper"})
@MapperScan({"com.example.demo.dao"})
@EnableScheduling
@EnableSwagger2
public class Application {

    public static void main(String[] args) {
        System.out.println("=====================");
        SpringApplication.run(Application.class, args);
        System.out.println("**********************");
    }

}