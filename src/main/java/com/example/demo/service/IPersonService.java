package com.example.demo.service;

import com.example.demo.domain.Person;

/**
 * Created by ZhaoYuJie on 2018/7/5.
 */
public interface IPersonService {
    Person selectByPrimaryKey(int id);

    void insertSelective(Person person);
}
