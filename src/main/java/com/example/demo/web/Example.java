package com.example.demo.web;

import com.example.demo.bean.PropertiesDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ZhaoYuJie on 2018/6/20.
 */
@RestController
public class Example {
    @Autowired
    private ApplicationArguments applicationArguments;
    @Autowired
    private PropertiesDemo propertiesDemo;

    @RequestMapping("/")
    public String home(){
        System.out.println("=====================6666666666666666");
        System.out.println(applicationArguments.getNonOptionArgs());
        System.out.println(propertiesDemo);
        return "Hello World!";
    }


}
