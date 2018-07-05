package com.example.demo.service.impl;

import com.example.demo.dao.PersonMapper;
import com.example.demo.domain.Person;
import com.example.demo.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ZhaoYuJie on 2018/7/5.
 */
@Transactional
@Service
public class PersonServiceImpl implements IPersonService {
    @Autowired
    private PersonMapper personMapper;


    @Transactional(readOnly = true)
    @Override
    public Person selectByPrimaryKey(int id) {
        return personMapper.selectByPrimaryKey(id);
    }

    @Override
    public void insertSelective(Person person) {
        personMapper.insertSelective(person);
        System.out.println(1/0);
    }
}
