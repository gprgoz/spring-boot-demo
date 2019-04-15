package com.example.demo.service.impl;

import com.example.demo.dao.ShuiDiChouMapper;
import com.example.demo.domain.ShuiDiChou;
import com.example.demo.service.IShuiDiChouService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ZhaoYuJie on 2018/7/5.
 */
@Transactional
@Service
public class ShuiDiChouServiceImpl implements IShuiDiChouService {
    @Autowired
    private ShuiDiChouMapper shuiDiChouMapper;


    @Transactional(readOnly = true)
    @Override
    public ShuiDiChou selectByPrimaryKey(int id) {
        return shuiDiChouMapper.selectByPrimaryKey(id);
    }

    @Override
    public void insertSelective(ShuiDiChou shuiDiChou) {
        shuiDiChouMapper.insertSelective(shuiDiChou);
    }
}
