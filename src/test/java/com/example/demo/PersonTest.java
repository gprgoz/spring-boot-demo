package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.demo.domain.Person;
import com.example.demo.service.IPersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

/**
 * Created by ZhaoYuJie on 2018/7/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonTest {
    @Autowired
    private IPersonService personService;
    @Test
    public void queryTest(){
        Person person = personService.selectByPrimaryKey(2);
        System.out.println(JSON.toJSONString(person));
        System.out.println(JSON.toJSONStringWithDateFormat(person,"yyyy-MM-dd HH:mm:ss",SerializerFeature.WriteDateUseDateFormat));
    }

    @Test
    public void addTest(){
        Person person = new Person();
        person.setName(System.currentTimeMillis()+"");
        person.setAge((int) (Math.random()*100));
        personService.insertSelective(person);
        System.out.println(JSON.toJSONString(person));
        System.out.println(JSON.toJSONStringWithDateFormat(person,"yyyy-MM-dd HH:mm:ss",SerializerFeature.WriteDateUseDateFormat));
    }

    @Test
    public void updateTest(){
        Person person = new Person();
        person.setId(2);
        person.setName("张三哈哈张三哈哈张三哈哈张三哈哈张三哈哈张三哈哈张三哈哈张三哈哈张三哈哈张三哈哈张三哈哈张三哈哈张三哈哈张三哈哈张三哈哈张三哈哈张三哈哈张三哈哈张三哈哈张三哈哈张三哈哈张三哈哈张三哈哈张三哈哈");
        personService.update(person);
    }


}
