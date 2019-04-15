package com.example.demo.service;

import com.example.demo.domain.ShuiDiChou;

public interface IShuiDiChouService {
    ShuiDiChou selectByPrimaryKey(int id);

    void insertSelective(ShuiDiChou shuiDiChou);
}
