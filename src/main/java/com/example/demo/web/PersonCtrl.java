package com.example.demo.web;

import com.example.demo.dao.PersonMapper;
import com.example.demo.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZhaoYuJie on 2018/7/2.
 */
@RestController
@RequestMapping(("/person"))
public class PersonCtrl {
    @Autowired
    private PersonMapper personMapper;

    @RequestMapping("/query")
    public Map<String,Object> query(@RequestParam int id){
        Map<String,Object> retMap = new HashMap<>();
        Person person = personMapper.selectByPrimaryKey(id);

        retMap.put("person",person);

        return retMap;
    }

    @RequestMapping("/add")
    public Map<String,Object> add(Person person){
        Map<String,Object> retMap = new HashMap<>();

        personMapper.insertSelective(person);

        retMap.put("personID",person.getId());

        return retMap;
    }


}
