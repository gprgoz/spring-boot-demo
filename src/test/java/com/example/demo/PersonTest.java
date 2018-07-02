package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.demo.dao.PersonMapper;
import com.example.demo.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by ZhaoYuJie on 2018/7/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonTest {
    @Autowired
    private PersonMapper personMapper;
    @Test
    public void queryTest(){
        Person person = personMapper.selectByPrimaryKey(1);
        System.out.println(JSON.toJSONString(person));
        System.out.println(JSON.toJSONStringWithDateFormat(person,"yyyy-MM-dd HH:mm:ss",SerializerFeature.WriteDateUseDateFormat));
    }
}
