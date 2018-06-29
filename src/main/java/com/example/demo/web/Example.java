package com.example.demo.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ZhaoYuJie on 2018/6/20.
 */
@RestController
public class Example {
    @RequestMapping("/")
    public String home(){
        System.out.println("=====================6666666666666666");
        return "Hello World!";
    }


}
